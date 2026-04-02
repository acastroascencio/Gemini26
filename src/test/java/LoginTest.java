import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    // 1. Variables globales para nuestro Reporte Web
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void configurarReporte() {
        // Le decimos cómo se llamará el archivo y lo inicializamos
        ExtentSparkReporter spark = new ExtentSparkReporter("Reporte_QA.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @DataProvider(name = "usuariosDePrueba")
    public Object[][] proveerCredenciales() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"hacker_anonimo", "clave_falsa"}
        };
    }

    @BeforeMethod
    public void configurarNavegador() {
        // Le ponemos los lentes de visión nocturna a Chrome (Modo Fantasma)
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--headless=new"); // Corre sin abrir ventana visual
        options.addArguments("--window-size=1920,1080"); // Le damos un tamaño de pantalla virtual

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "usuariosDePrueba")
    public void verificarLoginMultiplesUsuarios(String usuario, String password) {
        test = extent.createTest("Prueba de Login: " + usuario);
        test.log(Status.INFO, "Navegador abierto. Intentando login con: " + usuario);

        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(password);
        loginPage.hacerClicLogin();

        String urlActual = driver.getCurrentUrl();

        try {
            Assert.assertTrue(urlActual.contains("inventory.html"), "El usuario no pudo entrar.");
            test.log(Status.PASS, "✅ Login exitoso. Validado correctamente.");
        } catch (AssertionError e) {
            // 1. TOMAR LA FOTO: Convertimos la pantalla en código Base64
            org.openqa.selenium.TakesScreenshot camara = (org.openqa.selenium.TakesScreenshot) driver;
            String base64Screenshot = camara.getScreenshotAs(org.openqa.selenium.OutputType.BASE64);

            // 2. ADJUNTAR AL REPORTE: Creamos la imagen en ExtentReports
            com.aventstack.extentreports.model.Media evidencia = com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();

            // 3. REGISTRAR EL FALLO: Le pasamos el texto rojo y la foto al reporte
            test.log(Status.FAIL, "❌ Fallo en el Login: " + e.getMessage(), evidencia);

            // 4. ROMPER LA PRUEBA EN TESTNG
            Assert.fail(e.getMessage());
        }
    }
    @AfterMethod
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void guardarReporte() {
        // El alwaysRun garantiza que el reporte se genere sí o sí
        if (extent != null) {
            extent.flush();
        }
    }
}