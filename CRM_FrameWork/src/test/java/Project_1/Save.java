package Project_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Save {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions option = new ChromeOptions();

		option.addArguments("--disable-notifications");
		option.setExperimentalOption("prefs", java.util.Map.of("credentials_enable_service", false,"profile.password_manager_enabled", false));

		WebDriver d = new ChromeDriver(option);
		d.manage().window().maximize(); System.out.println("Browser Launched");
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); Thread.sleep(3000); System.out.println("Login done");
	   

	    
	// For new Employee Creation
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click(); Thread.sleep(3000);
	    d.findElement(By.linkText("Employees")).click();  Thread.sleep(2000);
	    d.findElement(By.xpath("//*[@id=\"employeeListNavBar\"]/div/div[1]/div/a/img")).click();
	    Thread.sleep(2000);
  System.out.println("Going to employee module");
  
          Random rn = new Random();
  int ra = 100 + rn.nextInt(900);
  String badgeId = "PEP" + ra;
  d.findElement(By.id("id_badge_id")).click();
  d.findElement(By.id("id_badge_id")).clear();
  d.findElement(By.id("id_badge_id")).sendKeys(badgeId); System.out.println("The generated Badge ID is "+badgeId);
  Random random = new Random();      
  String firstName = "";
         String lastName = "";
          firstName += (char)('A' + random.nextInt(26));
          for (int i = 0; i < 6; i++) {firstName += (char)('a' + random.nextInt(26));}
          lastName += (char)('A' + random.nextInt(26));
          d.findElement(By.id("id_employee_first_name")).sendKeys(firstName);
          d.findElement(By.id("id_employee_last_name")).sendKeys(lastName);

System.out.println(firstName);
System.out.println(lastName);
	    
       //EMAIL ID
	    int n = 8;
	    String AlphaNumString = "abcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {  int index = (int) (AlphaNumString.length() * Math.random());
	        sb.append(AlphaNumString.charAt(index));
	    } 	String email = sb.toString() + "@test.in"; d.findElement(By.id("id_email")).sendKeys(email);

	    System.out.println("Generated Email: " + email);
	    
	    Random r = new Random();
	    String phone = "";
	    phone = phone.concat(String.valueOf(r.nextInt(9) + 1));
	    for (int i = 1; i < 10; i++) { phone = phone.concat(String.valueOf(r.nextInt(10))); }
	    d.findElement(By.id("id_phone")).sendKeys(phone);
	    System.out.println(phone);
	    
	   d.findElement(By.id("id_address")).sendKeys("Dharmavaram , Hindupur , Andhra Pradesh , 555222");
	    d.findElement(By.id("select2-id_country-container")).click();
	   d.findElement(By.xpath("//*[@id=\"id_country\"]/option[106]")).click();
	   d.findElement(By.xpath("//*[@id=\"id_state\"]/option[3]")).click();
	    d.findElement(By.id("id_city")).sendKeys("Hindupur"); 
	    d.findElement(By.id("id_zip")).sendKeys("555222");
	    
	Actions a = new Actions(d);
	  WebElement qualification = d.findElement(By.id("id_qualification"));
	    a.scrollToElement(qualification).click().build().perform();
	    
	    d.findElement(By.id("dob")).sendKeys("21-02-1997");
	   d.findElement(By.id("id_qualification")).sendKeys("BE-Mechanical");
	    d.findElement(By.id("id_experience")).sendKeys("3");  
	    d.findElement(By.id("select2-id_marital_status-container")).click();
	    d.findElement(By.id("id_children")).sendKeys("1");
	    d.findElement(By.id("id_emergency_contact")).sendKeys("1234512345");
	    d.findElement(By.id("id_emergency_contact_name")).sendKeys("King");
	    d.findElement(By.id("id_emergency_contact_relation")).sendKeys("Friend");
	    d.findElement(By.xpath("//button[@class=\"px-6 py-2 bg-primary-600 rounded-lg text-sm font-medium text-white hover:bg-primary-700 transition-colors shadow-sm\"]")).click();
	    System.out.println("1st page done");
	    
	    // To check the name in organization
