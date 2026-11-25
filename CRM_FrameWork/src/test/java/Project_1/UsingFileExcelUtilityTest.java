package Project_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.cast.crm.generic.fileutility.ExcelUtility;

public class UsingFileExcelUtilityTest {

	WebDriver d=null;
	
	@Test
	public void Emptest() throws InterruptedException, IOException {

	FileInputStream fis = new FileInputStream("C:/Users/akhil/Desktop/horilla/pp/horilla.properties");Properties pobj = new Properties();pobj.load(fis);
		
		System.out.println(pobj.getProperty("url"));
		System.out.println(pobj.getProperty("browser")); 
		System.out.println(pobj.getProperty("username")); 
		System.out.println(pobj.getProperty("password"));
				
		String URL = pobj.getProperty("url");
		String BROWSER  = pobj.getProperty("browser");
		String USERNAME =	pobj.getProperty("username");
		String PASSWORD=	pobj.getProperty("password");
		
        WebDriver d = null;
		if(BROWSER.equals("chrome")) { d = new ChromeDriver();} else if (BROWSER.equals("firefox")) { d = new FirefoxDriver();} else if (BROWSER.equals("edge")) { d = new EdgeDriver();} else { d= new FirefoxDriver(); }
		d.get(URL);
		   d.findElement(By.id("username")).clear();
		    d.findElement(By.id("username")).sendKeys(USERNAME);
		    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD);
		    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); Thread.sleep(3000); System.out.println("Login done");
		
	//		 For new Employee Creation
		    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click(); Thread.sleep(2000);
		    d.findElement(By.linkText("Employees")).click();  Thread.sleep(2000);
		    d.findElement(By.xpath("//*[@id=\"employeeListNavBar\"]/div/div[1]/div/a/img")).click();
		    Thread.sleep(2000);
	  System.out.println("Going to employee module");
	          Random ran = new Random(); String chara = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" ; 


           StringBuilder sb = new StringBuilder();
           for(int i = 0; i < 5; i++) {int index = ran.nextInt(chara.length()); sb.append(chara.charAt(index));}
            String randomString = sb.toString();
            System.out.println(randomString);
	          
           FileInputStream fis1 = new FileInputStream("C:/Users/akhil/Desktop/horilla/horillatestdata.xlsx");	       
           Workbook wb =  WorkbookFactory.create(fis1);
	        System.out.println("File exists: " + fis1);
	      Sheet sh = wb.getSheet("H1");
	       Row row = sh.getRow(1);	
	       String FN = row.getCell(1).toString()+randomString;
	       d.findElement(By.id("id_employee_first_name")).sendKeys(FN);
	       String LN = row.getCell(2).toString()+randomString;
	       d.findElement(By.id("id_employee_last_name")).sendKeys(LN);
	        System.out.println(FN); System.out.println(LN);
		    String ADD = row.getCell(3).toString();
		    String Cty = row.getCell(4).toString();
		    String Z = row.getCell(5).toString();

//	       EMAIL ID
		    int n = 8;
		    String AlphaNumString = "abcdefghijklmnopqrstuvwxyz0123456789";
		    StringBuilder s = new StringBuilder(n);
		    for (int i = 0; i < n; i++) {  int index = (int) (AlphaNumString.length() * Math.random());
		        s.append(AlphaNumString.charAt(index));
		    } 	String email = sb.toString() + "@test.in"; d.findElement(By.id("id_email")).sendKeys(email);
		    System.out.println("Generated Email: " + email);
               //	Phone NO	    
		    Random r = new Random();
		    String phone = "";
		    phone = phone.concat(String.valueOf(r.nextInt(9) + 1));
		    for (int i = 1; i < 10; i++) { phone = phone.concat(String.valueOf(r.nextInt(10))); }
		    d.findElement(By.id("id_phone")).sendKeys(phone);
		    System.out.println(phone);
		   d.findElement(By.id("id_address")).sendKeys(ADD); System.out.println(ADD); Thread.sleep(2000);
		    d.findElement(By.id("select2-id_country-container")).click(); Thread.sleep(2000);
		    d.findElement(By.xpath("//*[@id=\"id_country\"]/option[106]")).click();		    
		    System.out.println("INDIA Selected");
		 //   d.findElement(By.xpath("//*[@id=\"id_country\"]/option[106]")).click(); 

		   d.findElement(By.xpath("//*[@id=\"id_state\"]/option[3]")).click();
		   System.out.println(Cty); 
		    d.findElement(By.id("id_zip")).sendKeys(Z); System.out.println(Z);
		    
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
		    Thread.sleep(2000);
	}
		        
		    }
	



