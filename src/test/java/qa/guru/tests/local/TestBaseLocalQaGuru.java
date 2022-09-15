package qa.guru.tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import qa.guru.drivers.LocalMobileDriverQaGuru;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;

public class TestBaseLocalQaGuru {

    @BeforeAll
    public static void setup() {
        Configuration.browser = LocalMobileDriverQaGuru.class.getName();
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

//        Видео Локально НЕ пишется
        step("Close driver", Selenide::closeWebDriver);
    }
}
