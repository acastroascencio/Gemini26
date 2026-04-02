package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

// ¡Aquí está la magia! El Fotógrafo Global vigilando esta clase
@Listeners(ExtentReportListener.class)
public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    // 1. Proveedor de Datos (Nuestros 3 usuarios de prueba)
    @DataProvider(name = "usuarios")
    public Object[][] datosLogin() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"}
        };
    }

    // 2. Configuración antes de cada intento de login
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Modo fantasma para GitHub Actions

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
    }

    // 3. La Prueba Pura (Sin código basura de reportes)
    @Test(dataProvider = "usuarios")
    public void verificarLoginMultiplesUsuarios(String usuario, String contrasena) {
        // Acciones en la página
        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(contrasena);
        loginPage.hacerClicLogin();

        // Validación
        String urlActual = driver.getCurrentUrl();
        Assert.assertTrue(urlActual.contains("inventory.html"), "El usuario no pudo entrar.");
    }

    // 4. Limpieza después de cada intento
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}