
package Project_1;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SysTest1 extends BaseTest {

    @Test
    public void Stest() {

        System.out.println("===== EMPLOYEE AUTOMATION STARTED =====");

        // Open sidebar menu
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='sidebar']/div[1]/nav/div[1]/button/span")
        )).click();

        System.out.println("Sidebar opened");

        // Click Employees using your original working locator
        wait.until(ExpectedConditions.elementToBeClickable(
            By.linkText("Employees")
        )).click();

        System.out.println("Employee module opened");

        // Verify navigation
        wait.until(ExpectedConditions.urlContains("employee"));

        Assert.assertTrue(
            d.getCurrentUrl().toLowerCase().contains("employee"),
            "Employee module navigation failed"
        );

        System.out.println("===== TEST PASSED =====");
    }
}
