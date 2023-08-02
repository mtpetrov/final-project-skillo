package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void clickHomeButton(){
       WebElement homeButton = driver.findElement(By.id("nav-link-home"));
        homeButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));
    }
    public void clickSkilloButton(){
        WebElement skilloButton = driver.findElement(By.id("homeIcon"));
        skilloButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));
    }
    public void clickLoginButton(){
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }

}
