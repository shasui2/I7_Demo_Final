package page;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jamie.Shannon on 20/06/2017.
 */
public class Browser_Info {

    @Test(priority = 10, groups = {"BasicLogIn"})
    public void getBrowserInfo(WebDriver driver, ExtentTest test, ExtentReports report) {

        test = report.startTest("Browser info.");

        System.out.println("Website title: " + driver.getTitle());
        test.log(LogStatus.INFO, "Website title: " + driver.getTitle());
//        System.out.println("Website source: " + driver.getPageSource()); // Will display html file.
        System.out.println("Current URL: " + driver.getCurrentUrl());
        test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
        test.log(LogStatus.INFO, "Expected URL: http://10.16.5.149/Accounts/Login");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Storing Title name in the String variable
        String title = driver.getTitle();

        // Storing Title length in the Int variable
        int titleLength = driver.getTitle().length();

        // Printing Title & Title length in the Console window
        System.out.println("Title of the page is : " + title);
        System.out.println("Length of the title is : " + titleLength);

        // Storing URL in String variable
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals("http://10.16.5.117/Accounts/Login")) {
            System.out.println("Verification Successful - The correct Url is opened.");
            test.log(LogStatus.PASS, "Verification Successful - The correct Url is opened.");
        } else {
            test.log(LogStatus.FAIL, "Verification Failed - An incorrect Url is opened.");
            System.out.println("Verification Failed - An incorrect Url is opened.");
            //In case of Fail, you like to print the actual and expected URL for the record purpose
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : http://10.16.5.117/Accounts/Login");
        }

        // Storing Page Source in String variable
        String pageSource = driver.getPageSource();

        // Storing Page Source length in Int variable
        int pageSourceLength = pageSource.length();

        // Printing length of the Page Source on console
        System.out.println("Total length of the Page Source is : " + pageSourceLength);
        report.endTest(test);

    }


}
