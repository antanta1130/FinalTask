package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Page {
    protected final WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(Page.class);

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    protected void open(String URL) {
        driver.get(URL);
        LOG.debug("Current url: {}", URL);
        waitForLoad();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    protected void waitForLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
