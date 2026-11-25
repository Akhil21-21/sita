package Project_1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Skt1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver d = new ChromeDriver();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    d.get("https://hr.demo.horilla.com/login/"); Thread.sleep(3000); d.quit();
	}

}
