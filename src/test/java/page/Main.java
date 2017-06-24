package page;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Created by Jamie.Shannon on 20/06/2017.
 * Demo for ISA 7.
 */
public class Main {

    // ----------------- PREREQUISITES ----------------- //

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    // Extent reports.
    private ExtentReports report;
    private ExtentTest test;

    // Initialising page classes.
    private IE_Browser browser;
    private Browser_Info info;
    private Log_In logIn;
    private Reception reception;


    // ----------------- SET-UP ----------------- //

    @BeforeClass(alwaysRun = true, groups = {"BasicLogIn"})
    public void setUp() throws FileNotFoundException {

        // Set IE driver location.
        System.out.println("Setting up IE driver.");
        System.setProperty("webdriver.ie.driver", "C:\\IEDriver\\IEDriver.exe");

        // For compatibility with IE.
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        // Opens new instance of IE.
        driver = new InternetExplorerDriver(capabilities);
        System.out.println("Done.");

        // Extent.
        report = new ExtentReports("C:\\Users\\Jamie.Shannon\\Desktop\\I7_Selenium_Java\\Reports\\Report.html");
        test = report.startTest("ISA 7 Demo Test.");
        System.out.println("Set up Extent.");

        // Set default wait timer.
        wait = new WebDriverWait(driver, 10);

        // Initialisers.
        browser = new IE_Browser();
        info = new Browser_Info();
        logIn = new Log_In();
        softAssert = new SoftAssert();
        reception = new Reception();

        test.log(LogStatus.INFO, "Beginning Test Suite");
        System.out.println("Finished Set up.");
    }

    // ---------------- START OF TESTS ---------------- //
    // ----------------- OPEN BROWSER ----------------- //

    @Test(priority = 1, alwaysRun = true, groups = {"BasicLogIn"})
    public void openBrowser() {

        System.out.println("Setting up IE driver.");


        test.log(LogStatus.INFO, "Beginning Open Browser Test");
        browser.open_browser(driver, test, report, softAssert, wait);
        test.log(LogStatus.INFO, "Finished openBrowser Test");

    }

    // -------------- BROWSER INFO -------------- //

    @Test(priority = 5, alwaysRun = true, groups = {"BasicLogIn"})
    public void browserInfo () {
        test.log(LogStatus.INFO, "Beginning Browser Info Test.");
        info.getBrowserInfo(driver, test, report);
        test.log(LogStatus.INFO, "Finished openBrowser Test");
    }

    // ----------------- LOG IN ----------------- //

    @Test(priority = 10, alwaysRun = true, groups = {"BasicLogIn"})
    public void logIn () throws InterruptedException {
        test.log(LogStatus.INFO, "Beginning Log In Test.");
        logIn.logIn(driver, wait, test, report);
        test.log(LogStatus.INFO, "Finished Log In Test");
    }

    // -------------- RECEPTION -------------- //

    @Test(priority = 20, alwaysRun = true, groups = {"Reception"})
    public void reception() throws InterruptedException, SQLException, ClassNotFoundException {

        // Navigate
        test.log(LogStatus.INFO, "Beginning Reception Test.");
        reception.navigateReception(driver, wait, report, test);
        test.log(LogStatus.INFO, "Finished Reception Test");

        // Bag Import.
        test.log(LogStatus.INFO, "Beginning Bag Import Test.");
        reception.importBags(driver, wait, report, test);
        test.log(LogStatus.INFO, "Finished Bag Import Test");
    }

    // -------------- PLACEHOLDER -------------- //


    // -------------- END OF TEST -------------- //

    @AfterClass(alwaysRun = true, groups = {"BasicLogIn"})
    public void endTest() {
        // driver.quit();
        report.endTest(test);
        report.flush();
    }


/*   // ----------------- TO DO ----------------- //

    1. Work out domain problems with IE protected settings.

    2. Find better selectors for Log In procedure.
       I.e. Send key presses rather than finding by xpath?

    3. Determine if Maven issues are caused by firewall.

*/
    // ----------------- NOTES ---------------- //

/* Check present

    if(driver.findElement(By.xpath("value"))!= null){
    System.out.println("Element is Present");
    }else{
    System.out.println("Element is Absent");
    }

 */

/* Check visible

    if( driver.findElement(By.cssSelector("a > font")).isDisplayed()){
    System.out.println("Element is Visible");
    }else{
    System.out.println("Element is InVisible");
    }

 */

/* Check enabled

    if( driver.findElement(By.cssSelector("a > font")).isEnabled()){
    System.out.println("Element is Enable");
    }else{
    System.out.println("Element is Disabled");
    }

 */

/* Check text present

    if(driver.getPageSource().contains("Text to check")){
    System.out.println("Text is present");
    }else{
    System.out.println("Text is absent");
    }

    String selectAll = Keys.chord(Keys.CONTROL, "a");
    driver.findElement(By.whatever("anything")).sendKeys(selectAll);

 */
















}
