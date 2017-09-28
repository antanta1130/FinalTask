package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    protected final WebDriver driver;

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    protected void open(String URL) {
        driver.get(URL);
        waitForLoad();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    protected void waitForLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
