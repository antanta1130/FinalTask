package utils;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public final class Props {
    // private static final Logger log =
    // LoggerFactory.getLogger(PropertiesUtils.class);
    public static final String WEB_DRIVER = "webdriver";
    public static final String WEB_DRIVER_PATH = "driver.path";
    public static final String WEB_DRIVER_PATH_KEY = "driver.path.key";
    public static final String WEB_DRIVER_CLASS = "driver.class";

    private Props() {
    }

    public static void loadProperties() {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("framework.property"));
            String driverName = config.getString(WEB_DRIVER);
            System.setProperty(WEB_DRIVER, driverName);
            load(config, driverName, WEB_DRIVER_PATH);
            load(config, driverName, WEB_DRIVER_PATH_KEY);
            load(config, driverName, WEB_DRIVER_CLASS);
        } catch (ConfigurationException cex) {
            // shit
        }
    }

    private static void load(Configuration config, String driverName, String key) {
        String realKey = driverName + "." + key;
        String value = config.getString(realKey);
        System.setProperty(key, value);
        System.out.println(key + "=" + value);
    }
}
