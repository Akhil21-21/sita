package practice_Org_Test;

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
import org.openqa.selenium.support.ui.Select;

public class Create_Organization_Test_with_Industry {

	public static void main(String[] args) throws IOException {
	
		
		FileInputStream fis = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\Commandata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
	
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

	
		
		 Random num = new Random();
		 int rnum = num.nextInt(1000);
		
		 FileInputStream fis1 = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\test\\testScriptdat.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
          Sheet sh = wb.getSheet("organ");
			Row row =sh.getRow(4);
			String orgname = row.getCell(2).toString() + rnum;
			String ind = row.getCell(3).toString();
			String type = row.getCell(4).toString();
			wb.close();
			
			WebDriver d = null;
			
			if(BROWSER.equals("chrome")) { d = new ChromeDriver();}
			else if (BROWSER.equals("firefox")) { d = new FirefoxDriver();}
			else if (BROWSER.equals("edge")) { d = new EdgeDriver();}
			else { d= new FirefoxDriver(); }
			
			d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			d.get(URL); 	d.manage().window().maximize();
			 d.findElement(By.name("user_name")).sendKeys(USERNAME);
			 d.findElement(By.name("user_password")).sendKeys(PASSWORD);
			 d.findElement(By.id("submitButton")).click();
			 d.findElement(By.linkText("Organizations")).click();
	         
			 d.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			 
			 
			 d.findElement(By.name("accountname")).sendKeys(orgname + rnum);
			 d.findElement(By.xpath("//option[@value='Manufacturing']")).click();
			 d.findElement(By.xpath("//option[@value='Investor']")).click();
			 
			 
			 d.findElement(By.xpath("//td[@class='detailedViewHeader']/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
//		 WebElement wbsel = d.findElement(By.name("industry"));
//			      Select sel = new Select(wbsel);
//			 sel.selectByVisibleText(ind);
//			
//			WebElement wbsl = d.findElement(By.name("accounttype"));
//			  Select se = new Select(wbsl);
//				 se.selectByVisibleText(type);  
//			      
//			 
	
		//verify the industry and type info
		String actind = d.findElement(By.id("dtlview_Industry")).getText();
		System.out.println(actind);
		System.out.println(ind);
		if(actind.equals(ind)) {
			System.out.println(ind + " -- This Case has been verified . So it is PASSED");
		} else {
			System.out.println(ind + " -- This Case has been verified . So it is FAIL");
		}
		
		String acttype = d.findElement(By.id("mouseArea_Type")).getText();
		System.out.println(type);
		System.out.println(acttype);
		if(acttype.equals(type)) {
			System.out.println(type + " -- This Case has been verified . So it is PASSED");
		} else {
			System.out.println(type + " -- This Case has been verified . So it is FAIL");
		}
		
		
//		 d.findElement(By.name("industry")).click();
//			 
//			 ;
//			 
//			 
//			 d.findElement(By.name("accounttype")).click();
//			 
//			

		
		d.quit();
		
	}
		
	}


// Verify Head - msg - expected result
//String hedr =	d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//if(hedr.contains(orgname)) {
//	System.out.println(orgname + " -- Case is PASSED");
//} else {
//	System.out.println(orgname + " -- Case is FAIL");
//}
//
//// Verify Header orgname is same ass expected result
//String act = d.findElement(By.id("dtlview_Organization Name")).getText();
//if(act.equals(orgname)) {
//	System.out.println(orgname + " -- Case is PASSED");
//} else {
//	System.out.println(orgname + " -- Case is FAIL");
//}
