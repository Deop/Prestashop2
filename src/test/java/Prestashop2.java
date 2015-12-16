import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Deop on 12.12.2015.
 */
public class Prestashop2 {

    WebDriver firefoxDriver;

    @BeforeClass
    public void setUp(){
        firefoxDriver = new FirefoxDriver();
    }

    @Test
    public void test() throws InterruptedException {
        log("Navigating to main page");
        firefoxDriver.navigate().to("http://prestashop.qatestlab.com.ua/en/");

        Thread.sleep(1000);

        log("Clicking item in \"Popular\" tab");
        firefoxDriver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li")).get(2).findElement(By.className("product-container")).click();

        Thread.sleep(2000);

        firefoxDriver.findElement(By.name("qty")).sendKeys(Keys.BACK_SPACE + "3");
        firefoxDriver.findElement(By.name("Submit")).click();

        Thread.sleep(1000);

        /**
         * Value in cart is updated only after page is refreshed
         */
        //log("Refreshing the page for cart quantity value to update");
        //firefoxDriver.navigate().refresh();

        log("Checking cartQuantity value");
        String cartQuantity = firefoxDriver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[1]")).getText();
        System.out.println(cartQuantity);

        Assert.assertEquals(cartQuantity, "3", "Unexpected value for cartQuantity or value is not displayed");

        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown(){
        firefoxDriver.quit();
    }

    public void log(String message){
        Reporter.log(message);
    }
}
