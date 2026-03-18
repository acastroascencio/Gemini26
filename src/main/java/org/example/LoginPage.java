package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // 1. El "Mesero" necesita saber en qué navegador trabajar
    private WebDriver driver;

    // 2. Aquí guardamos las "coordenadas" (IDs) UNA SOLA VEZ
    private By usuarioInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By botonLogin = By.id("login-button");

    // Constructor: Conecta la página con el navegador
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Las "acciones" que puede hacer esta página
    public void escribirUsuario(String usuario) {
        driver.findElement(usuarioInput).sendKeys(usuario);
    }

    public void escribirPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void hacerClicLogin() {
        driver.findElement(botonLogin).click();
    }
}