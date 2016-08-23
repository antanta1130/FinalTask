package my.first.automation.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.Matchers.hasItem;

@RunWith(value = Parameterized.class)
public class HomePageTests {

    private WebDriver driver;
    private String expected;

    public HomePageTests(String expected) {
        this.expected = expected;
    }

    @Parameters
    public static List<String> data() {
        return Arrays.asList("Home", "About us", "Services", "Demo", "Blog", "Contact");
    }

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
    public void verifyTopMenuLinkNames() {
        driver.get("http://demoqa.com/");
        List<String> topMenuLinks = getTopMenuLinks();
        Assert.assertThat(String.format("%s Top Menu link is not present!", expected), topMenuLinks, hasItem(expected));
    }

    private List<String> getTopMenuLinks() {
        List<String> linkNames = new ArrayList<String>();
        List<WebElement> topMenuLinks = driver.findElements(By.xpath("//ul[@id='menu-primary-menu']/li/a"));
        for (WebElement link : topMenuLinks) {
            linkNames.add(link.getText());
        }
        return linkNames;
    }

}
