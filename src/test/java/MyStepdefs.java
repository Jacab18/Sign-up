import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;

    @Given("I have written my username {string}")
    public void iHaveWrittenMyUsername(String username) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        System.out.println(username);

        sendKeys(driver, By.id("new_username"), username);
    }

    @Given("I have written my email {string}")
    public void iHaveWrittenMyEmail(String email) {
        System.out.println(email);
        sendKeys(driver, By.id("email"), email);
    }

    @Given("I have written my password {string}")
    public void iHaveWrittenMyNewPassword(String current) {
        System.out.println(current);
        sendKeys(driver, By.id("new_password"), current);
    }

    @When("I click the Sign Up button")
    public void iClickTheSignUpButton() {
        scroll(driver);
        click(driver, By.id("create-account"));
    }

    @Then("my account is made")
    public void myAccountIsMade() {
        String expected = "Check your email\n" +
                "We’ve sent a message to fenisik483@karavic.com with a link to activate your account.\n" +
                "Didn’t get an email?\n" +
                "If you don’t see an email from us within a few minutes, a few things could have happened:\n" +
                "The email is in your spam folder. (Sometimes things get lost in there.)\n" +
                "The email address you entered had a mistake or typo. (Happens to the best of us.)\n" +
                "You accidentally gave us another email address. (Usually a work or personal one instead of the one you meant.)\n" +
                "We can’t deliver the email to this address. (Usually because of corporate firewalls or filtering.)\n" +
                "Re-enter your email and try again";
        String actual = getText(driver, By.cssSelector("div[class='lastUnit size1of1']"));
        System.out.println(actual);
        assertEquals(expected, actual);
        driver.quit();
    }

    @Then("I am shown a error")
    public void iAmShownAError() {
        String expected = "Please check your entry and try again.";
        String actual = getText(driver, By.cssSelector("div[class='av-flash v-flashBlock--error padding--lv3 !padding-left-right--lv0']"));
        System.out.println(actual);
        assertEquals(expected, actual);
        driver.quit();
    }

    private String getText(WebDriver driver, By by) {
        WebDriverWait foobar = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = foobar.until(ExpectedConditions.presenceOfElementLocated(by));
        String text = element.getText();
        return text;
    }

    public static void scroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }

    private static void sendKeys(WebDriver driver, By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        element.sendKeys(text);
    }

    private static void click(WebDriver driver, By by) {
        WebDriverWait foobar = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = foobar.until(ExpectedConditions.elementToBeClickable(by));

        element.click();
    }

}