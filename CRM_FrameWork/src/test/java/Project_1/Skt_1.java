package Project_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Skt_1 {
	
	
	@Test
	public void LoginTest() throws InterruptedException {
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); 
	    System.out.println("Just login and logout");Thread.sleep(3000); d.quit();
	}

	@Test
	public void EntEmpTest() throws InterruptedException {
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click();
	    System.out.println("Employee Module");
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click(); Thread.sleep(2000);
	    d.findElement(By.linkText("Employees")).click();  Thread.sleep(2000);
	   System.out.println(d.getCurrentUrl());
	    d.quit();
	}
	
	@Test(priority =-1)
	public void PayrollTest() throws InterruptedException {
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click();
	    System.out.println("Leave Module");
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[3]/button/span")).click(); Thread.sleep(2000);
	    d.findElement(By.linkText("Public Holidays")).click();  Thread.sleep(2000);
	   System.out.println(d.getCurrentUrl());
	    d.quit();
	}
	

	
	
}
