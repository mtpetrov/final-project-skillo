package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisteredUser extends Header {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public RegisteredUser(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickProfile(){
        WebElement profileButton = driver.findElement(By.id("nav-link-profile"));
        profileButton.click();
        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users"));
    }
    public void clickNewPost(){
        WebElement newPostButton = driver.findElement(By.id("nav-link-new-post"));
        newPostButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
    }
    public void logOut(){
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']")));
        logoutButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }
    public boolean isLogoutButtonNotDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']")));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLogoutButtonDisplayed(){
        WebElement logoutButton = driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']"));
        return logoutButton.isDisplayed();
    }

}
