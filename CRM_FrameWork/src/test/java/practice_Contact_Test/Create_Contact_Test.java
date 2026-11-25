package practice_Contact_Test;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cast.crm.generic.fileutility.ExcelUtility;
import com.cast.crm.generic.fileutility.FileUtility;

public class Create_Contact_Test {

	public static void main(String[] args) throws InterruptedException, IOException {

			//Create Object for File and Excel
			
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
//			FileInputStream fis = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\Commandata.properties");
//			Properties pObj = new Properties();
//			pObj.load(fis);

			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
			
		
			
			 Random num = new Random();
			 int rnum = num.nextInt(1000);
			
		 String lastname = eLib.getDataFromExcel("Cont", 1, 2) + rnum;
			 
//			 Workbook wb = WorkbookFactory.create(fis1);
//	          Sheet sh = wb.getSheet("Cont");
//				Row row =sh.getRow(1);
//				String lastname = row.getCell(2).toString();
//				wb.close();
				
				WebDriver d = null;
				
				if(BROWSER.equals("chrome")) { d = new ChromeDriver();}
				else if (BROWSER.equals("firefox")) { d = new FirefoxDriver();}
				else if (BROWSER.equals("edge")) { d = new EdgeDriver();}
				else { d= new FirefoxDriver(); }
				
				d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				d.get(URL); d.manage().window().maximize();
				 d.findElement(By.name("user_name")).sendKeys(USERNAME);
				 d.findElement(By.name("user_password")).sendKeys(PASSWORD);
				 d.findElement(By.id("submitButton")).click();
				 d.findElement(By.xpath("//a[contains(text(),'Organizations')]/parent::td/following-sibling::td/a[contains(., 'Contacts')]")).click();
		         Thread.sleep(2000);
				 d.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				 
				 
				 d.findElement(By.name("lastname")).sendKeys(lastname); 
				 d.findElement(By.xpath("//td[@class='detailedViewHeader']/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
			
				// Verify Head - msg - expected result
//			String hedr =	d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//			if(hedr.contains(orgname)) {
//				System.out.println(orgname + " -- Case is PASSED");
//			} else {
//				System.out.println(orgname + " -- Case is FAIL");
//			}
//			
			// Verify Header orgname is same ass expected result
//			String act = d.findElement(By.id("dtlview_Organization Name")).getText();
//			if(act.equals(orgname)) {
//				System.out.println(orgname + " -- Case is PASSED");
//			} else {
//				System.out.println(orgname + " -- Case is FAIL");
//			}
			
			String actlastnm = d.findElement(By.id("dtlview_Last Name")).getText();
			System.out.println(actlastnm);
			System.out.println(lastname);
			if(actlastnm.equals(lastname)) {
				System.out.println(lastname + " -- Case is PASSED");
			} else {
				System.out.println(lastname + " -- Case is FAIL");
					
			
//			 d.findElement(By.name("industry")).click();
//				 
//				 d.findElement(By.xpath("//option[@value='Manufacturing']")).click();
//				 
//			dtlview_Phone	 
//				 d.findElement(By.name("accounttype")).click();
//				 
//				 d.findElement(By.xpath("//option[@value='Investor']")).click();

				
				d.quit();
			}}}	
				
				