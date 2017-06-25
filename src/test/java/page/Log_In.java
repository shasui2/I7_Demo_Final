package page;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page.logInPageFactory;

/**
 * Created by Jamie.Shannon on 20/06/2017.
 *
 */
public class Log_In {

    private Select select;
    private WebElement element;
    logInPageFactory logIn;

    public void logIn (WebDriver driver, WebDriverWait wait, ExtentTest test, ExtentReports report) throws InterruptedException {

        logIn = new logInPageFactory(driver);

        // Enter Log In details.
        test = report.startTest("Log in.");
        test.log(LogStatus.INFO, "Logging in...");
        test.log(LogStatus.INFO, "Entering username.");
        logIn.userName.sendKeys("ecmlab\\jamie.shannon");
        test.log(LogStatus.INFO, "Entering password.");
        logIn.password.sendKeys("Nikki123");
        test.log(LogStatus.INFO, "Clicking Log In.");
        logIn.submit.click();

        // Wait until Cash Centre select box appears.
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("CashCentre_Text")));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            test.log(LogStatus.FAIL, "Timeout occurred.");
        }

        // Select processing centre.
        logIn.centre.click();

        System.out.println("Selecting VSO 188..");
        logIn.vso188.click();
        test.log(LogStatus.INFO, "Clicking Select.");
        logIn.select.click();

        // Wait for DeLaRue Logo to appear.
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-block-logo")));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            test.log(LogStatus.FAIL, "Timeout occurred.");
        }

        // Verify That you are on the home page.
        if (driver.findElement(By.id("header-block-logo")) != null) {
            test.log(LogStatus.PASS, "ISA 7 Home page found.");
        } else {
            test.log(LogStatus.FAIL, "ISA 7 Home page NOT found.");
            Assert.fail();
        }

        // Select Inventory Location
        logIn.inventoryLocation.click();
        logIn.everywhere.click();
        test.log(LogStatus.INFO, "Inventory Location selected.");

        System.out.println("Thread sleeping for 1sec..");
        Thread.sleep(1000);

        report.endTest(test);

    }

}
