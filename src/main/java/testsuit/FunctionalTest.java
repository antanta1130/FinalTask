package testsuit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import factory.WebDriverFactory;
import pages.Page;
import utils.Props;

public abstract class FunctionalTest {
    private static WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(FunctionalTest.class);

    static {
        Props.loadProperties();
    }

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            log.info("Test: {} started, method name {}", className, methodName);
        }

        @Override
        protected void succeeded(Description description) {
            log.info("Test {} passed \n", description.getDisplayName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            log.info("Test {} failed with error {}", description.getDisplayName(), e.getStackTrace());
        }

        // @Override
        // protected void finished(Description description) {
        // log.info("Test {} finished", description.getDisplayName());
        // }
    };

    // @BeforeClass
    public static void initSuit() {
        driver = WebDriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
    }

    @Before
    public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Field field : this.getClass().getDeclaredFields()) {
            Class<?> klass = field.getType();
            if (isPage(klass)) {
                field.setAccessible(true);
                Class<? extends Page> k = klass.asSubclass(Page.class);
                try {
                    Constructor<? extends Page> constr = k.getConstructor(WebDriver.class);
                    constr.setAccessible(true);
                    Page page = constr.newInstance(driver);
                    field.set(this, page);
                } catch (NoSuchMethodException e) {
                    log.error("Page subclass should have constuctor with WebDriver agrument");
                    throw new RuntimeException("Page subclass should have constuctor with WebDriver agrument");
                }
            }
        }

    }

    private boolean isPage(Class<?> klass) {
        Class<?> k = klass;
        do {
            if (Page.class.equals(k)) {
                return true;
            }
            k = k.getSuperclass();
        } while (k != null);
        return false;
    }

    // @AfterClass
    public static void testAfterSuite() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
