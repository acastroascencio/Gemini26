# 🚀 Framework de Automatización Web: E-Commerce SauceDemo

¡Bienvenido a mi repositorio! Este proyecto es un framework de automatización de pruebas web diseñado con buenas prácticas de ingeniería de calidad (QA). Su objetivo principal es validar el flujo de autenticación (Login) de un E-Commerce manejando múltiples perfiles de usuario de forma dinámica.

Este proyecto sirve como una excelente plantilla base para aprender y escalar la automatización de pruebas en Java.

## 🛠️ Stack Tecnológico

Este framework está construido sobre las herramientas más demandadas en la industria:
* **Lenguaje:** Java 17+
* **Automatización UI:** Selenium WebDriver
* **Gestor de Dependencias:** Maven
* **Motor de Pruebas:** TestNG
* **Reportes Visuales:** ExtentReports

## 🏗️ Arquitectura y Patrones de Diseño Aplicados

Para garantizar que el código sea escalable, mantenible y profesional, se implementaron los siguientes patrones:

1. **Page Object Model (POM):** Separación estricta entre la lógica de las páginas web (localizadores web) y la lógica de las pruebas (Asserts). 
2. **Data-Driven Testing (DDT):** Utilización de `@DataProvider` de TestNG para iterar el mismo caso de prueba de Login con diferentes sets de datos (usuarios válidos, bloqueados e inválidos) sin duplicar código.
3. **Manejo de Excepciones Seguras:** Bloques `try-catch` acoplados a Asserts para capturar errores de aserción sin colapsar el hilo de ejecución, permitiendo que el reporte recoja la evidencia del fallo.

## ⚙️ Pre-requisitos

Para clonar y ejecutar este proyecto en tu máquina local, necesitarás tener instalado:
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (Versión 11 o superior).
* [Apache Maven](https://maven.apache.org/download.cgi) configurado en tus variables de entorno.
* Un IDE de tu preferencia (IntelliJ IDEA, Eclipse, etc.).
* Navegador Google Chrome.

## 🚀 Instalación y Ejecución

Sigue estos pasos para correr el proyecto localmente:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/acastroascencio/Gemini26.git](https://github.com/acastroascencio/Gemini26.git)

2. **Abrir el proyecto:**
Abre tu IDE (ej. IntelliJ IDEA) y selecciona Open. Busca la carpeta Gemini26 que acabas de clonar y espera a que Maven descargue las dependencias necesarias.

3. **Ejecutar las Pruebas:**
Navega a la ruta:
src/main/java/org/example/LoginTest.java
Haz clic derecho sobre la clase LoginTest y selecciona Run 'LoginTest' (o presiona la flecha verde de ejecución).

📊 Reportes Web (Dashboard)
Este framework está configurado para generar evidencia visual de los resultados de las pruebas.

Al finalizar la ejecución, se generará automáticamente un archivo llamado Reporte_QA.html en la raíz del proyecto. Solo haz clic derecho sobre el archivo, selecciona Open in Browser -> Chrome, y podrás interactuar con un Dashboard profesional que detalla:

Porcentaje de Pruebas Exitosas vs Fallidas.

Tiempo de ejecución por cada paso.

Registro de errores (Logs) para facilitar el debuggeo a los desarrolladores.

**Hecho con dedicación para seguir aportando a la comunidad de QA Automation. 💻✨**
---
