package Project_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Skt2 {

	public static void main(String[] args)   throws Exception {
		// TODO Auto-generated method stub
		WebDriver d = new ChromeDriver();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); 
	    System.out.println("Login successful");
	    Thread.sleep(3000); d.quit();
	}}