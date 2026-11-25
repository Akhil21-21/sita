
package Project_1;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class EmpTest extends BaseTesst {

    // Shared employee details for AssetTest
    public static String badgeId;
    public static String firstName;
    public static String lastName;

    @Test(groups = "employee-created")
    public void createEmployee() throws InterruptedException {

        System.out.println("===== EMPLOYEE CREATION STARTED =====");

        Random random = new Random();

        // Generate employee details
        badgeId = "PEP" + (100 + random.nextInt(900));

        firstName = "E" + random.ints(6, 97, 123)
                .collect(StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append).toString();

        lastName = "L" + random.ints(5, 97, 123)
                .collect(StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append).toString();

        String email = "emp" + System.currentTimeMillis()
                + "@test.in";

        String phone = "9" + String.format("%09d",
                random.nextInt(1000000000));

        // Open Employee module
        d.findElement(By.xpath(
            "//*[@id='sidebar']/div[1]/nav/div[1]/button/span"
        )).click();

        d.findElement(By.linkText("Employees")).click();

        d.findElement(By.xpath(
            "//*[@id='employeeListNavBar']/div/div[1]/div/a/img"
        )).click();

        // BASIC EMPLOYEE DETAILS
        d.findElement(By.id("id_badge_id")).sendKeys(badgeId);
        d.findElement(By.id("id_employee_first_name")).sendKeys(firstName);
        d.findElement(By.id("id_employee_last_name")).sendKeys(lastName);
        d.findElement(By.id("id_email")).sendKeys(email);
        d.findElement(By.id("id_phone")).sendKeys(phone);

        System.out.println("Employee: " + firstName);
        System.out.println("Badge ID: " + badgeId);

        /*
         * MOVE YOUR EXISTING BASIC DETAILS CODE HERE:
         *
         * Address
         * Country and State
         * City and ZIP
         * DOB
         * Qualification
         * Experience
         * Marital Status
         * Emergency Contact
         *
         * Submit Basic Details
         */

        // Wait for Work tab
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
            .elementToBeClickable(By.id("step-work"))).click();

        /*
         * MOVE YOUR EXISTING WORK DETAILS CODE HERE:
         *
         * Department
         * Job Position
         * Job Role
         * Work Type
         * Employee Type
         * Shift
         * Location
         * Tags
         * Work Email
         * Mobile
         *
         * Submit Work Form
         * Add Employee History
         */

        /*
         * MOVE YOUR EXISTING BANK DETAILS CODE HERE:
         *
         * Click Bank Tab
         * Bank Name
         * Account Number
         * Branch
         * Other Codes
         * Address
         * City
         *
         * Submit Bank Form
         */

        Reporter.log("Employee created: " + firstName);

        System.out.println("===== EMPLOYEE CREATION COMPLETED =====");
    }
}
