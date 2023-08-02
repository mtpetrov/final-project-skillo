package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisteredUser extends Header {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(id = "nav-link-profile")
    private WebElement profileButton;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostButton;
    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement logoutButton;


    public RegisteredUser(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void clickProfile(){
        profileButton.click();
        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users"));
    }
    public void clickNewPost(){
        newPostButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
    }
    public void logOut(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }
    public boolean isLogoutButtonNotDisplayed() {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(logoutButton));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLogoutButtonDisplayed(){

        return logoutButton.isDisplayed();
    }

}
