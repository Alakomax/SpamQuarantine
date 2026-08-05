package com.antigravity.spamquarantine.util

import android.content.Context
import com.antigravity.spamquarantine.data.db.AppDatabase
import com.antigravity.spamquarantine.data.model.RuleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

object SpamRuleCache {

    @Volatile
    private var cachedRules: List<RuleEntity>? = null

    /**
     * Obtiene la lista de reglas activas en memoria de manera ultra-rápida y síncrona.
     * Evita consultas a disco durante la llegada de una llamada entrante.
     */
    fun getActiveRulesSync(context: Context): List<RuleEntity> {
        val current = cachedRules
        if (current != null) {
            return current
        }

        return synchronized(this) {
            cachedRules ?: runBlocking(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getDatabase(context.applicationContext)
                    var rules = db.ruleDao().getActiveRules()
                    if (rules.isEmpty() && db.ruleDao().getRuleCount() == 0) {
                        PhoneUtils.getDefaultChileSpamPatterns().forEach { (pattern, desc) ->
                            db.ruleDao().insertRule(RuleEntity(pattern = pattern, description = desc))
                        }
                        rules = db.ruleDao().getActiveRules()
                    }
                    cachedRules = rules
                    rules
                } catch (e: Exception) {
                    PhoneUtils.getDefaultChileSpamPatterns().map { (pattern, desc) ->
                        RuleEntity(pattern = pattern, description = desc)
                    }.also { cachedRules = it }
                }
            }
        }
    }

    /**
     * Invalida el caché en memoria para que se recargue la próxima vez desde la BD.
     */
    fun invalidateCache() {
        cachedRules = null
    }

    /**
     * Actualiza directamente la lista de reglas activas en el caché.
     */
    fun updateCache(rules: List<RuleEntity>) {
        cachedRules = rules.filter { it.isActive }
    }
}
