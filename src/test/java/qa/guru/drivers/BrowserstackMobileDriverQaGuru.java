package qa.guru.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.guru.owner.BrowserstackProviderQaGuru;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriverQaGuru implements WebDriverProvider {

    @Override
    public WebDriver createDriver (Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        BrowserstackProviderQaGuru browserstackProviderQaGuru = ConfigFactory.create(BrowserstackProviderQaGuru.class, System.getProperties());

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", browserstackProviderQaGuru.userName());
        mutableCapabilities.setCapability("browserstack.key", browserstackProviderQaGuru.userPassword());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", browserstackProviderQaGuru.app());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", browserstackProviderQaGuru.device());
        mutableCapabilities.setCapability("os_version", browserstackProviderQaGuru.os_version());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl(){
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
