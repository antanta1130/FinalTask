package testsuit;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import factory.WebDriverFactory;
import utils.Props;

public class MySuite {
    private static WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(MySuite.class);

    static {
        Props.loadProperties();
    }

    @ClassRule
    public static final ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            driver = createDriver();
            LOG.info("Suite started");
        };

        @Override
        protected void after() {
            driver.quit();
            driver = null;
            LOG.info("Suite finished");
        };
    };

    // for usage in test
    public static WebDriver getDriver() {
        if (driver == null) {
            return createDriver();
        }
        return driver;
    }

    // for usage in test (to check if test includes in suite)
    public static boolean hasSuite() {
        return driver != null;
    }

    private static WebDriver createDriver() {
        WebDriver driver = WebDriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
        return driver;
    }

}
