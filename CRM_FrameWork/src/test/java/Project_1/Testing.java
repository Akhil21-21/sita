package Project_1;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Testing {

	public static void main(String[] args) throws InterruptedException {

		WebDriver d = new ChromeDriver();
		//d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.get("https://testautomationpractice.blogspot.com/");
		Thread.sleep(2000);
		System.err.println("The AUTOMATION PRACTICE LINK HAS completed.");
		d.findElement(By.id("name")).sendKeys("Akhil");
		d.findElement(By.id("email")).sendKeys("Abcd@test.in");
		d.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("7897897894");
 		d.findElement(By.id("textarea")).sendKeys("Nagarabhavi , Bangalore , Karnataka - 5660072");
		d.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		d.findElement(By.xpath("(//input[@type='checkbox'])[7]")).click();
		d.findElement(By.xpath("//*[@id=\"country\"]/option[10]")).click();
		d.findElement(By.xpath("//*[@id=\"colors\"]/option[7]")).click();
		d.findElement(By.xpath("//*[@id=\"animals\"]/option[5]")).click();
		d.findElement(By.xpath("//*[@id=\"animals\"]/option[5]")).click();
		
		
		
 		Thread.sleep(3000);
 	    System.out.println("The Data Entry form is completed");
 		
 		Actions ac = new Actions(d);
 		//Price slider
		
 	    WebElement maxs = d.findElement(By.xpath("//*[@id=\"slider-range\"]/span[1]"));
 	    WebElement mins = d.findElement(By.xpath("//*[@id=\"slider-range\"]/span[2]"));
 	    ac.dragAndDropBy(mins, 50, 0);
 	    Thread.sleep(3000);
 	   ac.dragAndDropBy(maxs, 50, 0);
				WebElement sc = d.findElement(By.id("draggable"));
				WebElement tgt = d.findElement(By.id("droppable"));
				ac.dragAndDrop(sc, tgt).build().perform();	
				System.out.println("The Drag and Drop action is completed");
 		
 		Thread.sleep(3000);
	//	date selection -- 2
		d.findElement(By.id("txtDate")).click();
		d.findElement(By.linkText("10")).click();
		Thread.sleep(3000);
		// date selection -- 3
		d.findElement(By.id("start-date")).sendKeys("21-02-1997");
		d.findElement(By.id("end-date")).sendKeys("11-10-2026");
		d.findElement(By.xpath("//*[@id=\"post-body-1307673142697428135\"]/div[8]/button")).click();
		System.out.println("The Entry of datess of date picker 2 and date picker 3 is completed");
		Thread.sleep(3000);
		//Selection of broken link--408
		d.findElement(By.xpath("//*[@id=\"broken-links\"]/a[5]")).click();
		Thread.sleep(3000);
		d.navigate().back();
		System.out.println("The Link of Errorcode408 is Working");
		//selection of dell
		d.findElement(By.id("lenovo")).click();
		Thread.sleep(3000);
		d.navigate().back();
		System.out.println("The Link of Lenovo is Working");
		
		//static table
		d.findElement(By.xpath("//*[@id=\"HTML1\"]/div[1]/table/tbody/tr[6]/td[1]")).getText();
		
				
		
		// selection of smartwatch checkbox
		d.findElement(By.linkText("3")).click();
		d.findElement(By.xpath("//*[@id=\"productTable\"]/tbody/tr[3]/td[4]/input")).click();
		System.err.println("The Smartwatch checkbox has been clicked");
		Thread.sleep(3000);
		System.err.println("The Portable Charger checkbox has been clicked");
		d.findElement(By.linkText("1")).click();
		d.findElement(By.xpath("//*[@id=\"productTable\"]/tbody/tr[4]/td[4]/input")).click();
		System.out.println("The Navigation Table checkbox is completed");
		Thread.sleep(3000);
		d.quit();
	}

}
