package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "usuariosDePrueba")
    public void verificarLoginMultiplesUsuarios(String usuario, String password) {
        // 2. Creamos una nueva entrada en el reporte para este usuario
        test = extent.createTest("Prueba de Login: " + usuario);
        test.log(Status.INFO, "Navegador abierto. Intentando login con: " + usuario);

        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(password);
        loginPage.hacerClicLogin();

        String urlActual = driver.getCurrentUrl();

        // 3. Evaluamos el resultado y le avisamos al reporte
        try {
            Assert.assertTrue(urlActual.contains("inventory.html"), "El usuario no pudo entrar.");
            test.log(Status.PASS, "✅ Login exitoso. Validado correctamente.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "❌ Fallo en el Login: " + e.getMessage());
            Assert.fail(e.getMessage()); // Le avisamos a TestNG que la prueba falló
        }
    }

    @AfterMethod
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void guardarReporte() {
        // 4. Esto es vital: Escribe físicamente el archivo HTML en tu computadora
        extent.flush();
    }
}