//	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click();
//	    d.findElement(By.linkText("Employees")).click();
//	    d.findElement(By.xpath("//*[@id=\"filterForm\"]/div[1]/input[1]")).sendKeys("Dheeraj");
	    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

	 // Wait until the success notification disappears
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oh-alert.oh-alert--animated.oh-alert--success")));
	 
	 // Wait until the Work tab is clickable
	 WebElement workTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("step-work")) );  workTab.click();
	
	    d.findElement(By.xpath("//label[text()='Department']/parent::div/descendant::span[@class='select2-selection__arrow']/b")).click();
        d.findElement(By.xpath("//li[text()='Engineering']")).click(); Thread.sleep(3000);
        
        WebDriverWait wait1 = new WebDriverWait(d, Duration.ofSeconds(10));

     // Job Position
     wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_job_position_id-container"))).click();
     wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Software Engineer']"))).click();
     System.out.println("Job Position selected successfully"); Thread.sleep(3000);
     
     // Job Role
     wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_job_role_id-container"))).click();
     wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Software Engineer']"))).click();
     System.out.println("Job Role selected successfully"); Thread.sleep(3000);

     
      Thread.sleep(2000);
      	
//      d.findElement(By.xpath("//*[@id=\"id_work_type_id\"]/option[2]")).click(); System.out.println("Work From Office");
//      Thread.sleep(3000);
//      d.findElement(By.id("select2-id_employee_type_id-container")).click();  Thread.sleep(2000);
//      d.findElement(By.xpath("//*[@id=\"select2-id_employee_type_id-result-sj5y-1\"]")).click(); System.out.println("Full Time");
//      d.findElement(By.id("select2-id_shift_id-container")).click();
//      d.findElement(By.id("select2-id_shift_id-result-2vd6-1")).click(); System.out.println("Regular");
        
   // Work From Office
      d.findElement(By.xpath("//*[@id='id_work_type_id']/option[2]")).click();
      System.out.println("Work From Office");  Thread.sleep(3000);
      ////li[@class='select2-results__option select2-results__option--highlighted' and @id= 'select2-id_employee_type_id-result-26pt-1']
      
      // Employee Type
      wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_employee_type_id-container"))).click();
      wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='Full-Time']"))).click();
    		System.out.println("Full Time selected successfully");
      
      // Shift
      wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_shift_id-container"))).click();  Thread.sleep(3000);
      wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Regular Shift']"))).click();
      System.out.println("Regular shift selected successfully");  Thread.sleep(3000);
      
        // Reporting and location
        d.findElement(By.id("id_location")).sendKeys("Bangalore"); Thread.sleep(2000);
        d.findElement(By.id("id_mobile")).sendKeys("1231231231");
        
        WebDriverWait wait3 = new WebDriverWait(d, Duration.ofSeconds(15));

        WebElement bankTab = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("step-bank")));

        // Scroll the Bank tab into the visible area
        ((org.openqa.selenium.JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView({block:'center'});", bankTab);
        System.out.println("Work part Ends");
        // Wait briefly for the page to settle and the tab to be clickable
        wait3.until(ExpectedConditions.elementToBeClickable(By.id("step-bank")));

        bankTab.click();   System.out.println("Bank Part Starts");
        d.findElement(By.id("id_bank_name")).sendKeys("Demo Bank");
        d.findElement(By.id("id_account_number")).sendKeys("12312345645965");
        d.findElement(By.id("id_branch")).sendKeys("RRR Branch"); Thread.sleep(3000);
        d.findElement(By.id("id_any_other_code1")).sendKeys("#1");
        d.findElement(By.id("id_any_other_code2")).sendKeys("#1");Thread.sleep(3000); 
        d.findElement(By.xpath("(//textarea[@id = 'id_address'])[2]")).sendKeys("VV Puram , Bangalore"); System.out.println("The Address is getting print");
        Thread.sleep(3000);  
       d.findElement(By.xpath("(//*[@id=\"id_city\"])[2]")).sendKeys("Bangalore");
        
        
        System.out.println("Bank Part Ends");
	    //For Logout
	    d.findElement(By.xpath("//*[@id=\"mainHeader\"]/div[2]/div[2]/button/div/div[1]/img")).click();	    
	    d.findElement(By.linkText("Logout")).click();
        
	    Thread.sleep(2000);d.quit();}


}
