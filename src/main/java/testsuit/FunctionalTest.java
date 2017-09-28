package testsuit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import factory.WebDriverFactory;
import pages.Page;

public abstract class FunctionalTest {
    private WebDriver driver;

    @Before
    public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        driver = WebDriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();

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

    @After
    public void testAfterSuite() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
