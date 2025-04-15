package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class ClearSelenideTest extends TestBase {

    @Test
    @Owner("KravchukK")
    @DisplayName("Проверка названия Issue в репозитории")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/home");

        $(".search-input").click();
        $("#query-builder-test").sendKeys("KravchukKat/Allure_tests");
        $("#query-builder-test").submit();

        $(linkText("KravchukKat/Allure_tests")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);

    }

    @Test
    @Owner("KravchukK")
    @DisplayName("Проверка, что тест падает")
    public void testIssueBroken() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/home");

        $(".search-input").click();
        $("#query-builder-test").sendKeys("KravchukKat/Allure_tests");
        $("#query-builder-test").submit();

        $(linkText("KravchukKat/Allure_tests")).click();
        $("#issues-tab").click();
        $(withText("#11")).should(Condition.exist);

    }
}
