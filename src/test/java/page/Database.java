package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;

/**
 * Created by Jamie.Shannon on 21/06/2017.
 *
 */
public class Database {

    public void dbTest(WebDriver driver, WebDriverWait wait) throws SQLException, ClassNotFoundException {

        // Needs to check NAME against DB. Not individual bag IDs.
        // Last error: No suitable driver found for 10.16.5.149.

        Connection con = DriverManager.getConnection("jdbc:sqlserver://10.16.5.149:1433;DatabaseName=isadb", "sa", "Pass.123");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("select name from maestro.reception");

        System.out.println("Order names are: " + rs);
        while (rs.next()){
            String myOrder = rs.getString(1);
            System.out.println(myOrder + "  ");
        }

        con.close();

        // iFrame

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-block-container\"]/div[3]")));
        driver.switchTo().frame(driver.findElement(By.name("//*[@id=\"title-block-container\"]/div[3]")));
        String text = driver.findElement(By.xpath("//*[@id=\"title-block-container\"]/div[3]")).getText();
        String text1 = driver.findElement(By.xpath("//*[@id=\"title-block-container\"]/div[3]")).getAttribute("span");
        driver.switchTo().parentFrame();

    }
}

/*



http://www.guru99.com/database-testing-using-selenium-step-by-step-guide.html





 */







