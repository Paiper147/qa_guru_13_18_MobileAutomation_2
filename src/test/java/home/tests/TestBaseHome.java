package home.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static home.basicSettings.BasicSets.getBrowser;
import static home.basicSettings.BasicSets.getIdPrefix;
import static io.qameta.allure.Allure.step;

public class TestBaseHome {
    public static String testEnvironment = System.getProperty("testEnv","Local");
    String idPrefix = getIdPrefix(testEnvironment);

    @BeforeAll
    public static void setup() throws Exception {
        String browser = getBrowser(testEnvironment);
        if (browser == null) {
            throw new Exception("no testEnvironment provided");
        }
        Configuration.browser = browser;
        Configuration.browserSize = null;
        Configuration.timeout = 15000;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        if (testEnvironment != null) {
            if (testEnvironment.equals("Browserstack")) {
//              Видео (ссылка на видео) генерируется только, когда драйвер в Browserstack закрыт,
//              НО метод getSessionId работает, когда браузер запущен
                String sessionId = Attach.getSessionId();
                step("Close driver", Selenide::closeWebDriver);
                Attach.video(sessionId);
            } else if (testEnvironment.equals("Emulator") || testEnvironment.equals("Local")) {
//              Видео Локально НЕ пишется
                step("Close driver", Selenide::closeWebDriver);
            }
        }
    }
}
