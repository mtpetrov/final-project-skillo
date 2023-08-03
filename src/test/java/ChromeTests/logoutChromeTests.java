package ChromeTests;

import PageFactory.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class logoutChromeTests extends ChromeTestSetupObject {
    @DataProvider
    public Object[][] generateLoginData(){

        return new Object[][]{
                {"genadigenadi", "genadi"},
        };
    }
    @DataProvider
    public Object[][] generateRegisterData(){
        return new Object[][]{
                {"10/07/2002"},
        };
    }

    @Test
    public void verifyLogoutButtonFromHomepage(){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

       Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

       RegisteredUser registeredUser = new RegisteredUser(getDriver());
       Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");

    }
    @Test
    public void verifyLogoutButtonFromLogin(){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        RegisteredUser registeredUser = new RegisteredUser(getDriver());
        Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");

    }

    @Test(dataProvider = "generateLoginData")
    public void verifyLogoutAfterLogin(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        RegisteredUser registeredUser = new RegisteredUser(getDriver());
        Assert.assertTrue(registeredUser.isLogoutButtonDisplayed(), "The logout button is not displayed!");
        registeredUser.logOut();
        Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");
    }
    @Test(dataProvider = "generateRegisterData")
    public void verifyLogoutAfterRegister(String date){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.register(date);

        RegisteredUser registeredUser = new RegisteredUser(getDriver());

        Assert.assertTrue(registeredUser.isLogoutButtonDisplayed(), "The logout button is not displayed!");

        registeredUser.logOut();

        Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");

    }
    @Test(dataProvider = "generateLoginData")
    public void verifyLogoutFromProfile(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        String signInText = loginPage.getSignInText();
        Assert.assertEquals(signInText, "Sign in", "The login page is incorrect");

        loginPage.login(username, password);

        RegisteredUser registeredUser = new RegisteredUser(getDriver());

        registeredUser.clickProfile();

        ProfilePage profilePage = new ProfilePage(getDriver());
        Assert.assertTrue(profilePage.isProfileURLLoaded(), "The profile page is not loaded");

        Assert.assertEquals(profilePage.getProfilePageUser(), username, "The profile page shows the wrong user");

        Assert.assertTrue(registeredUser.isLogoutButtonDisplayed(), "The logout button is not shown on the Profile page");

        registeredUser.logOut();

        Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");

    }
    @Test(dataProvider = "generateLoginData")
    public void verifyLogoutFromNewPost(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(super.getWait().until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")),"The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        RegisteredUser registeredUser = new RegisteredUser(getDriver());
        registeredUser.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(getDriver());
        Assert.assertTrue(newPostPage.isNewPostURLLoaded(), "The page is not loaded");

        Assert.assertTrue(registeredUser.isLogoutButtonDisplayed(), "The logout button is not displayed on the New Post page");
        registeredUser.logOut();

        Assert.assertTrue(registeredUser.isLogoutButtonNotDisplayed(), "The logout button is incorrectly displayed");
    }
}
