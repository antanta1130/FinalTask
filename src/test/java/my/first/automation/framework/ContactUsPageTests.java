package my.first.automation.framework;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactUsPageTests {

    private static final String CONTACT_US_HEADING = "Contact us";
    WebDriver driver;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyContactUsPageHeading() {
        driver.get("http://demoqa.com/");
        driver.findElement(By.linkText(CONTACT_US_HEADING)).click();
        Assert.assertEquals("Contact Us page heading is wrong!", CONTACT_US_HEADING, getPageHeading());
    }

    private String getPageHeading() {
        return driver.findElement(By.xpath("//h1")).getText();
    }

}
