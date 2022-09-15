package qa.guru.tests.local;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("localTestsQaGuru")
public class AndroidLocalSearchTests extends TestBaseLocalQaGuru {

    @Test
    void searchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia:id/search_src_text"))
                    .sendKeys("Appium");
        });

        step("Chose \"English\" language", () -> {
            $$(AppiumBy.id("org.wikipedia:id/language_label")).filterBy(Condition.text("ENGLISH")).first().click();
        });

        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void searchWithByTextTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia:id/search_src_text"))
                    .sendKeys("Appium");
        });

        step("Chose \"English\" language", () -> {
            $$(AppiumBy.id("org.wikipedia:id/language_label")).filterBy(Condition.text("ENGLISH")).first().click();
        });

        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }
}
