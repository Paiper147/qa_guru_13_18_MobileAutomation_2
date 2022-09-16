package home.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static home.basicSettings.BasicSets.getIdPrefix;
import static io.qameta.allure.Allure.step;

@Tag("testHome")
public class AndroidHomeTests extends TestBaseHome {

    @Test
    void searchTest() {
        back();
//        switchTo().alert().accept();

        step("Type search", () -> {
            $(AppiumBy.id(idPrefix + ":id/search_container")).click();
            $(AppiumBy.id(idPrefix + ":id/search_src_text"))
                    .sendKeys("Appium");
        });

        if (testEnvironment != null) {
            if (testEnvironment.equals("Local")) {
                step("Chose \"English\" language", () -> {
                    $$(AppiumBy.id(idPrefix + ":id/language_label")).filterBy(Condition.text("ENGLISH")).first().click();
                });
            }
        }

        step("Verify content found", () ->
                $$(AppiumBy.id(idPrefix + ":id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void searchWithByTextTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id(idPrefix + ":id/search_src_text"))
                    .sendKeys("Appium");
        });

        step("Verify content found", () ->
                $$(AppiumBy.id(idPrefix + ":id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void onboardingTest(){
        step("Check first screen", () -> {
            $(AppiumBy.id(idPrefix + ":id/primaryTextView"))
                    .shouldHave(Condition.text("The Free Encyclopedia …in over 300 languages"));
        });

        step("Check second screen", () -> {
            $(AppiumBy.id(idPrefix + ":id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id(idPrefix + ":id/primaryTextView"))
                    .shouldHave(Condition.text("New ways to explore"));
        });

        step("Check third screen", () -> {
            $(AppiumBy.id(idPrefix + ":id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id(idPrefix + ":id/primaryTextView"))
                    .shouldHave(Condition.text("Reading lists with sync"));
        });

        step("Check fourth screen", () -> {
            $(AppiumBy.id(idPrefix + ":id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id(idPrefix + ":id/primaryTextView"))
                    .shouldHave(Condition.text("Send anonymous data"));
            $(AppiumBy.id(idPrefix + ":id/switchView"))
                    .shouldHave(Condition.attribute("checked","true"));
        });
    }
}
