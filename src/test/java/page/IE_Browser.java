package page;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * Created by Jamie.Shannon on 20/06/2017.
 */
public class IE_Browser {

    public void open_browser(WebDriver driver, ExtentTest test, ExtentReports report, SoftAssert softAssert, WebDriverWait wait) {

        System.out.println("Opening browser.");
        test = report.startTest("Browser Open.");
        test.log(LogStatus.INFO, "Beginning open_browser test.");

        driver.manage().window().maximize();
        driver.navigate().to("http://10.16.5.149");
        String strPageTitle = driver.getTitle();
        System.out.println("Page title: - " + strPageTitle);

        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header-block-container\"]")));
            test.log(LogStatus.INFO, "Waiting for elements to load..");
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            test.log(LogStatus.FAIL, "Timeout occurred.");
        }

        System.out.println("Elements loaded.");
        test.log(LogStatus.INFO, "Elements loaded.");
        System.out.println("Verifying presence of log in inputs.");
        test.log(LogStatus.INFO, "Verifying presence of log in inputs.");

        if (driver.findElement(By.xpath("//*[@id=\"header-block-container\"]")) != null) {
            test.log(LogStatus.PASS, "Success: Browser Opened.");
            System.out.println("Header block found.");
        } else {
            System.out.println("Header block not found!");
            test.log(LogStatus.FAIL, "Fail: Browser NOT loaded correctly");
            report.endTest(test);
            report.flush();
            softAssert.fail("Could not find login elements.");
        }

        report.endTest(test);

    }
}
