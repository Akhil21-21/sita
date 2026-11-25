
package Project_1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SysTest2 extends BaseTest {

	@Test
    public void employeeAndSidebarTest() {

        // =====================================
        // TEST 1: EMPLOYEE TEST
        // =====================================

        System.out.println("===== EMPLOYEE TEST STARTED =====");

        By employeesMenu = By.xpath(
            "//*[@id='sidebar']//*[normalize-space()='Employees']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(employeesMenu)).click();

        System.out.println("Employees menu clicked successfully");

        System.out.println("===== EMPLOYEE TEST PASSED =====");


        // =====================================
        // TEST 2: SIDEBAR INSPECTION
        // =====================================

        System.out.println();
        System.out.println("===== SIDEBAR INSPECTION STARTED =====");

        // Verify sidebar
        Assert.assertTrue(
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("sidebar")
            )).isDisplayed(),
            "Sidebar is not displayed"
        );

        System.out.println("Sidebar is displayed");

        // Click sidebar button
        By menuButton = By.xpath(
            "//*[@id='sidebar']/div[1]/nav/div[1]/button/span"
        );

        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

        System.out.println("Sidebar button clicked");

        // Verify Employees menu
        Assert.assertTrue(
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                employeesMenu
            )).isDisplayed(),
            "Employees menu is not visible"
        );

        System.out.println("Employees menu is visible");

        // Click Employees menu
        wait.until(ExpectedConditions.elementToBeClickable(employeesMenu)).click();

        System.out.println("Employees menu clicked successfully");

        System.out.println("===== SIDEBAR INSPECTION COMPLETED =====");
    }
}
