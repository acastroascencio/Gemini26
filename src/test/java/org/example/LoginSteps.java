package org.example;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Dado("que estoy en la página de inicio de sesión de SauceDemo")
    public void que_estoy_en_la_pagina_de_inicio() {
        // Ejecutamos Chrome en modo fantasma para que no explote en la nube después
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Cuando("ingreso el usuario {string} y la contraseña {string}")
    public void ingreso_el_usuario_y_la_contrasena(String usuario, String contrasena) {
        // Cucumber inyecta mágicamente las palabras "standard_user" y "secret_sauce" aquí
        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(contrasena);
        loginPage.hacerClicLogin();
    }

    @Entonces("debería ver el panel principal de productos")
    public void deberia_ver_el_panel_principal() {
        // Validamos el login exitoso comprobando que la URL cambió a la página de inventario
        String urlActual = driver.getCurrentUrl();
        Assert.assertTrue(urlActual.contains("inventory"), "El login falló. No entramos al inventario.");

        // Cerramos el navegador fantasma
        driver.quit();
    }
}