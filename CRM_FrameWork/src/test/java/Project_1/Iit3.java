package Project_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Iit3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver d = new ChromeDriver();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); Thread.sleep(3000);
	
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click();
	    d.findElement(By.linkText("Employees")).click();
	    d.findElement(By.xpath("//*[@id=\"view-container\"]/div[7]/div[1]/table/tbody/tr[1]/td[16]/div/button[1]")).click(); 
		 //    For Logout
	    d.findElement(By.xpath("//*[@id=\"mainHeader\"]/div[2]/div[2]/button/div/div[1]/img")).click();	    
	    d.findElement(By.linkText("Logout")).click();Thread.sleep(3000);
          d.quit();
	}

}
