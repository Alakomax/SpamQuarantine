package com.antigravity.spamquarantine

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.antigravity.spamquarantine.ui.HomeScreen
import com.antigravity.spamquarantine.ui.QuarantineScreen
import com.antigravity.spamquarantine.ui.RulesScreen

class MainActivity : ComponentActivity() {

    private val roleRequestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "¡Filtro de llamadas activado correctamente!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Se requiere activar el permiso para interceptar llamadas spam.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainAppStructure(onRequestRole = { requestCallScreeningRole() })
            }
        }
    }

    private fun requestCallScreeningRole() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = getSystemService(Context.ROLE_SERVICE) as? RoleManager
            if (roleManager != null && !roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING)) {
                val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
                roleRequestLauncher.launch(intent)
            } else {
                Toast.makeText(this, "El filtro de llamadas ya se encuentra activo.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Su versión de Android gestiona el permiso desde los ajustes del sistema.", Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun MainAppStructure(onRequestRole: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Block, contentDescription = "Cuarentena") },
                    label = { Text("Cuarentena") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.List, contentDescription = "Reglas") },
                    label = { Text("Reglas") }
                )
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> HomeScreen(onRequestRole = onRequestRole)
                1 -> QuarantineScreen()
                2 -> RulesScreen()
            }
        }
    }
}
