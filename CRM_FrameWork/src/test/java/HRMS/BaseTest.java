package HRMS;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    // One browser session shared by EmplRegis, Asset and Leave.
    public static WebDriver d;

    // Generated once in EmplRegis and reused by Asset and Leave.
    public static String firstName;
    public static String lastName;
    public static String badgeId;

    protected WebDriverWait wait;

    @Parameters("browser")
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("chrome") String browser) {
        Reporter.log("========== HRMS SUITE STARTED ==========", true);

        // Safety guard: do not open a second browser if one already exists.
        if (d == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-save-password-bubble");
                options.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false,
                        "autofill.credit_card_enabled", false,
                        "profile.default_content_setting_values.notifications", 2
                ));
                d = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                d = new FirefoxDriver(options);
            } else {
                throw new IllegalArgumentException("Invalid browser: " + browser);
            }

            d.manage().window().maximize();
            d.manage().timeouts().implicitlyWait(Duration.ZERO);
            Reporter.log(browser + " browser launched successfully", true);
            System.out.println(browser + " browser launched successfully");
        }

        wait = new WebDriverWait(d, Duration.ofSeconds(15));
    }

    // Shared notification helper used by the module classes.
    protected void clearSuccessNotifications(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('div.oh-alert--success').forEach(function(alert) { alert.remove(); });"
        );
    }

    // Do not quit the browser after each class. Asset and Leave need the same
    // logged-in session created by EmplRegis.
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        try {
            Reporter.log("========== HRMS SUITE COMPLETED ==========", true);
        } finally {
            if (d != null) {
                d.quit();
                d = null;
                System.out.println("Shared browser closed after all HRMS modules");
                Reporter.log("Shared browser closed after all HRMS modules", true);
            }
        }
    }
}

