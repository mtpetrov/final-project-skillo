package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isProfileURLLoaded(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));
    }

    public String getProfilePageUser(){
        WebElement usernameField = driver.findElement(By.tagName("h2"));
        return usernameField.getText();
    }
}
