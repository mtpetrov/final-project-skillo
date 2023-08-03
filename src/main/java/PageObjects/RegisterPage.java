package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class RegisterPage {
    private static final Random random = new Random();
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void populateUsername() {
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        String username = generateUsername(8);
        usernameField.sendKeys(username);
    }

    public void populateEmail() {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='email']"));
        String email = generateEmail(8);
        passwordField.sendKeys(email);
    }

    public void populateDate(String date) {
        WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));
        dateField.sendKeys(date);
    }

    public void populatePasswordFields() {
        WebElement passwordField = driver.findElement(By.id("defaultRegisterFormPassword"));
        String password = generatePassword(6);
        passwordField.sendKeys(password);
        WebElement confirmPasswordField = driver.findElement(By.id("defaultRegisterPhonePassword"));
        confirmPasswordField.sendKeys(password);
    }

    public void populatePublicText() {
        WebElement publicTextField = driver.findElement(By.xpath("//textarea[@name='pulic-info']"));
        publicTextField.sendKeys("random text");
    }

    public void clickSignIn() {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();
    }

    public void register(String date) {
        populateUsername();
        populateEmail();
        populateDate(date);
        populatePasswordFields();
        populatePublicText();
        clickSignIn();
    }


    public static String generateUsername(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String generateEmail(int length) {
        String firstPart = generateUsername(8);
        String secondPart = "@gmail.com";
        return firstPart + secondPart;
    }

    public static String generatePassword(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString() + random.nextInt();
    }
}