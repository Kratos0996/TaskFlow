# TaskFlow

Interfaz de ejemplo para Android con Kotlin, Jetpack Compose y Material 3.

## Incluye
- Dashboard de productividad con progreso semanal y tarjetas de estadísticas.
- Tarjetas de tareas con categoría, prioridad, fecha y estado.
- Chips de filtro presentacionales (todavía no filtran).
- Botón “Add task” visual (todavía no crea tareas).
- Alternancia funcional entre tema claro y oscuro.
- Datos de ejemplo en `data/TaskItem.kt`.

## Abrir en Android Studio
1. Descomprime el archivo ZIP.
2. En Android Studio selecciona **Open** y elige la carpeta `TaskFlow`.
3. Espera a que termine la sincronización de Gradle. Si Android Studio solicita instalar SDK 36, acepta o cambia `compileSdk` y `targetSdk` en `app/build.gradle.kts` a la versión instalada.
4. Ejecuta la configuración `app` en un emulador o dispositivo Android.

## Organización
- `MainActivity.kt`: punto de entrada y estado del tema.
- `data/TaskItem.kt`: modelos y datos de muestra.
- `ui/screens/DashboardScreen.kt`: pantalla principal.
- `ui/components/TaskCard.kt`: tarjeta reutilizable de tarea.
- `ui/components/StatCard.kt`: estadísticas reutilizables.
- `ui/theme/Color.kt`: paleta.
- `ui/theme/Theme.kt`: esquemas Material 3.

## Alcance actual
Es un prototipo visual. Los filtros, el botón de añadir y las notificaciones no ejecutan acciones; no hay persistencia, ViewModel ni lógica de negocio. El icono de luna/sol sí alterna el tema.
