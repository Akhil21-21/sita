package Project_1;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    public static WebDriver d;
	public static String firstName;
	public static String lastName;
	public static String badgeId;
    protected WebDriverWait wait;
    private boolean loggedIn = false;

    // Executes once before the entire TestNG suite
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        Reporter.log("\n========== SUITE STARTED ==========\n", true);
    }

    // Executes once before this test class

@Parameters("browser")
@BeforeClass(alwaysRun = true)
public void beforeClass(@Optional("chrome") String browser) {

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

    wait = new WebDriverWait(d, Duration.ofSeconds(15));

    System.out.println(browser + " browser launched successfully");
}

    
 // Executes after every @Test method
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        if (d == null || !loggedIn) {
            return;
        }

        // Close allocation modal if it is still open
        ((JavascriptExecutor) d).executeScript(
                "var modal = document.getElementById('allocationModal');" +
                "if (modal) {" +
                "modal.classList.remove('oh-modal--show');" +
                "modal.style.display = 'none';" +
                "}"
        );

        // Wait until allocation modal disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("allocationModal")
        ));

        // Wait until notification disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oh-alert.oh-alert--animated.oh-alert--info")
        ));

        // Click the profile BUTTON instead of the image
        By profileButton = By.xpath(
                "//*[@id='mainHeader']/div[2]/div[2]/button"
        );

        WebElement profile = wait.until(
                ExpectedConditions.elementToBeClickable(profileButton)
        );

        profile.click();

        System.out.println("Profile menu opened");


     // Inspect visible Logout elements in the profile menu
     String logoutDetails = (String) ((JavascriptExecutor) d).executeScript(
             "return Array.from(document.querySelectorAll('a, button, [role=menuitem]'))" +
             ".filter(e => e.offsetParent !== null)" +
             ".filter(e => /log\\s*out/i.test(e.innerText.trim()))" +
             ".map(e => 'TEXT: ' + e.innerText.trim() + " +
             "' | HREF: ' + e.getAttribute('href') + " +
             "' | HTML: ' + e.outerHTML)" +
             ".join('\\n');"
     );

     System.out.println("========== LOGOUT ELEMENT DETAILS ==========");
     System.out.println(logoutDetails);

     if (logoutDetails.isEmpty()) {
         System.out.println("No visible Logout element found.");
     }
    }
        
    // Executes before every @Test method
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        System.out.println("Starting test execution");

    }
    
    // Executes once after all @Test methods in this class
    @AfterClass(alwaysRun = true)
    public void afterClass() {

        if (d != null) {
            d.quit();
            d = null;
            System.out.println("Chrome browser closed");
        }

        Reporter.log("===== EMPLOYEE AUTOMATION COMPLETED =====", true);
    }

    // Executes once after the entire TestNG suite
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Reporter.log("===== SUITE COMPLETED =====", true);
    }
}

   
