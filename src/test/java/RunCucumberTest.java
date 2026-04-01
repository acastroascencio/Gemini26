package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Dónde está el texto en español
        glue = "org.example",                     // Dónde está el código Java traductor
        plugin = {"pretty"}                       // Para que se vea bonito en la consola
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    // ¡Esta clase se queda vacía! Solo sirve como control remoto.
}