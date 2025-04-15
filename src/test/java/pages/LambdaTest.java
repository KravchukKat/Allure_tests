package pages;

import static com.codeborne.selenide.Selectors.withText;
import static org.openqa.selenium.By.linkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;

public class LambdaTest extends TestBase {

    private static final String REPOSITORY = "KravchukKat/Allure_tests";
    private static final int ISSUE = 1;

    @Test
    @Owner("KravchukK")
    @DisplayName("Проверка названия Issue в репозитории")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/home");
                });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys("KravchukKat/Allure_tests");
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText("KravchukKat/Allure_tests")).click();
        });

        step("Открываем tab Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером" + ISSUE, () -> {
            $(withText("#1")).should(Condition.exist);
        });
    }

    @Test
    @Owner("KravchukK")
    @DisplayName("Проверка, что тест падает")
    public void testIssueBroken() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/home");
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys("KravchukKat/Allure_tests");
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText("KravchukKat/Allure_tests")).click();
        });

        step("Открываем tab Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером" + ISSUE, () -> {
            $(withText("#11")).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
        steps.takeScreenshot();
    }
}
