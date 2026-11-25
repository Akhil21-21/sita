package Project_1;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener implements ITestListener, IConfigurationListener {

    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static final String SCREENSHOT_PATH = "C:/CRM_FrameWork/Screenshot";

    // ================= TEST CONTEXT START =================


@Override
public void onStart(ITestContext context) {

    if (extent == null) {

        new File("Reports").mkdirs();
        new File(SCREENSHOT_PATH).mkdirs();

        ExtentSparkReporter spark =
                new ExtentSparkReporter("Reports/HRMS_ExtentReport.html");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project ➡️➡️ ", "HRMS Automation");
        extent.setSystemInfo("Application ➡️➡️ ", "Horilla HRMS");
        extent.setSystemInfo("Automation Tool ➡️➡️ ", "Selenium WebDriver");
        extent.setSystemInfo("Test Framework ➡️➡️ ", "TestNG");
        extent.setSystemInfo("Testing Features -1 ➡️➡️ " , "Employee Registration");
        extent.setSystemInfo("Testing Features -2 ➡️➡️ " , "Employee Assets");
        extent.setSystemInfo("Testing Features -3 ➡️➡️ " , "Employee Leaves");
    }

    System.out.println("========== TEST CONTEXT STARTED: "
            + context.getName() + " ==========");
}

    // ================= TEST START =================


@Override
public void onTestStart(ITestResult result) {

    String browser = result.getTestContext()
            .getCurrentXmlTest()
            .getParameter("browser");

    ExtentTest extentTest = extent.createTest(
            browser + " - " + result.getMethod().getMethodName()
    );

    test.set(extentTest);

    test.get().info("Browser: " + browser);
    test.get().info("Test execution started");

    System.out.println("TEST STARTED: " + browser + " - " + result.getName());
}


    // ================= TEST SUCCESS =================

    @Override
    public void onTestSuccess(ITestResult result) {

        logReporterMessages(result);
        test.get().pass("TEST PASSED SUCCESSFULLY😊😊❤️😊😊👌👌");

        System.out.println("TEST PASSED: " + result.getName());
    }

    // ================= TEST FAILURE =================

    @Override
    public void onTestFailure(ITestResult result) {

        Throwable error = result.getThrowable();
        String stackTrace = getStackTrace(error);

        System.err.println("========== TEST FAILED ==========");
        System.err.println("Test Name: " + result.getName());
        System.err.println(stackTrace);

        ensureExtentTest(result, "Test Failure");
        logReporterMessages(result);

        test.get().fail("TEST FAILED");

        if (error != null) {
            test.get().fail(stackTrace);
        }

        captureFailureScreenshot(result);
    }

    // ================= CONFIGURATION FAILURE =================
    // Captures failures in @BeforeMethod, @AfterMethod, @BeforeClass, etc.

    @Override
    public void onConfigurationFailure(ITestResult result) {

        Throwable error = result.getThrowable();
        String stackTrace = getStackTrace(error);

        System.err.println("========== TESTNG CONFIGURATION FAILED ==========");
        System.err.println("Configuration: " + result.getName());
        System.err.println(stackTrace);

        ensureExtentTest(result, "Configuration Failure");

        if (error != null) {
            test.get().fail("Configuration failed: " + result.getName());
            test.get().fail(stackTrace);
        }

        captureFailureScreenshot(result);
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        // No additional action required.
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {
        System.out.println("CONFIGURATION SKIPPED: " + result.getName());
    }

    // ================= SCREENSHOT CAPTURE =================

    private void captureFailureScreenshot(ITestResult result) {

        WebDriver driver = BaseTest.d;

        if (driver == null) {
            if (test.get() != null) {
                test.get().warning("Screenshot not captured: WebDriver is null.");
            }
            System.out.println("Screenshot not captured because WebDriver is null.");
            return;
        }

        File screenshotFolder = new File(SCREENSHOT_PATH);
        screenshotFolder.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        String screenshotName = browser.toLowerCase() + "_Screenshot_" + timeStamp + ".png";
        File destination = new File(screenshotFolder, screenshotName);

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        boolean saved = source.renameTo(destination);

        if (saved) {
            ensureExtentTest(result, "Failure Screenshot");

            test.get().fail(
                    "Failure Screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(destination.getAbsolutePath())
                            .build()
            );

            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } else {
            if (test.get() != null) {
                test.get().warning("Screenshot file could not be moved to: "
                        + destination.getAbsolutePath());
            }
            System.out.println("Screenshot file could not be moved to: "
                    + destination.getAbsolutePath());
        }
    }

    // ================= EXTENT TEST INITIALIZATION =================

    private void ensureExtentTest(ITestResult result, String label) {

        if (test.get() == null && extent != null) {
            test.set(extent.createTest(label + " - " + result.getName()));
        }
    }

    // ================= STACK TRACE =================

    private String getStackTrace(Throwable error) {

        if (error == null) {
            return "No exception details were provided.";
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);

        return sw.toString();
    }

    // ================= TEST SKIPPED =================

    @Override
    public void onTestSkipped(ITestResult result) {

        ensureExtentTest(result, "Test Skipped");
        logReporterMessages(result);

        test.get().skip("TEST SKIPPED");

        if (result.getThrowable() != null) {
            test.get().skip(result.getThrowable());
            result.getThrowable().printStackTrace();
        }

        System.out.println("TEST SKIPPED: " + result.getName());
    }

    // ================= REPORTER LOG =================

    public void logReporterMessages(ITestResult result) {

        if (test.get() == null) {
            return;
        }

        List<String> messages = Reporter.getOutput(result);

        for (String message : messages) {
            test.get().info(message);
        }
    }

    // ================= TEST CONTEXT FINISH =================

    @Override
    public void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }

        System.out.println("========== TEST CONTEXT COMPLETED ==========");
        System.out.println("Extent Report: Reports/HRMS_ExtentReport.html");
    }
}

