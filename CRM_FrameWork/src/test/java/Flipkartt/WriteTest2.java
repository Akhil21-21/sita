
package Flipkartt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WriteTest2 {

    @Test
    public void FlipTest() throws IOException {

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(option);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // OPEN FLIPKART ONLY ONCE
        driver.get("https://www.flipkart.com");

        // CLOSE LOGIN POPUP
        List<WebElement> closeButtons = driver.findElements( By.xpath("//span[@role='button' and @class='b3wTlE']"));

        if (!closeButtons.isEmpty()) { closeButtons.get(0).click(); System.out.println("Popup closed"); }

        // MOBILE SEARCH
        System.out.println("========== MOBILE SEARCH STARTED ==========");

        WebElement search = wait.until( ExpectedConditions.elementToBeClickable(By.name("q")));

        search.sendKeys("mobiles");
        search.sendKeys(Keys.ENTER);

        // MOBILE PRICE BELOW 30000
        By mobilePriceLocator = By.xpath("//div[contains(@class,'hZ3P6w') and " + "translate(normalize-space(.),'₹,','') < 30000]");
        wait.until(ExpectedConditions.presenceOfElementLocated(mobilePriceLocator));

        // GET MOBILE RESULTS - NO SCROLLING
        List<WebElement> mobileElements = driver.findElements(mobilePriceLocator);
        List<String> mobileNames = new ArrayList<>();
        List<String> mobilePrices = new ArrayList<>();

        // EXTRACT MOBILE DETAILS
        for (WebElement priceElement : mobileElements) {
            List<WebElement> cards = priceElement.findElements(By.xpath("./ancestor::div[.//div[contains(@class,'RG5Slk')]][1]"));
            if (cards.isEmpty()) { continue; }

            List<WebElement> nameElements = cards.get(0).findElements(By.xpath(".//div[contains(@class,'RG5Slk')]"));

            if (nameElements.isEmpty()) { continue; }
            String name = nameElements.get(0).getText().trim();
            String price = priceElement.getText().trim();

            if (!name.isEmpty() && !price.isEmpty()) { mobileNames.add(name); mobilePrices.add(price);
                System.out.println("Mobile Sl.no: " + mobileNames.size());
                System.out.println("Mobile Name: " + name);
                System.out.println("Mobile Price: " + price);
                System.out.println("----------------------------"); } }

        System.out.println("Total mobiles extracted: " + mobileNames.size());
        System.out.println("========== MOBILE SEARCH ENDED ==========");

        // LAMP SEARCH
        System.out.println("========== LAMP SEARCH STARTED ==========");

        // REUSE SAME FLIPKART SESSION
        search = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        search.click();
        search.sendKeys(Keys.CONTROL, "a");
        search.sendKeys("lamp");
        search.sendKeys(Keys.ENTER);

        // LAMP NAME XPATH - PRICE BELOW 1500
        By lampNameLocator = By.xpath("//div[@class='hZ3P6w' and "+"translate(text(),'₹,','') < 1500]" + "/ancestor::div[@class='RGLWAk']" + "/descendant::a[@class='pIpigb']");
        wait.until(ExpectedConditions.presenceOfElementLocated(lampNameLocator));

        // GET LAMP RESULTS - NO SCROLLING
        List<WebElement> lampElements = driver.findElements(lampNameLocator);
        List<String> lampNames = new ArrayList<>();
        List<String> lampPrices = new ArrayList<>();

        // EXTRACT LAMP DETAILS
        for (WebElement lampElement : lampElements) { List<WebElement> cards = lampElement.findElements( By.xpath("./ancestor::div[@class='RGLWAk'][1]"));
            if (cards.isEmpty()) { continue;  }
            List<WebElement> priceElements = cards.get(0).findElements( By.xpath(".//div[@class='hZ3P6w']"));
            if (priceElements.isEmpty()) { continue;  }
            String name = lampElement.getText().trim();
            String price = priceElements.get(0).getText().trim();
            String numericPrice = price.replace("₹", "").replace(",", "") .replaceAll("[^0-9.]", "").trim();

            if (numericPrice.isEmpty()) {  continue;   }
            double amount = Double.parseDouble(numericPrice);
            if (amount < 1500 && !name.isEmpty()) {  lampNames.add(name); lampPrices.add(price);

                System.out.println("Lamp Sl.no: " + lampNames.size());
                System.out.println("Lamp Name: " + name);
                System.out.println("Lamp Price: " + price);
                System.out.println("----------------------------");   }  }

        System.out.println("Total lamps extracted: " + lampNames.size());
        System.out.println("========== LAMP SEARCH ENDED ==========");

        // WRITE BOTH TO EXCEL
        Flipkart excel = new Flipkart();
        excel.WriteMobiles(mobileNames, mobilePrices);
        excel.WriteLamps(lampNames, lampPrices);

        System.out.println("==================================");
        System.out.println("BOTH SHEETS UPDATED SUCCESSFULLY");
        System.out.println("Total mobiles: " + mobileNames.size());
        System.out.println("Total lamps: " + lampNames.size());
        System.out.println("==================================");

        // CLOSE BROWSER AFTER BOTH SEARCHES
        driver.quit();}}
