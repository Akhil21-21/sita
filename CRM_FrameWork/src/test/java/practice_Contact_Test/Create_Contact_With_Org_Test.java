	package practice_Contact_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Create_Contact_With_Org_Test {

	public static void main(String[] args) throws InterruptedException, IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\Commandata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);

		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		WebDriver d = null;
		if(BROWSER.equals("chrome")) { d = new ChromeDriver();}
		else if (BROWSER.equals("firefox")) { d = new FirefoxDriver();}
		else if (BROWSER.equals("edge")) { d = new EdgeDriver();}
		else { d= new FirefoxDriver(); }
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.get(URL);
		d.manage().window().maximize();
		 d.findElement(By.name("user_name")).sendKeys(USERNAME);
		 d.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 d.findElement(By.id("submitButton")).click();
		 d.findElement(By.linkText("Organizations")).click();
         
		 d.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		 
		 Random num = new Random();
		 int rnum = num.nextInt(1000);
		 

		 FileInputStream fis1 = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\test\\testScriptdat.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
          Sheet sh = wb.getSheet("Cont");
			Row row =sh.getRow(7);
			String orgname = row.getCell(2).toString() + rnum;
			String contactlastname = row.getCell(2).getStringCellValue();
			wb.close();
		 
		 d.findElement(By.name("accountname")).sendKeys(orgname);
		 d.findElement(By.name("industry")).click();
		 d.findElement(By.xpath("//option[@value='Manufacturing']")).click();
		 d.findElement(By.name("accounttype")).click();
		 d.findElement(By.xpath("//option[@value='Investor']")).click();
		 d.findElement(By.xpath("//td[@class='detailedViewHeader']/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
		 Thread.sleep(3000);
			// Navigate to contact module
			d.findElement(By.xpath("//a[contains(text(),'Organizations')]/parent::td/following-sibling::td/a[contains(.,'Contacts')]")).click();
	         Thread.sleep(5000);
			 d.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			 
			 
			 d.findElement(By.name("lastname")).sendKeys(contactlastname); 
			 d.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
			 
			 //switch to Childwindow
			Set<String> set= d.getWindowHandles();
			Iterator<String> it = set.iterator();
				while(it.hasNext()) {
					String windowid = it.next();
					d.switchTo().window(windowid);
				String actlurl=d.getCurrentUrl();
				if(actlurl.contains("module=Accounts")) {
					break;
				}}
				
			 d.findElement(By.id("search_txt")).sendKeys(orgname);
			 d.findElement(By.xpath("//input[@name='search']")).click();
			 d.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
			 
			 //Switch to parent window
				Set<String> set1= d.getWindowHandles();
				Iterator<String> it1 = set1.iterator();
					while(it1.hasNext()) {
						String windowid = it1.next();
						d.switchTo().window(windowid);
					String actlurl=d.getCurrentUrl();
					if(actlurl.contains("Contacts&action")) {
						break;
					}}
			 
			 d.findElement(By.xpath("//td[@class='detailedViewHeader']/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
		
				// Verify Head - msg - expected result
				String hedr = d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				System.out.println(hedr);
				System.out.println(contactlastname);
				if(hedr.contains(contactlastname)) {
					System.out.println(contactlastname + " -- Case is PASSED");
				} else {
					System.out.println(contactlastname + " -- Case is FAIL");
				}
			
				// Verify Header orgname is same ass expected result
				String act = d.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println(act);
				System.out.println(orgname);
				if(act.contains(orgname)) {
					System.out.println(orgname + " -- Case is PASSED");
				} else {
					System.out.println(orgname + " -- Case is FAIL");
				}
			
//		Actions a = new Actions(d); Thread.sleep(2000);
//		 a.moveToElement((WebElement) By.xpath("//img[@src='themes/softed/images/user.PNG']")).perform(); Thread.sleep(3000);
//		 d.findElement(By.linkText("Sign Out")).click();
		 
		 Thread.sleep(3000); d.quit();}}