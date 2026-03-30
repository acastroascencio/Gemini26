package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void probarApiDeUsuarios() {
        // 1. Cambiamos a un servidor amigable para QA (Sin Cloudflare)
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        System.out.println("--- ENVIANDO PETICIÓN (REQUEST) ---");

        // 2. Patrón GIVEN - WHEN - THEN
        given()
                .log().all()
                .when()
                .get("/users/2") // Pedimos el usuario 2 de este nuevo servidor
                .then()
                .log().all()
                .statusCode(200) // Validamos que ahora SÍ nos dé pase libre (200 OK)
                .body("name", equalTo("Ervin Howell")) // Validamos el nombre en la BD
                .body("username", equalTo("Antonette")); // Validamos el nombre de usuario

        System.out.println("✅ Prueba de API superada. El servidor respondió correctamente.");
    }
}