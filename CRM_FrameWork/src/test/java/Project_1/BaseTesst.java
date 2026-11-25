
package Project_1;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseTesst {

    protected static WebDriver d;
    protected static WebDriverWait wait;

    private boolean loggedIn = false;

    @BeforeSuite(alwaysRun = true)
    public void launchBrowser() {

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

        if (d == null) {
            d = new ChromeDriver(options);
        }

        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

        wait = new WebDriverWait(d, Duration.ofSeconds(15));

        System.out.println("Chrome launched successfully");
        Reporter.log("Chrome launched successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void login() {

        loggedIn = false;

        d.get("https://hr.demo.horilla.com/login/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("username"))).sendKeys("admin");

        d.findElement(By.id("password")).sendKeys("admin");

        d.findElement(By.xpath(
            "/html/body/div[2]/div/form/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("sidebar")));

        loggedIn = true;

        System.out.println("Login successful");
        Reporter.log("Login successful");
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        if (d == null || !loggedIn) {return;}
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//*[@id='mainHeader']/div[2]/div[2]/button/div/div[1]/img") )).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            loggedIn = false;
            System.out.println("Logout successful");
            Reporter.log("Logout successful");
        } catch (Exception e) {  System.out.println("Logout error: " + e.getMessage()); } }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        if (d != null) { d.quit(); d = null; wait = null; System.out.println("Chrome closed successfully");}
        System.out.println("========== SUITE COMPLETED ==========");
    }
}
