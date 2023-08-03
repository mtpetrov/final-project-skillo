package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewPostPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public NewPostPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isNewPostURLLoaded(){
        return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
    }

}
