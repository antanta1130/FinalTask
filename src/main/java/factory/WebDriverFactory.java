package factory;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.UnsupportedDriverException;
import utils.Props;

public class WebDriverFactory {
    private static volatile WebDriverFactory instance = null;
    private final Class<? extends WebDriver> klass;
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    private WebDriverFactory() throws ClassNotFoundException {
        System.setProperty(System.getProperty(Props.WEB_DRIVER_PATH_KEY), System.getProperty(Props.WEB_DRIVER_PATH));
        String klassName = System.getProperty(Props.WEB_DRIVER_CLASS);
        klass = (Class<? extends WebDriver>) WebDriverFactory.class.getClassLoader().loadClass(klassName);
    }

    public static WebDriverFactory getInstance() {
        if (instance == null) {
            synchronized (WebDriverFactory.class) {
                if (instance == null) {
                    try {
                        instance = new WebDriverFactory();
                    } catch (ClassNotFoundException e) {
                        throw new UnsupportedDriverException(e.getMessage());
                    }
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        try {
            LOG.info("New instance of webdriver is created");
            return klass.newInstance();
        } catch (ReflectiveOperationException e) {
            LOG.error("ReflectiveOperationException", e);
            throw new UnsupportedDriverException(e.getMessage());
        }
    }

}
