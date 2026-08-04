package com.antigravity.spamquarantine.service

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import com.antigravity.spamquarantine.data.db.AppDatabase
import com.antigravity.spamquarantine.data.model.QuarantineLogEntity
import com.antigravity.spamquarantine.data.model.RuleEntity
import com.antigravity.spamquarantine.util.PhoneUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpamCallScreeningService : CallScreeningService() {

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onScreenCall(callDetails: Call.Details) {
        val rawHandle: Uri? = callDetails.handle
        val rawNumber: String = rawHandle?.schemeSpecificPart ?: ""

        if (rawNumber.isBlank()) {
            respondToCall(callDetails, CallResponse.Builder().build())
            return
        }

        val normalizedNumber = PhoneUtils.normalizeChilePhoneNumber(rawNumber)
        Log.d("SpamScreening", "Llamada entrante evaluada: Raw=$rawNumber -> E.164=$normalizedNumber")

        // 1. Verificar si el número está en la agenda de contactos (Lista Blanca automática)
        if (isContact(this, rawNumber) || isContact(this, normalizedNumber)) {
            Log.d("SpamScreening", "Número $normalizedNumber está en Contactos. Permitido.")
            respondToCall(callDetails, CallResponse.Builder().build())
            return
        }

        // 2. Evaluar reglas de patrones de spam activas en la BD
        serviceScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            var activeRules = db.ruleDao().getActiveRules()

            // Si la base de datos está vacía, precargar las reglas por defecto para Chile
            if (activeRules.isEmpty() && db.ruleDao().getRuleCount() == 0) {
                PhoneUtils.getDefaultChileSpamPatterns().forEach { (pattern, desc) ->
                    db.ruleDao().insertRule(RuleEntity(pattern = pattern, description = desc))
                }
                activeRules = db.ruleDao().getActiveRules()
            }

            var matchedRule: RuleEntity? = null
            for (rule in activeRules) {
                if (PhoneUtils.matchesRegexPattern(normalizedNumber, rule.pattern) ||
                    PhoneUtils.matchesRegexPattern(rawNumber, rule.pattern)) {
                    matchedRule = rule
                    break
                }
            }

            if (matchedRule != null) {
                Log.w("SpamScreening", "LLAMADA SPAM DETECTADA Y BLOQUEADA: $normalizedNumber por patrón ${matchedRule.pattern}")

                // Registrar en la base de datos de Cuarentena
                db.quarantineDao().insertLog(
                    QuarantineLogEntity(
                        rawPhoneNumber = rawNumber,
                        normalizedPhoneNumber = normalizedNumber,
                        matchedPattern = matchedRule.pattern
                    )
                )

                // Responder a Android: Cortar llamada antes de sonar
                val response = CallResponse.Builder()
                    .setDisallowCall(true)  // Bloquear
                    .setRejectCall(true)    // Rechazar llamada
                    .setSkipCallLog(false)  // Conservar en log para auditoría
                    .setSkipNotification(true) // No mostrar notificación nativa molesta
                    .build()

                respondToCall(callDetails, response)
            } else {
                Log.d("SpamScreening", "Llamada $normalizedNumber no coincide con ningún patrón de spam. Permitido.")
                respondToCall(callDetails, CallResponse.Builder().build())
            }
        }
    }

    /**
     * Revisa si el número de teléfono existe en los contactos locales del dispositivo.
     */
    private fun isContact(context: Context, number: String): Boolean {
        if (number.isBlank()) return false
        return try {
            val uri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number)
            )
            val projection = arrayOf(ContactsContract.PhoneLookup._ID)
            context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                cursor.count > 0
            } ?: false
        } catch (e: Exception) {
            Log.e("SpamScreening", "Error al consultar Contactos: ${e.message}")
            false
        }
    }
}
