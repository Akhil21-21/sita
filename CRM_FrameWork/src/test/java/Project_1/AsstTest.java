
package Project_1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AsstTest extends EmpTest {

    @Test(dependsOnGroups = "employee-created")
    public void allocateAndRejectAssets() throws InterruptedException {

        System.out.println("===== ASSET AUTOMATION STARTED =====");

        // Access employee information from EmployeeTest
        String firstName = EmpTest.firstName;
        String badgeId = EmpTest.badgeId;

        System.out.println("Employee: " + firstName);
        System.out.println("Badge ID: " + badgeId);

        // Open Employees module
        d.findElement(By.xpath("//*[@id='sidebar']/div[1]/nav/div[1]/button/span")).click();
        d.findElement(By.linkText("Employees")).click();

        // Search employee
        By search = By.xpath("//*[@id='filterForm']/div[1]/input[1]");

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(search)).sendKeys(firstName);

        // Find employee row
        By employeeRow = By.xpath( "//table//tbody/tr[contains(.,'" + firstName + "')]" );
        WebElement row = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(employeeRow));   

        // Open asset allocation
        List<WebElement> buttons = row.findElements(By.xpath("./td[last()]//button"));

        if (buttons.size() < 2) {throw new RuntimeException("Asset button not found"); }
        buttons.get(1).click();

        /*
         * MOVE YOUR EXISTING ASSET ALLOCATION CODE HERE:
         *
         * Open allocation modal
         * Select Accessories
         * Select Headphones
         * Click Add
         * Close modal
         */

        /*
         * MOVE YOUR EXISTING ASSET REJECTION CODE HERE:
         *
         * Open Assets module
         * Open Requests and Allocations
         *
         * Reject Accessories
         * Confirm rejection
         * Verify Rejected status
         *
         * Reject Headphones
         * Confirm rejection
         * Verify Rejected status
         */

        Reporter.log("Assets allocated and rejected successfully");

        System.out.println("===== ASSET AUTOMATION COMPLETED =====");
    }
}
