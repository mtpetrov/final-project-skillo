package EdgeTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class EdgeTestSetupObject {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeSuite
    protected final void setUpTestSuite(){
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    protected final void tearDownTest(){
        if (this.driver != null){
            driver.close();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

}
