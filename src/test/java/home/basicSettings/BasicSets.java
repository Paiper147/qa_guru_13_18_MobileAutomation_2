package home.basicSettings;

import home.drivers.BrowserstackMobileDriverHome;
import home.drivers.EmulatorMobileDriverHome;
import home.drivers.LocalMobileDriverHome;

import static home.tests.TestBaseHome.testEnvironment;

public class BasicSets {

    public static String getIdPrefix() {
        String idPrefix = null;

        if (testEnvironment == null) {
            return null;
        }

        switch (testEnvironment) {
            case "Browserstack":
            case "Emulator":
                idPrefix = Constants.APP_PACKAGE_WIKIPEDIA_ALPHA;
                break;
            case "Local":
                idPrefix = Constants.APP_PACKAGE_WIKIPEDIA;
                break;
        }
        return idPrefix;
    }

    public static String getBrowser(String testEnvironment) {
        String browser = null;

        if (testEnvironment == null) {
            return null;
        }

        switch (testEnvironment) {
            case "Browserstack":
                browser = BrowserstackMobileDriverHome.class.getName();
                break;
            case "Emulator":
                browser = EmulatorMobileDriverHome.class.getName();
                break;
            case "Local":
                browser = LocalMobileDriverHome.class.getName();
                break;
        }
        return browser;
    }
}
