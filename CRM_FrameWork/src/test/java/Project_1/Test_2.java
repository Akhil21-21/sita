package Project_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test_2 {

	public static void main(String[] args) throws InterruptedException {

		WebDriver d = new ChromeDriver();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); Thread.sleep(3000);
	   
	    // For Logout
	 //   d.findElement(By.xpath("//*[@id=\"mainHeader\"]/div[2]/div[2]/button/div/div[1]/img")).click();	    
	 //   d.findElement(By.linkText("Logout")).click();
	    
	// For new Employee Creation
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click();
	    d.findElement(By.linkText("Employees")).click();  Thread.sleep(3000);
	    d.findElement(By.xpath("//*[@id=\"employeeListNavBar\"]/div/div[1]/div/a/img")).click();
//	    d.findElement(By.className("badge_id")).sendKeys("PEP126");
	    Thread.sleep(2000);
	    d.findElement(By.id("id_employee_first_name")).sendKeys("Nagaraju");
	    d.findElement(By.id("id_employee_last_name")).sendKeys("V");
	    int n = 8;
	    String AlphaNumString = "abcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	        int index = (int) (AlphaNumString.length() * Math.random());
	        sb.append(AlphaNumString.charAt(index));
	    } 	String email = sb.toString() + "@test.in"; d.findElement(By.id("id_email")).sendKeys(email); System.out.println("Generated Email: " + email);
	    
	    d.findElement(By.id("id_phone")).sendKeys("1001100115");
	//    d.findElement(By.linkText("Male")).click();
	    d.findElement(By.id("id_address")).sendKeys("Dharmavaram , Hindupur , Andhra Pradesh , 555222");
	    d.findElement(By.id("select2-id_country-container")).click();
	//    d.findElement(By.xpath("//*[@id=\"select2-id_country-container\"]")).click();
	   d.findElement(By.xpath("//*[@id=\"id_country\"]/option[106]")).click();
	   Thread.sleep(3000);
	   d.findElement(By.xpath("//*[@id=\"id_state\"]/option[3]")).click();
	    d.findElement(By.id("id_city")).sendKeys("Hindupur");
	    d.findElement(By.id("id_zip")).sendKeys("555222");
	    
	Actions a = new Actions(d);
	  WebElement qualification = d.findElement(By.id("id_qualification"));
	    a.scrollToElement(qualification).click().build().perform();
	    
	    d.findElement(By.id("dob")).sendKeys("21-02-1997");
	   d.findElement(By.id("id_qualification")).sendKeys("BE-Mechanical"); Thread.sleep(2000);
	//    d.findElement(By.className("id_experience")).sendKeys("3");  
	    d.findElement(By.id("id_emergency_contact")).sendKeys("1234512345");
	    d.findElement(By.id("id_emergency_contact_name")).sendKeys("ABCD");
	    d.findElement(By.id("id_emergency_contact_relation")).sendKeys("Cousin"); Thread.sleep(2000);
	    d.findElement(By.xpath("//button[@class=\"px-6 py-2 bg-primary-600 rounded-lg text-sm font-medium text-white hover:bg-primary-700 transition-colors shadow-sm\"]")).click();
	    Thread.sleep(3000);
	    a.moveToElement(d.findElement(By.id("step-work"))).click().build().perform();  Thread.sleep(2000);
		
	//     a.moveToElement(d.findElement(By.xpath("//*[@id=\"select2-id_job_role_id-container\"]"))).click().build().perform(); Thread.sleep(1000);
//	     WebElement jbe = d.findElement(By.linkText("Junior Backend Engineer"));
//		    a.scrollToElement(jbe).click().build().perform();
//	     
//	     d.findElement(By.linkText("Junior Backend Engineer")).click();
//		a.doubleClick(jbe);
		
		Thread.sleep(5000);d.quit();
		
	}

}
