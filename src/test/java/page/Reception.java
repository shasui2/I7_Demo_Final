package page;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jamie.Shannon on 20/06/2017.
 *
 */
public class Reception {

    public void navigateReception(WebDriver driver, WebDriverWait wait, ExtentReports report, ExtentTest test) throws InterruptedException {


        report.startTest("Reception.");
        test.log(LogStatus.INFO, "Starting Reception Test.");
        System.out.println("Starting Reception Test.");

        // NAV to Reception
        driver.findElement(By.id("menu-button")).click();
        System.out.println("Clicked Menu.");
        Thread.sleep(500);
        driver.findElement(By.id("ui-id-88")).click();
        Thread.sleep(500);
        driver.findElement(By.id("ui-id-89")).click();

        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Route_CodeBox")));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            test.log(LogStatus.FAIL, "Timeout occurred.");
        }

        report.endTest(test);

    }

    public void importBags(WebDriver driver, WebDriverWait wait, ExtentReports report, ExtentTest test) throws InterruptedException, SQLException, ClassNotFoundException {

        report.startTest("Reception.");

        String[] bagID = new String[10];
        Calendar cal;
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        test.log(LogStatus.INFO, "Adding bag IDs.");
        for (int i = 0; i < bagID.length; i++) {
            cal = Calendar.getInstance();
            bagID[i] = sdf.format(cal.getTime());
            Thread.sleep(1000);
            System.out.println("bagID[" + i + "]" + bagID[i]);
            test.log(LogStatus.INFO, "bagID[" + i + "]: " + bagID[i] + " added.");
        }

        // Fill in entry form.
        test.log(LogStatus.INFO, "Filling in entry form.");
        System.out.println("Selecting Route..");
        driver.findElement(By.xpath("//*[@id=\"AddReceptionIndex\"]/div[1]/span/span/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"AddReceptionIndex\"]/div[1]/span/span/span[2]/span[3]")).click();
        System.out.println("Route Selected..");
        Thread.sleep(1000);
        driver.findElement(By.name("NumDeclaredContent")).sendKeys("10");

        // Enter Container IDs
        test.log(LogStatus.INFO, "Entering container IDs.");
        System.out.println("Entering container IDs.");

        for (int j = 0; j < bagID.length; j++) {

            WebElement containerID = driver.findElement(By.name("ContainerScan"));

            containerID.sendKeys(bagID[j]);
            containerID.sendKeys(Keys.ENTER);
            Thread.sleep(500);

            // Need to add logic to wait until input box is empty.

//            if (containerID != null) {
//                String attribute = "";
//                System.out.println("Waiting for element to become blank..");
//                System.out.println("Container value is: " + containerID.getAttribute(attribute));
//                Thread.sleep(3000);
//                wait.until(ExpectedConditions.attributeContains(containerID, "isa-scan-field-validated", "isa-scan-field-validated"));
//            }
        }

        // Test the database.

//        Database database = new Database();
//        database.dbTest(driver, wait);

        // Click save
        driver.findElement(By.id("Submit")).click();

        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Route_NameBox")));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            test.log(LogStatus.FAIL, "Timeout occurred.");
        }

        // iFrame

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-block-container\"]/div[3]")));
        String text = driver.findElement(By.xpath("//*[@id=\"title-block-container\"]/div[3]")).getText();

        if (text.contains("printing")) {

        }
        String text1 = driver.findElement(By.xpath("//*[@id=\"title-block-container\"]/div[3]/div/div[2]/span[2]")).getText();
        String text2 = driver.findElement(By.xpath("//*[@id=\"title-block-container\"]/div[3]/div/div[2]/span[2]")).getAttribute("span");
        driver.switchTo().parentFrame();

        //   //*[@id="title-block-container"]/div[3]/div/div[1]

        System.out.println("Text: " + text);
        System.out.println("Text1: " + text1);
        System.out.println("Text1: " + text2);

        test.log(LogStatus.PASS, "Bags added successfully.");
        report.endTest(test);
    }
}

//*[@id="title-block-container"]/div[3]/div
//div/span[2]

// driver.findElement(By.xpath("your editbox's XPath")).sendKeys(Keys.chord(Keys.ALT, "s"));

//    Actions action = new Actions(driver);
//    action.sendKeys(Keys.chord(Keys.CONTROL, "T"))






