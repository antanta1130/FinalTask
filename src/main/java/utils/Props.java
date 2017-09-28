package utils;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Props {
    private static final Logger log = LoggerFactory.getLogger(Props.class);
    public static final String WEB_DRIVER = "webdriver";
    public static final String WEB_DRIVER_PATH = "driver.path";
    public static final String WEB_DRIVER_PATH_KEY = "driver.path.key";
    public static final String WEB_DRIVER_CLASS = "driver.class";
    public static final String PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS = "csv.folder.path";
    public static final String DEFAULT_BROWSER = "chrome";

    private Props() {
    }

    public static void loadProperties() {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("framework.property"));
            String driverName = config.getString(WEB_DRIVER, DEFAULT_BROWSER);
            System.setProperty(WEB_DRIVER, driverName);
            System.setProperty(PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS, config.getString(PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS));
            load(config, driverName, WEB_DRIVER_PATH);
            load(config, driverName, WEB_DRIVER_PATH_KEY);
            load(config, driverName, WEB_DRIVER_CLASS);
        } catch (ConfigurationException cex) {
            log.error(cex.getMessage());
        }
    }

    private static void load(Configuration config, String driverName, String key) {
        String realKey = driverName + "." + key;
        String value = config.getString(realKey);
        System.setProperty(key, value);
    }
}
