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

    @Test
    public void crearNuevoRegistroPost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // 1. Preparamos la "Carga Útil" (El JSON que le inyectaremos al servidor)
        String cuerpoJson = "{\n" +
                "  \"title\": \"Arquitecto QA Automation\",\n" +
                "  \"body\": \"Dominando la Matrix con Rest-Assured\",\n" +
                "  \"userId\": 1\n" +
                "}";

        System.out.println("--- ENVIANDO POST (CREAR REGISTRO) ---");

        // 2. Patrón GIVEN - WHEN - THEN
        given()
                // Le decimos al guardia: "Oye, traigo un paquete en formato JSON"
                .header("Content-type", "application/json; charset=UTF-8")
                .body(cuerpoJson) // Entregamos el paquete
                .log().all()
                .when()
                .post("/posts") // ¡Disparamos el método POST!
                .then()
                .log().all()
                .statusCode(201) // ASSERT: Validamos que responda "201 Created" (Creado exitosamente)
                .body("title", equalTo("Arquitecto QA Automation")); // Validamos que el servidor guardó nuestro texto

        System.out.println("✅ Prueba POST superada. Hemos inyectado datos en el servidor.");
    }
}