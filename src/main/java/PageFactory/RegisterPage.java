package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class RegisterPage {
    private static final Random random = new Random();
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@type='date']")
    private WebElement dateField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;
    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//textarea[@name='pulic-info']")
    private WebElement publicTextField;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void populateUsername(){
        String username = generateUsername(8);
        usernameField.sendKeys(username);
    }
    public void populateEmail(){
        String email = generateEmail(8);
        emailField.sendKeys(email);
    }
    public void populateDate(String date){
        dateField.sendKeys(date);
    }
    public void populatePasswordFields(){
        String password = generatePassword(6);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void populatePublicText(){
        publicTextField.sendKeys("random text");
    }

    public void clickSignIn(){
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
    public void register(String date){
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
