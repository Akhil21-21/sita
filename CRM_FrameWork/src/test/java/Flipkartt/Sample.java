
package Flipkartt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Sample {

    WebDriver d = null;

    @Test
    public void Test1() throws Exception {

        String browser = System.getProperty("browser");
        String url = System.getProperty("url");

        if (browser.equalsIgnoreCase("chrome"))
            d = new ChromeDriver();
        else if (browser.equalsIgnoreCase("firefox"))
            d = new FirefoxDriver();
        else if (browser.equalsIgnoreCase("edge"))
            d = new EdgeDriver();
        else
            throw new IllegalArgumentException("Invalid browser");

        System.out.println(url);

        d.get(url);
        Thread.sleep(3000);
        d.quit();
    }
}
