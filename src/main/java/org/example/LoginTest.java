package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    // 1. EL PROVEEDOR DE DATOS (Nuestra pequeña base de datos)
    @DataProvider(name = "usuariosDePrueba")
    public Object[][] proveerCredenciales() {
        return new Object[][] {
                {"standard_user", "secret_sauce"}, // Fila 1: Válido (Debe PASAR)
                {"locked_out_user", "secret_sauce"}, // Fila 2: Bloqueado (Debe FALLAR)
                {"hacker_anonimo", "clave_falsa"}    // Fila 3: Inventado (Debe FALLAR)
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

    // 2. CONECTAMOS EL TEST CON LOS DATOS Y AGREGAMOS PARÁMETROS
    @Test(dataProvider = "usuariosDePrueba")
    public void verificarLoginMultiplesUsuarios(String usuario, String password) {
        System.out.println("🚀 Iniciando prueba con el usuario: " + usuario);

        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(password);
        loginPage.hacerClicLogin();

        // 3. EL ASSERT QUE ROMPERÁ A PROPÓSITO
        String urlActual = driver.getCurrentUrl();
        Assert.assertTrue(urlActual.contains("inventory.html"),
                "❌ ERROR: El usuario '" + usuario + "' no pudo entrar al inventario.");
    }

    @AfterMethod
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}