package pages.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public String getSignInText() {
        WebElement signInFormTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public void populateUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("defaultLoginFormUsername"));
        usernameField.sendKeys(username);
    }

    public void populatePassword(String password) {
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signIn.click();
    }

    public void clickRegister() {
        WebElement registerButton = driver.findElement(By.xpath("//a[@href='/users/register']"));
        registerButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));
    }

    public void login(String username, String password) {
        populateUsername(username);
        populatePassword(password);
        clickSignIn();
    }
}