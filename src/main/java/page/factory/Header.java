package page.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "nav-link-home")
    private WebElement homeButton;
    @FindBy(id = "homeIcon")
    private WebElement skilloButton;
    @FindBy(id = "nav-link-login")
    private WebElement loginButton;


    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public void clickHomeButton(){
        homeButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));
    }
    public void clickSkilloButton(){
        skilloButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));
    }
    public void clickLoginButton(){
        loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }
   public boolean isLogoutDisplayed() {
       try {
           WebElement logoutButton = driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']"));
          return logoutButton.isDisplayed();
       } catch (NoSuchElementException e) {
           return false;
       }
   }


}
