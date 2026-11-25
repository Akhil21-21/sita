package Project_1;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;

import java.util.NoSuchElementException;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SysTest extends BaseTest {
	
	@BeforeClass
	public void TestBC() {
		Reporter.log("===== EMPLOYEE AUTOMATION STARTED =====",true);}
	
	@Test
	public void Stest() throws InterruptedException {
		
		Reporter.log("TEST STARTS");
		System.out.println("Browser Launched"); Reporter.log("Browser Launched",true);
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    d.get("https://hr.demo.horilla.com/login/");
	    d.findElement(By.id("username")).clear();
	    d.findElement(By.id("username")).sendKeys("admin");
	    d.findElement(By.xpath("//*[@id=\"password\"]")).clear();
	    d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	    d.findElement(By.xpath("/html/body/div[2]/div/form/button")).click(); Thread.sleep(3000); Reporter.log("Login done",true);
	    
	// For new Employee Creation
	    d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click(); Thread.sleep(3000);
	    d.findElement(By.linkText("Employees")).click();  Thread.sleep(3000);
	    d.findElement(By.xpath("//*[@id=\"employeeListNavBar\"]/div/div[1]/div/a/img")).click();
	    Thread.sleep(2000);Reporter.log("Going to Employee Module",true);
	    
	       Random rn = new Random();
	       int ra = 100 + rn.nextInt(900);
	       String badgeId = "PEP" + ra;
	       d.findElement(By.id("id_badge_id")).click();
	       d.findElement(By.id("id_badge_id")).clear();
	       d.findElement(By.id("id_badge_id")).sendKeys(badgeId);  Reporter.log("The generated Badge ID is "+badgeId,true);

         Random random = new Random();
        StringBuilder firstNameBuilder = new StringBuilder();
        firstNameBuilder.append((char) ('A' + random.nextInt(26)));

         for (int i = 0; i < 6; i++) { firstNameBuilder.append((char) ('a' + random.nextInt(26)));}
        String firstName = firstNameBuilder.toString();
        String lastName = String.valueOf((char) ('A' + random.nextInt(26)));
        String employeeNameForLeave = firstName;
	       
          d.findElement(By.id("id_employee_first_name")).sendKeys(firstName); Reporter.log(firstName,true);
          d.findElement(By.id("id_employee_last_name")).sendKeys(lastName);   Reporter.log(lastName,true);
	    
       //EMAIL ID
	    int n = 8;
	    String AlphaNumString = "abcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {  int index = (int) (AlphaNumString.length() * Math.random());
	        sb.append(AlphaNumString.charAt(index));  } 	String email = sb.toString() + "@test.in"; d.findElement(By.id("id_email")).sendKeys(email);
	   Reporter.log("Generated Email: " + email,true);
	    
	    Random r = new Random();
	    String phone = "";
	    phone = phone.concat(String.valueOf(r.nextInt(9) + 1));
	    for (int i = 1; i < 10; i++) { phone = phone.concat(String.valueOf(r.nextInt(10))); }
	    d.findElement(By.id("id_phone")).sendKeys(phone);
	    Reporter.log(phone,true);
	    d.findElement(By.id("id_address")).sendKeys("Dharmavaram , Hindupur , Andhra Pradesh , 555222");
	// Select Country - India
	WebDriverWait addressWait = new WebDriverWait(d, Duration.ofSeconds(15));
	WebElement countryDropdown = addressWait.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_country-container")));
	countryDropdown.click();
	WebElement indiaOption = addressWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='India']")));
	indiaOption.click();
	Reporter.log("India selected successfully", true);

	// Wait for State dropdown to load
	WebElement stateDropdown = addressWait.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_state-container")));
	// Select Andhra Pradesh
	stateDropdown.click();
	WebElement andhraOption = addressWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='Andhra Pradesh']")));
	andhraOption.click();
	Reporter.log("Andhra Pradesh selected successfully", true);
	    d.findElement(By.id("id_city")).sendKeys("Hindupur"); 
	    d.findElement(By.id("id_zip")).sendKeys("555222");
	    
	Actions a = new Actions(d);
	    
	    d.findElement(By.id("dob")).sendKeys("01-01-1960");
	   d.findElement(By.id("id_qualification")).sendKeys("BE-Mechanical");
	    d.findElement(By.id("id_experience")).sendKeys("3");  
	    d.findElement(By.id("select2-id_marital_status-container")).click();
	    d.findElement(By.id("id_children")).sendKeys("1");
	    d.findElement(By.id("id_emergency_contact")).sendKeys("1234512345");
	    d.findElement(By.id("id_emergency_contact_name")).sendKeys("Virat Kohli --- RCB King");
	    d.findElement(By.id("id_emergency_contact_relation")).sendKeys("FAN");
	    d.findElement(By.xpath("//button[@class=\"px-6 py-2 bg-primary-600 rounded-lg text-sm font-medium text-white hover:bg-primary-700 transition-colors shadow-sm\"]")).click();
	    System.out.println("1st page done"); Reporter.log("Basic Entry done",true);
	    
	    // To check the name in organization
	    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

	 // Wait until the success notification disappears
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oh-alert.oh-alert--animated.oh-alert--success"))); 
	 clearSuccessNotifications(d);
	 
	 // Wait until the Work tab is clickable
	 WebElement workTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("step-work")) );  workTab.click();
	    d.findElement(By.xpath("//label[text()='Department']/parent::div/descendant::span[@class='select2-selection__arrow']/b")).click();
        d.findElement(By.xpath("//li[text()='Finance']")).click(); Thread.sleep(3000);
        
        WebDriverWait wait1 = new WebDriverWait(d, Duration.ofSeconds(10));
     // Job Position
     wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_job_position_id-container"))).click();
     wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Financial Analyst']"))).click();
      Reporter.log("Job Position selected successfully",true); Thread.sleep(3000);
     // Job Role
     wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_job_role_id-container"))).click();
     wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Financial Analyst']"))).click();
     Thread.sleep(3000); Reporter.log("Job Role selected successfully",true);
 
  // Work From Office - Select2 dropdown
     WebDriverWait workTypeWait = new WebDriverWait(d, Duration.ofSeconds(15));
     WebElement workTypeDropdown = workTypeWait.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_work_type_id-container")));
     workTypeDropdown.click();

     WebElement workFromOffice = workTypeWait.until(
     ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='Work From Office']")));
     workFromOffice.click();
     Reporter.log("Work from Office - Selected", true);
     
      // Employee Type
      wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_employee_type_id-container"))).click();
      wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='Full-Time']"))).click();
    	 Reporter.log("Full Time selected successfully",true);
      // Shift
      wait1.until(ExpectedConditions.elementToBeClickable(By.id("select2-id_shift_id-container"))).click();  Thread.sleep(3000);
      wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Regular Shift']"))).click();
      Reporter.log("Regular shift selected successfully"); 

      // Reporting and Location
      WebDriverWait wait2 = new WebDriverWait(d, Duration.ofSeconds(5));
      // Location
      WebElement location = wait2.until(ExpectedConditions.elementToBeClickable(By.id("id_location"))); location.sendKeys("Bangalore");
      // Tags dropdown
      WebElement tagsDropdown = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(normalize-space(),'Tags')]/following::span[contains(@class,'select2-selection')][1]")));tagsDropdown.click(); 
      // Select New Joiner
      WebElement newJoiner = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='New Joiner']"))); newJoiner.click();  Reporter.log("New Joiner tag selected successfully",true);

      int m = 4;
   String AlphaNum = "abcdefghijklmnopqrstuvwxyz0123456789";
   StringBuilder sba = new StringBuilder(m);
   for (int i = 0; i < m; i++) {
       int index = rn.nextInt(AlphaNum.length());   sba.append(AlphaNumString.charAt(index));}
   String eemail = sba.toString() + "@horilla.in"; d.findElement(By.xpath("(//*[@id=\"id_email\"])[2]")).sendKeys(eemail);   Reporter.log("Generated Email: " + eemail,true);
   
       // Mobile number - enter only once
      WebElement mobile = wait2.until(ExpectedConditions.elementToBeClickable(By.id("id_mobile"))); mobile.sendKeys("1231231231"); Reporter.log("Mobile number entered successfully",true);

         // Submit Work form
          wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='employeeUpdateWorkForm']/div[6]/button"))).click();
         // Enter history details
          String sTitle = "The Title is " + badgeId; 
         wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_history_title"))).sendKeys(sTitle);
         System.out.println(sTitle); Reporter.log(sTitle); Reporter.log("The Description of the Employee is "+badgeId);
         d.findElement(By.id("id_history_description")).sendKeys("The Description of the Employee is " + badgeId);
         // Submit history modal
           wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'history-modal-button')]"))).click();

           WebDriverWait wait3 = new WebDriverWait(d, Duration.ofSeconds(20));
           By bankTab = By.id("step-bank");
            By successAlert = By.cssSelector("div.oh-alert--success"); clearSuccessNotifications(d);

          // 1. Wait until ALL success alerts disappear
         wait3.until(ExpectedConditions.invisibilityOfElementLocated(successAlert));

         Reporter.log("Success notification disappeared",true);

             //2. Find Bank tab
              WebElement bank = wait3.until(ExpectedConditions.presenceOfElementLocated(bankTab));

           //3. Scroll Firefox to the top of the webpage
            ((JavascriptExecutor) d).executeScript("window.scrollTo(0, 0);");

            //4. Wait until all success alerts disappear
            wait3.until(ExpectedConditions.invisibilityOfElementLocated(successAlert));

            //5. Wait for Bank tab and click
             WebElement freshBank = wait3.until(ExpectedConditions.elementToBeClickable(bankTab));
            ((JavascriptExecutor) d).executeScript("arguments[0].click();", freshBank);
            clearSuccessNotifications(d);
 
           Reporter.log("Bank tab selected successfully", true);
           Reporter.log("Bank Part Starts",true); Thread.sleep(3000); 
           d.findElement(By.id("id_bank_name")).sendKeys("AKHIL Bank");
           Random randomAccount = new Random();
           String accountNumber = String.valueOf(1000000000L + randomAccount.nextLong(9000000000L));
            d.findElement(By.id("id_account_number")).sendKeys(accountNumber);
           System.out.println("Generated Account Number: " + accountNumber);
           d.findElement(By.id("id_branch")).sendKeys("Nagarbhavi Branch"); Thread.sleep(1000);
           d.findElement(By.id("id_any_other_code1")).sendKeys("#1");
           d.findElement(By.id("id_any_other_code2")).sendKeys("#1");Thread.sleep(1000); 
           d.findElement(By.xpath("(//textarea[@id = 'id_address'])[2]")).sendKeys("VV Puram , Bangalore"); Reporter.log("The Address is Entered",true);
           d.findElement(By.xpath("(//*[@id=\"id_city\"])[2]")).sendKeys("Bangalore");
           d.findElement(By.xpath("//*[@id=\"employeeUpdateBankForm\"]/div[4]/button")).click(); Reporter.log("Submit Button is clicked",true);

         Reporter.log("Bank Part Ends",true);
        d.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/nav/div[1]/button/span")).click(); Thread.sleep(3000);
	    d.findElement(By.linkText("Employees")).click();  Thread.sleep(2000);
	    WebDriverWait wait4 = new WebDriverWait(d, Duration.ofSeconds(15)); Reporter.log("Test-ckeck1",true);

	     // Search employee by generated first name
	     WebElement searchBox = wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='filterForm']/div[1]/input[1]"))); searchBox.clear();
	     searchBox.sendKeys(firstName);
	     By employeeRow = By.xpath("//table//tbody/tr[contains(.,'" + firstName + "')]");

	     WebElement row = wait4.until(ExpectedConditions.visibilityOfElementLocated(employeeRow));
	     // Locate the employee row
	     row = wait4.until(ExpectedConditions.visibilityOfElementLocated(employeeRow));

	     // Find Edit button or link inside the employee row
	     By editLocator = By.xpath(".//*[self::button or self::a]" + "[@title='Edit' or @aria-label='Edit' or .//ion-icon[@name='create-outline']]");
	    WebElement editButton = wait4.until(driver -> { WebElement employee = driver.findElement(employeeRow); java.util.List<WebElement> elements =employee.findElements(editLocator);for (WebElement element : elements) { if (element.isDisplayed() && element.isEnabled()) {return element;}}  return null; });

	      // Scroll to Edit button
	      ((org.openqa.selenium.JavascriptExecutor)d).executeScript( "arguments[0].scrollIntoView({block:'center'});", editButton );

         	// Click Edit using JavaScript
	      String employeeListUrl = d.getCurrentUrl();

	      ((JavascriptExecutor) d).executeScript( "arguments[0].click();",  editButton);
	        Reporter.log("Edit button clicked using JavaScript", true);

	      // Wait for navigation or edit page rendering
	        wait4.until(driver ->  !driver.getCurrentUrl().equals(employeeListUrl) || !driver.findElements(By.id("step-work")).isEmpty()	);

	// Wait until the page loading completes
	wait4.until(driver -> "complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState")));

	// If the edit form has not rendered, refresh the current edit page
	if (d.findElements(By.id("step-work")).isEmpty()) {
	    d.navigate().refresh();
	    wait4.until(driver ->"complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState")));}

	     // Verify the actual Work Information tab exists
	       WebDriverWait editWait = new WebDriverWait(d, Duration.ofSeconds(30));
	editWait.until(ExpectedConditions.presenceOfElementLocated(By.id("step-work")));
	Reporter.log("Employee edit page opened successfully", true);

	// Click Work Information tab
	By workTabLocator = By.id("step-work");
	clearSuccessNotifications(d);
	
	// Locate the latest Work Information tab and click it
	wait4.until(driver -> {

	    WebElement freshWorkTab = driver.findElement(workTabLocator);
	    if (!freshWorkTab.isDisplayed() || !freshWorkTab.isEnabled()) {return false; }
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});" + "arguments[0].click();", freshWorkTab ); return true;});
       	System.out.println("Work Information tab clicked");

	    // Wait for Work Information form
	WebElement workForm = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("employeeUpdateWorkForm")));
    	Reporter.log("Work Information form opened successfully",true);
	 Reporter.log("Work Information tab clicked",true);

	 // Wait for Work Information form
	 WebElement edtworkForm = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("employeeUpdateWorkForm")));
	 Reporter.log("Work Information form opened successfully",true);

	// Scroll to the bottom of Work Information
	((org.openqa.selenium.JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView(false);", edtworkForm);

	// Locate Edit in Contract link
	By contractEditLink = By.cssSelector("a[href^='/payroll/update-contract/']");

	// Wait until link is available
  	WebElement editContract = wait4.until( ExpectedConditions.presenceOfElementLocated(contractEditLink)	);
	// Scroll to the link
	((JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView({block:'center'});", editContract);
	// ACTUALLY CLICK THE CONTRACT LINK
	((JavascriptExecutor)d).executeScript( "arguments[0].click();",editContract);
	Reporter.log("Edit in Contract link clicked",true);

	// Wait for contract page URL
	wait4.until(ExpectedConditions.urlContains("/payroll/update-contract/"));

	// 1. Basic Salary
	WebElement basicSalary = wait4.until(ExpectedConditions.elementToBeClickable(By.id("id_wage")));basicSalary.clear();	basicSalary.sendKeys("50000");

	// 2. Hourly Wage
	WebElement hourlyWage = d.findElement(By.id("id_hourly_wage"));hourlyWage.clear();hourlyWage.sendKeys("2600");

	// 3. Monthly CTC
	WebElement monthlyCTC = d.findElement(By.id("id_monthly_ctc")); monthlyCTC.clear();monthlyCTC.sendKeys("80000");

	// 4. Filing Status
	d.findElement(By.id("select2-id_filing_status-container")).click();
	wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and contains(.,'India') and contains(.,'New Regime')]"))).click();

	// 5. Salary Structure
	d.findElement(By.id("select2-id_salary_structure_id-container")).click();
	wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and normalize-space(.)='Standard']"))).click();
	Reporter.log("All contract salary details entered", true);

	
	// 6. Save Contract
	String contractUrl = d.getCurrentUrl();
	WebElement saveContract = wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));saveContract.click();

	wait4.until(driver -> !driver.getCurrentUrl().equals(contractUrl) ||!driver.findElements(By.cssSelector("div.oh-alert--success")).isEmpty());
	Reporter.log("Contract salary details submitted", true);
	d.get("https://hr.demo.horilla.com/employee/employee-view/");

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
		    employeeSearch.sendKeys(firstName);
		    Reporter.log("Employee first name entered in search: " + firstName, true);

		    // Employee full name and badge ID
		    String employeeOptionText = firstName + " " + lastName + " (" + badgeId + ")";
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
        ); }}

  
