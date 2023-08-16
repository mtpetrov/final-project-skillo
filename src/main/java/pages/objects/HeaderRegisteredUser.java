package pages.objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderRegisteredUser extends Header {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public HeaderRegisteredUser(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickProfile(){
        WebElement profileButton = driver.findElement(By.id("nav-link-profile"));
        profileButton.click();
        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users"));
    }

    public boolean isProfileButtonDisplayed(){
        WebElement profileButton = driver.findElement(By.id("nav-link-profile"));
        return profileButton.isDisplayed();
    }
    public void clickNewPost(){
        WebElement newPostButton = driver.findElement(By.id("nav-link-new-post"));
        newPostButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
    }
    public boolean isPostButtonDisplayed(){
        WebElement newPostButton = driver.findElement(By.id("nav-link-new-post"));
        return newPostButton.isDisplayed();
    }
    public void logOut(){
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']")));
        logoutButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }



}
