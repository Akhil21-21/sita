package Flipkartt;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class WriteTest {
     
    	@Test
    	public void FlipTest() throws IOException { {
    	
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Close login popup
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@role='button' and @class='b3wTlE']")));  close.click();
        System.out.println("Popup closed");
        
        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        search.sendKeys("mobiles");
        search.sendKeys(Keys.ENTER);

        // LOCATE MOBILE PRICES BELOW 30000

        By priceLocator = By.xpath(
                "//div[contains(@class,'hZ3P6w') and " +
                "translate(normalize-space(.),'₹,','') < 30000]"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(priceLocator)
        );

        List<WebElement> prices =
                driver.findElements(priceLocator);

        System.out.println("Total matching prices: " + prices.size());

        // CREATE LISTS TO STORE MOBILE DETAILS

        List<String> mobileNames = new ArrayList<>();
        List<String> mobilePrices = new ArrayList<>();

        // EXTRACT MOBILE NAME AND PRICE

        for (WebElement priceElement : prices) {

            WebElement mobileCard = priceElement.findElement(
                    By.xpath(
                        "./ancestor::div[contains(@class,'ZFwe0M') and contains(@class,'row')]"
                    )
            );

            String mobileName = mobileCard.findElement(
                    By.xpath(".//div[contains(@class,'RG5Slk')]")
            ).getText();

            String mobilePrice = priceElement.getText();

            mobileNames.add(mobileName);
            mobilePrices.add(mobilePrice);

            System.out.println("Mobile Name: " + mobileName);
            System.out.println("Mobile Price: " + mobilePrice);
            System.out.println("--------------------------");
        }

        // WRITE ALL DATA INTO EXCEL
       
//        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
//        search.sendKeys("MOTOROLA g37 power");
//        search.sendKeys(Keys.ENTER);
//        By mobileLocator = By.xpath("//div[contains(text(),'MOTOROLA g37 power')]");
//        WebElement mobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileLocator));
//        String mobileName = mobileElement.getText();
//        String price = mobileElement.findElement(By.xpath("./ancestor::div[contains(@class,'ZFwe0M')]//div[contains(@class,'hZ3P6w')]")).getText();
//        System.out.println("MOBILE NAME: " + mobileName);
//        System.out.println("MOBILE PRICE: " + price);
   //      // Lamp
//        search = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
//        search.click();
//        search.sendKeys(Keys.CONTROL + "a");
//        search.sendKeys(Keys.BACK_SPACE);
//        search.sendKeys("WEIRD WOLF table lamp");
//        search.sendKeys(Keys.ENTER);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'pIpigb')]")));
//        By lampLocator = By.xpath("//a[contains(@class,'pIpigb') and contains(@title,'WEIRD WOLF')]");
//
//        java.util.List<WebElement> lampResults = driver.findElements(lampLocator);
//        if (lampResults.isEmpty()) {  System.out.println("WEIRD WOLF lamp not found in Flipkart search results."); driver.quit(); return;}
//        WebElement lampElement = lampResults.get(0);
//        String lamp = lampElement.getAttribute("title");
//        WebElement lampCard = lampElement.findElement(By.xpath("./ancestor::div[contains(@class,'RGLWAk')]"));
//        String pricee = lampCard.findElement(By.xpath(".//div[contains(@class,'hZ3P6w')]")).getText();
//        System.out.println("LAMP NAME: " + lamp);
//        System.out.println("LAMP PRICE: " + pricee);

        // WRITE DATA INTO EXCEL
//        Flipkart1 f = new Flipkart1();
//        // Mobile - Row 2
//        f.WriteData(1, 1, mobileName);
//        f.WriteData(1, 2, price);
//        // Lamp - Row 5
//        f.WriteData(4, 1, lamp);
//        f.WriteData(4, 2, pricee);
//
//        System.out.println("Mobile data written into Excel");
//        System.out.println("Lamp data written into Excel");
//        System.out.println("All data written successfully");
//        driver.quit();
        
     // WRITE ALL DATA INTO EXCEL

        Flipkart1 f = new Flipkart1();

        f.WriteMobiles(mobileNames, mobilePrices);

        System.out.println("All mobile details written into Excel");

        driver.quit(); 
        
    }
}}
