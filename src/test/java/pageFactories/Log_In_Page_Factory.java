package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shann on 25/06/2017.
 *
 */
public class Log_In_Page_Factory {

    // Initialises the objects so you can reference test.whatever within your classes rather than driver.findelement.by etc.
    WebDriver driver;

    @FindBy(id = "UserName")
    public WebElement userName;

    @FindBy(id = "Password")
    public WebElement password;

    // Submit.
    @FindBy(xpath = "//*[@id=\"login-dialog-main\"]/fieldset/div[3]/button")
    public WebElement submit;

    // Processing centre.
    @FindBy(xpath = "//*[@id=\"login-dialog-main\"]/fieldset/div[3]/div[2]/span/span[1]")
    public WebElement centre;

    // VSO 188.
    @FindBy(xpath = "//*[@id=\"login-dialog-main\"]/fieldset/div[3]/div[2]/span/span[2]/span[7]")
    public WebElement vso188;

    // Select.
    @FindBy(xpath = "//*[@id=\"login-dialog-main\"]/fieldset/div[4]/button")
    public WebElement select;

    // Select Inventory Location
    @FindBy(xpath = "//*[@id=\"area-header\"]/div[1]/i[2]")
    public WebElement inventoryLocation;

    @FindBy(xpath = "//*[@id=\"area-header\"]/div[2]/div/p[1]/a")
    public WebElement everywhere;

    // Constructor. \\
    public Log_In_Page_Factory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // initiates all above elements.
    }

}
