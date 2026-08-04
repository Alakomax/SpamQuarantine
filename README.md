# SpamQuarantine - App de Bloqueo de Spam en Chile (Android Nativo)

Aplicación móvil nativa en Kotlin para Android (API 29+) diseñada para interceptar y rechazar llamadas entrantes de spam telefónico por patrones Regex/wildcards antes del primer timbre, archivando los eventos en un historial de cuarentena local.

## Características

* ⚡ **Interceptación previo a timbre:** Utiliza `CallScreeningService` para rechazar la llamada en milisegundos sin encender la pantalla.
* 🛡️ **Normalización E.164 Chile:** Procesa números locales e internacionales (`600`, `800`, `809`, `+56 9`, `(80)`).
* 📋 **Base de Datos de Cuarentena (Room):** Historial completo con fecha, hora, número y patrón coincidente.
* ⚙️ **Administrador de Reglas Regex:** Permite agregar o desactivar rangos de números salvajes (ej: `^\+5692882\d{4}$`).
* 🔒 **Lista Blanca Automática:** Las llamadas de contactos guardados en la agenda del teléfono nunca son bloqueadas.
* 🎨 **Interfaz Moderna en Jetpack Compose:** Material Design 3 con soporte para tema claro/oscuro.

## Requisitos y Compilación

* Android Studio Hedgehog (2023.1.1) o posterior.
* JDK 17+
* Gradle 8.2+

### Compilación desde CLI:
```bash
./gradlew assembleDebug
```
El archivo APK resultante estará ubicado en `app/build/outputs/apk/debug/app-debug.apk`.
