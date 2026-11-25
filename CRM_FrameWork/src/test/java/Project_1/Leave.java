package Project_1;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Leave extends BaseTest {

    @Test (dependsOnMethods = "Project_1.Asset.assetAllocation")
    public void leaveManagement() throws InterruptedException {

        // Move your existing code from lines 468–583 here.
    	// ================= LEAVE MODULE =================
		WebDriverWait waitLeave = new WebDriverWait(d, Duration.ofSeconds(20));

		// 1. Open sidebar
		WebElement sidebarButtonLeave = waitLeave.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar']/div[1]/nav/div[1]/button/span")));sidebarButtonLeave.click();
		Reporter.log("Sidebar opened", true);

		// 2. Click Leave
		By leaveMenu = By.xpath("//*[@id='sidebar']//*[self::a or self::button]" +"[normalize-space(.)='Leave' or .//span[normalize-space()='Leave']]");
		waitLeave.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();
		Reporter.log("Leave menu clicked", true);

		// 3. Click the visible Configuration link
		By configurationLink = By.xpath("//a[contains(@href,'/leave/leave-settings-view/')"+"and normalize-space()='Configuration']");

		WebElement configuration = waitLeave.until(driver -> { for (WebElement link : driver.findElements(configurationLink)) {	if (link.isDisplayed() && link.isEnabled()) { return link;   } } return null;	});

		((JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView({block:'center'});", configuration);
		configuration.click();

		// 4. Verify the actual Leave Types page loaded
		By leaveTypesHeading = By.xpath("//*[contains(normalize-space(.),'Leave Types')]");
		waitLeave.until(ExpectedConditions.visibilityOfElementLocated(leaveTypesHeading));
		Reporter.log("Leave Types page opened successfully", true);

		// 5. Assign Casual, Sick and Annual Leave to the generated employee
		String[] leaveTypes = {"Casual Leave", "Sick Leave", "Annual Leave"};
		for (String leaveType : leaveTypes) {

			// Leave Types page is already open.
			// Do not refresh before each assignment.
			waitLeave.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Leave Types')]")));
			Reporter.log("Leave Types page ready for " + leaveType, true);
		    // Find the row containing the required leave type
		    String leaveRowXPath = "//tr[contains(normalize-space(.),'" + leaveType + "')]";

		    // Find and click Assign
		    Boolean assignClicked = waitLeave.until(driver -> { return (Boolean) ((JavascriptExecutor) driver).executeScript("const rowResult = document.evaluate(arguments[0], document, null, " + "XPathResult.FIRST_ORDERED_NODE_TYPE, null);" +  "const row = rowResult.singleNodeValue;" + "if (!row || !row.isConnected) return false;" +  "const buttonResult = document.evaluate(\".//a[@title='Assign']\", row, null, " + "XPathResult.FIRST_ORDERED_NODE_TYPE, null);" +  "const button = buttonResult.singleNodeValue;" +  "if (!button || !button.isConnected || " +  "button.offsetWidth === 0 || button.offsetHeight === 0) return false;" + "button.scrollIntoView({block:'center'});" + "button.click();" +  "return true;", leaveRowXPath );  });
		    // Wait for Assign Leave modal
		    waitLeave.until(ExpectedConditions.visibilityOfElementLocated(By.id("genericModal")));
		    Reporter.log(leaveType + " Assign button clicked", true);
		    // Employee selection
		    By modalLocatorLeave = By.id("genericModal");

		    By employeeSearchLocator = By.cssSelector("input[placeholder='Search employees']");

		    WebElement employeeSearch = waitLeave.until(driver -> { for (WebElement input : driver.findElements(employeeSearchLocator)) {
		            if (input.isDisplayed() && input.isEnabled()) { return input;} } return null; });
		    // Enter employee first name
		    employeeSearch.click();
		    employeeSearch.clear();
		    employeeSearch.sendKeys(BaseTest.lastName);
		    Reporter.log("Employee first name entered in search: " + firstName, true);

		   
			// Employee full name and badge ID
		    String employeeOptionText = BaseTest.firstName + " " +  BaseTest.lastName + " (" +  BaseTest.badgeId + ")";;
		    By employeeOptionLocator = By.xpath("//li[contains(@class,'select2-results__option') " +"and contains(normalize-space(.),'" + firstName + "') " + "and contains(normalize-space(.),'" + badgeId + "')]");
		    WebDriverWait optionWait = new WebDriverWait(d, Duration.ofSeconds(30));

		    // Wait until employee suggestion appears
		    WebElement employeeOption = optionWait.until(ExpectedConditions.elementToBeClickable(employeeOptionLocator) );

		    // Scroll and click employee suggestion
		    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView({block:'center'});", employeeOption );
		    employeeOption.click();
		    Reporter.log("Employee selected: " + employeeOptionText, true);

		    // Wait until dropdown closes
		    optionWait.until(ExpectedConditions.invisibilityOfElementLocated(employeeOptionLocator));

		    // Click Save inside Assign Leave modal
		    By saveButtonLocator = By.xpath("//*[@id='genericModal']//button[normalize-space()='Save']");
		    WebElement saveButton = waitLeave.until( ExpectedConditions.elementToBeClickable(saveButtonLocator));
		    saveButton.click();
		    Reporter.log(leaveType + " Save button clicked", true);

		    // Wait until assignment modal closes
		    waitLeave.until(ExpectedConditions.invisibilityOfElementLocated(modalLocatorLeave));
		    Reporter.log(leaveType + " assigned successfully", true);} // END OF LEAVE TYPES LOOP
		// This message executes only after all three iterations finish
		Reporter.log("Casual Leave, Sick Leave and Annual Leave assignment steps completed", true);

		// Wait until allocation modal closes
		
     // ========== FINAL LEAVE ALLOCATION VERIFICATION ==========
     WebDriverWait finalWait = new WebDriverWait(d, Duration.ofSeconds(30));
     // 1. Open Employee List directly
     d.get("https://hr.demo.horilla.com/employee/employee-view/");

     // 2. Wait for Employee search box
     By finalSearchLocator = By.xpath("//input[@id='filterForm' or @name='search']");
     WebElement finalSearch = finalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='filterForm']/div[1]/input[1]") ));

     // 3. Search using generated employee name
     finalSearch.clear(); finalSearch.sendKeys(firstName);finalSearch.sendKeys(org.openqa.selenium.Keys.ENTER);
     Reporter.log("Searching employee: " + firstName, true);

     // 4. Locate the employee row using name OR unique Badge ID
     By finalEmployeeRow = By.xpath("//table//tbody/tr[contains(.,'" + firstName + "') or contains(.,'" + badgeId + "')]");

     WebElement employeeRowFinal = finalWait.until(ExpectedConditions.visibilityOfElementLocated(finalEmployeeRow));
     Reporter.log("Employee found: " + firstName, true);

        // 5. Locate Allocations action inside the employee row
        String allocationXPath = "//table//tbody/tr[contains(.,'" + firstName + "') or contains(.,'" + badgeId + "')]" + "//*[self::button or self::a]" +"[contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'allocation')" + " or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'allocation')" + " or contains(translate(@data-bs-original-title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'allocation')]";
  
           // 6. Wait for the button and click the fresh DOM element
          Boolean allocationClicked = finalWait.until(driver -> { return (Boolean) ((JavascriptExecutor) driver).executeScript( "const result = document.evaluate(arguments[0], document, null, " + "XPathResult.FIRST_ORDERED_NODE_TYPE, null);" + "const element = result.singleNodeValue;" + "if (!element || !element.isConnected || " + "element.offsetWidth === 0 || element.offsetHeight === 0 || " + "element.disabled) return false;" + "element.scrollIntoView({block:'center'});" + "element.click();" + "return true;",  allocationXPath  ); });

       Reporter.log("Allocations button clicked successfully", true);

     // 7. Verify all three leave allocations
     finalWait.until(driver -> { String pageText = driver.findElement(By.tagName("body")).getText(); return pageText.contains("Casual Leave")  && pageText.contains("Sick Leave") && pageText.contains("Annual Leave");});

     Reporter.log("Casual Leave - PASS", true); Reporter.log("Sick Leave - PASS", true);Reporter.log("Annual Leave - PASS", true);
     Reporter.log("ALL THREE LEAVE ALLOCATIONS VERIFIED SUCCESSFULLY", true);}
	
	private void clearSuccessNotifications(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("document.querySelectorAll('div.oh-alert--success').forEach(function(alert) { alert.remove(); });"
        ); 

    }
}