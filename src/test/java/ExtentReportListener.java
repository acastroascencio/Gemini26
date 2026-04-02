package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    // 1. Cuando empieza toda la suite, preparamos el lienzo en blanco
    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("Reporte_QA.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // 2. Cada vez que arranca un @Test, le tomamos una foto al nombre
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // 3. Si el @Test termina en verde, lo anotamos
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("✅ La prueba pasó con éxito.");
    }

    // 4. Si el @Test falla, capturamos el error
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("❌ La prueba falló. Razón: " + result.getThrowable().getMessage());
    }

    // 5. Al final de todas las pruebas, guardamos el archivo HTML
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}