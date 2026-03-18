package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando prueba con POM...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // 🌟 AQUÍ EMPIEZA LA MAGIA DE POM 🌟
        // Llamamos a nuestro "Mesero" y le damos el navegador
        LoginPage loginPage = new LoginPage(driver);

        // Le damos órdenes claras y legibles
        loginPage.escribirUsuario("standard_user");
        loginPage.escribirPassword("secret_sauce");
        loginPage.hacerClicLogin();

        // El Assert sigue en la prueba, no en la página
        if (driver.getCurrentUrl().contains("inventory.html")) {
            System.out.println("✅ PRUEBA PASADA con POM: Diseño profesional implementado.");
        } else {
            System.out.println("❌ PRUEBA FALLIDA.");
        }

        // driver.quit();
    }
}