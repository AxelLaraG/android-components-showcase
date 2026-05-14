# Android Components & Hardware Showcase 📱🛠️

Aplicación nativa para Android desarrollada en Kotlin que sirve como un entorno de pruebas (sandbox) y referencia técnica. El proyecto consolida la implementación funcional de múltiples APIs del sistema, componentes de interfaz de usuario avanzados y acceso a sensores de hardware.

## 🌟 Módulos de Implementación

El proyecto está dividido en actividades y fragmentos independientes que demuestran capacidades específicas del SDK de Android:

* **Integración de Hardware y Sensores:** * Captura de medios nativa mediante la integración de la cámara (`MainCamera.kt`).
  * Servicios de localización y rastreo de coordenadas mediante el sensor GPS (`GPS.kt`).
* **Gestión Multimedia:** * Reproducción nativa de video (`VideoPlayer.kt`).
  * Exploración y renderizado de imágenes mediante adaptadores personalizados optimizados (`MainGallery.kt`, `ImageAdapter.kt`).
* **Navegación y UI Avanzada:** * Implementación de patrones de navegación modernos como `ViewPager` y pestañas interactivas (`MainTabs.kt`).
  * Manejo de menús jerárquicos y ventanas emergentes personalizadas (`activity_popup.xml`).
* **Componentes de Estado:** * Integración de manejadores de tiempo y reloj (`Time.kt`).
  * Visualización de carga mediante barras de progreso dinámicas (`Progress.kt`).

## 🛠️ Stack Tecnológico

* **Lenguaje:** Kotlin.
* **Plataforma:** Android SDK nativo.
* **Sistema de Compilación:** Gradle (Kotlin DSL).
* **UI:** XML Layouts clásicos.

## 📦 Uso y Compilación

Este proyecto está pensado como una referencia arquitectónica. Para ejecutarlo localmente:

1. Clona el repositorio en tu máquina local.
2. Abre el directorio principal con **Android Studio**.
3. Permite que Gradle sincronice las dependencias.
4. Ejecuta el módulo `app` en un emulador o dispositivo físico. 
*(Nota: Para los módulos de Cámara y GPS, es estrictamente recomendable probar en un dispositivo físico para habilitar los permisos correspondientes correctamente).*
