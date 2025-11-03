# EV2 APIs — Kotlin + Jetpack Compose + MVVM

Proyecto base listo para **EV2 (encargo)** y **EV3 (defensa)**. Cubre validaciones, navegación, gestión de estado, persistencia local (DataStore), recurso nativo (Photo Picker), animaciones y accesibilidad básica.

## Stack
- Kotlin, Jetpack Compose (Material3)
- Navigation Compose, StateFlow + ViewModel
- DataStore (preferencias)
- Coil para imágenes
- MinSdk 24, Target 35

## Módulos clave
- `domain.validation.FormValidator`: reglas desacopladas, modificables en vivo.
- `data.DataStoreManager`: token, favoritos, tema, notificaciones, avatar.
- `ui/screen/*`: Auth, Catálogo, Detalle, Favoritos, Perfil.
- `navigation.AppNav`: Scaffold, Snackbar, rutas.
- `viewmodel.MainViewModel`: estado global (SessionState).

## Cómo correr
1. Android Studio Jellyfish o superior.
2. Abrir carpeta raíz, esperar sync de Gradle.
3. Ejecutar en AVD (Pixel 7, API 34+).

## Defensa (cambios en vivo sugeridos)
1) **Validación**: cambiar `minLen` de 6 a 8 en Auth para mostrar error en tiempo real.
2) **Persistencia**: marcar/desmarcar favorito en Catálogo y comprobar recarga inmediata; alternar notificaciones/tema en Perfil.

## Rúbrica — Evidencias
- **Validación + cambios en vivo**: `AuthScreen` + `FormValidator`.
- **Diseño/Navegación**: Material3 + NavHost.
- **Nuevo componente/animación**: icono favorito + `AnimatedVisibility`.
- **Gestión de estado**: `MainViewModel` + `StateFlow`.
- **Persistencia**: `DataStoreManager` con flujos y mutación.
- **Arquitectura + Colaboración**: MVVM; subir a GitHub y usar Trello/Jira.
- **Recurso nativo**: Photo Picker integrado en `ApiDetailScreen`.
- **Demostración UI**: AsyncImage renderiza selección en tiempo real.

## Notas
- Solicitud de permiso `POST_NOTIFICATIONS` (Android 13+) gestionada al inicio (fallback silencioso).
- Accesibilidad: `contentDescription` en componentes clave.


## Mejoras recientes
- Validación de contraseña: ahora exige número y **mayúscula** (configurable).
- Campos con **icono de error** + `supportingText` animado.
- **AnimatedPrimaryButton** con efecto de **scale** al presionar.
- **Photo Picker** con feedback si el usuario cancela/deniega.
- `AsyncImage` con **placeholder/error** y recorte controlado.
- Chip de **cambio de regla en vivo** (minLen 6↔8) para defensa.
