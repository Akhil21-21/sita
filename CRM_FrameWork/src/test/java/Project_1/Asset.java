package Project_1;

import org.testng.annotations.Test;
import org.testng.Reporter;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Asset extends BaseTest {

    @Test (dependsOnMethods = "Project_1.EmpRegistration.employeeRegistration")
    public void assetManagement() throws InterruptedException {
    	 Actions a = new Actions(d);
    	   Random random = new Random();
           StringBuilder firstNameBuilder = new StringBuilder();
           firstNameBuilder.append((char) ('A' + random.nextInt(26)));

            for (int i = 0; i < 6; i++) { firstNameBuilder.append((char) ('a' + random.nextInt(26)));}
           String firstName = firstNameBuilder.toString();
           String lastName = String.valueOf((char) ('A' + random.nextInt(26)));
           String employeeNameForLeave = firstName;
   	       
             d.findElement(By.id("id_employee_first_name")).sendKeys(firstName); Reporter.log(firstName,true);
             d.findElement(By.id("id_employee_last_name")).sendKeys(lastName);   Reporter.log(lastName,true);
             
             Random rn = new Random();
  	       int ra = 100 + rn.nextInt(900);
  	       String badgeId = "PEP" + ra;
  	       d.findElement(By.id("id_badge_id")).click();
  	       d.findElement(By.id("id_badge_id")).clear();
  	       d.findElement(By.id("id_badge_id")).sendKeys(badgeId); 
    	
  	       
  	       
        // Move your existing code from lines 324–465 here.
    	d.get("https://hr.demo.horilla.com/employee/employee-view/");
        WebDriverWait wait4 = new WebDriverWait(d, Duration.ofSeconds(15));	
        By employeeRow = By.xpath(
        	    "//table//tbody/tr[contains(.,'" + BaseTest.firstName +
        	    "') and contains(.,'" + BaseTest.badgeId + "')]"
        	);
        WebElement row = wait4.until(
        	    ExpectedConditions.visibilityOfElementLocated(employeeRow)
        	);
       
    	WebElement searchBoxx = wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='filterForm']/div[1]/input[1]")));
    	searchBoxx.clear(); 	searchBoxx.sendKeys(firstName);

    	// Find the employee again
    	row = wait4.until(ExpectedConditions.visibilityOfElementLocated(employeeRow));
    	Reporter.log("Employee found again after contract update",true);
    	 // Find the action buttons in the last column of that employee's row
    	 java.util.List<WebElement> buttons = row.findElements(By.xpath("./td[last()]//button"));

    	 System.out.println("Action buttons found: " + buttons.size());
    	 clearSuccessNotifications(d);
    	 // Your original XPath was clicking button[2]
    	 if (buttons.size() >= 2) {WebElement assetButton = buttons.get(1);
    	     wait4.until(ExpectedConditions.elementToBeClickable(assetButton)).click();
    	     Reporter.log("Clicked the second action button for " + firstName,true);} else { throw new NoSuchElementException("Second action button not found for employee: " + firstName);}
    	 
    	 WebDriverWait wait5 = new WebDriverWait(d, Duration.ofSeconds(15));
    	// Wait for allocation modal
    	By modalLocator = By.id("allocationModal");

    	WebElement allocationModal = wait5.until(ExpectedConditions.visibilityOfElementLocated(modalLocator));
      Reporter.log("Allocation modal opened",true);
    	// Print modal content to inspect its actual structure
    	Reporter.log("Modal text: " + allocationModal.getText(),true); Thread.sleep(3000);
    	d.findElement(By.xpath("//*[@id=\"asset-id\"]")).click();
    	// Accessories checkbox
    	By accessoriesCheckbox = By.xpath(".//tr[.//*[normalize-space()='Accessories']]//input[@type='checkbox']"	); Thread.sleep(3000);
    	WebElement accessories = wait5.until(driver -> {WebElement modal = driver.findElement(modalLocator); java.util.List<WebElement> elements = modal.findElements(accessoriesCheckbox);
    	    if (!elements.isEmpty() && elements.get(0).isDisplayed()  && elements.get(0).isEnabled()) {  return elements.get(0); }    return null;	}); 
    	if (!accessories.isSelected()) {  accessories.click();	}   Reporter.log("Accessories checkbox selected",true);

    	// Headphones checkbox
    	By headphonesCheckbox = By.xpath(".//tr[.//*[normalize-space()='Headphones']]//input[@type='checkbox']");
    	WebElement headphones = wait5.until(driver -> { WebElement modal = driver.findElement(modalLocator);
    	    java.util.List<WebElement> elements = modal.findElements(headphonesCheckbox);
    	    if (!elements.isEmpty()  && elements.get(0).isDisplayed() && elements.get(0).isEnabled()){return elements.get(0);}  return null; });
    	if (!headphones.isSelected()) {headphones.click();}	System.out.println("Headphones checkbox selected"); Reporter.log("Headphones checkbox selected");
    	clearSuccessNotifications(d);
    	// Click Add button
    	     WebElement addButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addTypesButton\"]")));
    		a.moveToElement(addButton).doubleClick().perform();
    	  Reporter.log("Add button clicked successfully",true);

    		// Locate Shadow Host
    		WebElement shadowHosst = d.findElement(By.cssSelector("ion-icon[name='close-outline']"));
    		// Access Shadow Root
    		SearchContext shadowRoott = shadowHosst.getShadowRoot();
    		// Locate SVG
    		WebElement cancelIcon = shadowRoott.findElement(By.cssSelector("svg.ionicon.s-ion-icon"));
    		// Click Cancel
    		cancelIcon.click();
    		Reporter.log("The Cancel Icon is working",true);

    		WebDriverWait wait6 = new WebDriverWait(d, Duration.ofSeconds(15));

    		wait6.until(ExpectedConditions.invisibilityOfElementLocated(By.id("allocationModal")));
    		Reporter.log("Allocation modal closed", true);

    		// Open sidebar
    		WebElement sidebarButton = wait6.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar']/div[1]/nav/div[1]/button/span")));
    		sidebarButton.click();
    		Reporter.log("Sidebar opened", true);
    		d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click();
    		d.findElement(By.linkText("Employees")).click();

    		// Open Assets menu using its visible text
    		By assetsMenuLocator = By.xpath("//*[@id='sidebar']//*[self::a or self::button]" +"[normalize-space(.)='Assets' or .//span[normalize-space()='Assets']]");

    		WebElement assetsMenu = wait6.until(ExpectedConditions.elementToBeClickable(assetsMenuLocator));
    		assetsMenu.click();Reporter.log("Assets menu clicked", true);

    		// Click Requests & Allocation from the Assets flyout
    		By requestsAllocationLocator = By.xpath("//*[@id='sidebarFlyoutList']//a[contains(normalize-space(.),'Requests') " +"and contains(normalize-space(.),'Allocation')]");

    		WebElement requestsAllocation = wait6.until(ExpectedConditions.elementToBeClickable(requestsAllocationLocator));
    		requestsAllocation.click();Reporter.log("Requests & Allocation opened", true);
    	
    		// ================= ASSET REQUEST TAB =================
    		WebDriverWait wait7 = new WebDriverWait(d, Duration.ofSeconds(30));
    		// Exact Asset Request tab
    		By assetRequestTab = By.cssSelector("button.oh-tabs__tab[data-target='#assetReqAllocContainer2']");
    		// Wait for the correct tab
    		WebElement assetTab = wait7.until(ExpectedConditions.visibilityOfElementLocated(assetRequestTab));
    		// Scroll to the tab
    		((JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView({block:'center'});", assetTab);

    		// Perform actual mouse click
    		new Actions(d).moveToElement(assetTab).click().perform();
    		Reporter.log("Asset Request tab clicked using Actions", true);

    		// Wait until Asset Request table row is actually VISIBLE
    		By assetRequestRow = By.cssSelector("#assetReqAllocContainer2 table tbody tr");

    		wait7.until(driver -> {java.util.List<WebElement> rows = driver.findElements(assetRequestRow);
    		    for (WebElement rowz : rows) {if (rowz.isDisplayed()) {return true; }  } return false;});
    		Reporter.log("Asset Request tab selected and table displayed", true);
          	
    		// ================= REJECTION CODE STARTS =================

    		String[] assetNames = {"Accessories", "Headphones"};
    		for (String assetName : assetNames) {Reporter.log("Processing rejection for: " + assetName, true);

    		    // Find the requested asset row for this employee
    		    By targetRowLocator = By.xpath("//div[@id='assetReqAllocContainer2']//table//tbody/tr[" +"contains(normalize-space(.),'" + firstName + "') and " + "contains(normalize-space(.),'" + badgeId + "') and " + "contains(normalize-space(.),'" + assetName + "') and " + "contains(normalize-space(.),'Requested')]");

    		 // Locate the red cross button in the matching employee and asset row
    		 By rejectLocator = By.xpath("./td[last()]//*[self::a or self::button][2]");

    		 WebElement rejectButton = wait7.until(driver -> {
    		     java.util.List<WebElement> rows = driver.findElements(targetRowLocator);
    		     if (rows.isEmpty()) {return null;}
    		     WebElement targetRow = rows.get(0);
    		     java.util.List<WebElement> actions = targetRow.findElements(rejectLocator);
    		     if (!actions.isEmpty() && actions.get(0).isDisplayed() && actions.get(0).isEnabled()) { return actions.get(0);  } return null; });

    		 ((JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", rejectButton );
    		 Reporter.log(assetName + " red cross button clicked", true);   Reporter.log(assetName + " Reject button clicked", true);

    		    // Confirm rejection
    		    WebElement confirmButton = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.swal2-confirm")));
    		    confirmButton.click();

    		    // Wait for confirmation popup to disappear
    		    wait7.until( ExpectedConditions.invisibilityOfElementLocated( By.cssSelector(".swal2-container")));

    		    // Verify status changed to Rejected
    		    By rejectedRowLocator = By.xpath(
    		        "//div[@id='assetReqAllocContainer2']//table//tbody/tr[" + "contains(normalize-space(.),'" + firstName + "') and " + "contains(normalize-space(.),'" + badgeId + "') and " +"contains(normalize-space(.),'" + assetName + "') and " + "contains(normalize-space(.),'Rejected')]" );
    		    wait7.until(
    		        ExpectedConditions.visibilityOfElementLocated(rejectedRowLocator)
    		    );

    		    System.out.println(assetName + " successfully rejected");
    		    Reporter.log(assetName + " successfully rejected", true);
    		}

    		System.out.println("Both Accessories and Headphones rejected successfully");
    		Reporter.log("Both Accessories and Headphones rejected successfully", true);
    		
    	      	Reporter.log("Both Accessories and Headphones rejected successfully",true);  Reporter.log("Both asset requests rejected successfully",true); Thread.sleep(3000);   

    	
    }

	private void clearSuccessNotifications(WebDriver d) {
		// TODO Auto-generated method stub
		
	}
}