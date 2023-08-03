package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(tagName = "h2")
    private WebElement usernameField;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isProfileURLLoaded(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));
    }

    public String getProfilePageUser(){
        return usernameField.getText();
    }
}
