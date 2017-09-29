package testsuit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.Page;
import utils.Props;

public abstract class FunctionalTest {
    private static final Logger LOG = LoggerFactory.getLogger(FunctionalTest.class);

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
            LOG.info("Test: {} started, method name {}", className, methodName);
        }

        @Override
        protected void succeeded(Description description) {
            LOG.info("PASSSED: {} test\n", description.getDisplayName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOG.error("FAILED: {} test with error {} \n", description.getDisplayName(), e.getClass().getSimpleName());
            LOG.error("cause: {} \n", e.getMessage());
        }
    };

    private static WebDriver driver;

    @BeforeClass
    public static void initDriver() {
        LOG.debug("@BeforeClass");
        driver = MySuite.getDriver();
    }

    @Before
    public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        LOG.debug("@Before");
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
                    LOG.error("Page subclass should have constuctor with WebDriver agrument");
                    throw new RuntimeException("Page subclass should have constuctor with WebDriver agrument");
                }
            }
        }
    }

    @AfterClass
    public static void after() {
        if (!MySuite.hasSuite()) {
            driver.quit();
            driver = null;
            LOG.debug("@AfterClass");
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
}
