package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//p[text()='Sign in']")
    private WebElement signInFormTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(xpath = "//a[@href='/users/register']")
    private WebElement registerButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    public String getSignInText() {
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public void populateUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickRegister() {
        registerButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));
    }

    public void login(String username, String password) {
        populateUsername(username);
        populatePassword(password);
        clickSignIn();
    }
}