package tests;


import org.openqa.selenium.NoSuchElementException;
import page.factory.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LogoutTests extends TestSetupObject {
    @DataProvider
    public Object[][] generateLoginData(){

        return new Object[][]{
                {"genadigenadi", "genadi"},
        };
    }
    @DataProvider
    public Object[][] generateRegisterData(){
        return new Object[][]{
                {RegisterPage.generateUsername(5), RegisterPage.generateEmail(2), RegisterPage.generatePassword(10), "10/07/2022"},
        };
    }

    @Test
    public void testLogoutHomePageAnonymousUser(){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

       Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

       Header header = new Header(getDriver());
       Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
    }
    @Test
    public void testLogoutLoginPageAnonymousUser(){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        header.clickLoginButton();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
    }

    @Test(dataProvider = "generateLoginData")
    public void testLogoutAfterLogin(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        header.clickLoginButton();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed" );

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser headerRegisteredUser = new HeaderRegisteredUser(getDriver());
        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The logout button is not displayed!");

        headerRegisteredUser.logOut();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
    }
    @Test(dataProvider = "generateRegisterData")
    public void testLogoutAfterRegister(String username, String email, String password, String date){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        header.clickLoginButton();
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRegister();
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.populateUsername(username);
        registerPage.populateEmail(email);
        registerPage.populatePasswordFields(password);
        registerPage.populateDate(date);
        registerPage.populatePublicText();
        registerPage.clickSignIn();


        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser headerRegisteredUser = new HeaderRegisteredUser(getDriver());

        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The logout button is not displayed!");

        headerRegisteredUser.logOut();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");

    }
    @Test(dataProvider = "generateLoginData")
    public void testLogoutProfilePageLoggedInUser(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        header.clickLoginButton();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");

        LoginPage loginPage = new LoginPage(getDriver());
        String signInText = loginPage.getSignInText();
        Assert.assertEquals(signInText, "Sign in", "The login page is incorrect");

        loginPage.login(username, password);

        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser headerRegisteredUser = new HeaderRegisteredUser(getDriver());
        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The Logout button is not displayed");
        headerRegisteredUser.clickProfile();

        ProfilePage profilePage = new ProfilePage(getDriver());
        Assert.assertTrue(profilePage.isProfileURLLoaded(), "The profile page is not loaded");

        Assert.assertEquals(profilePage.getProfilePageUser(), username, "The profile page shows the wrong user");

        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The Logout button is not displayed");

        headerRegisteredUser.logOut();
        Assert.assertFalse(headerRegisteredUser.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
    }
    @Test(dataProvider = "generateLoginData")
    public void testLogoutPostPageLoggedInUser(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
        loginPage.login(username, password);

        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser headerRegisteredUser = new HeaderRegisteredUser(getDriver());
        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The Logout button is not visible");
        headerRegisteredUser.clickNewPost();

        PostPage postPage = new PostPage(getDriver());
        Assert.assertTrue(postPage.isNewPostURLLoaded(), "The page is not loaded");

        Assert.assertTrue(headerRegisteredUser.isLogoutDisplayed(), "The logout button is not displayed on the New Post page");
        headerRegisteredUser.logOut();

        Assert.assertFalse(headerRegisteredUser.isLogoutDisplayed(), "The logout button is incorrectly displayed");
    }

    @Test
    public void testLogoutSignUpPageAnonymousUser(){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRegister();

        Assert.assertFalse(header.isLogoutDisplayed(), "The Logout button is incorrectly displayed");
    }
    @Test(dataProvider = "generateLoginData")
    public void testProfilePageAfterLogout(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser registeredUser = new HeaderRegisteredUser(getDriver());
        registeredUser.logOut();
try {
    registeredUser.clickProfile();
    Assert.assertFalse(registeredUser.isProfileButtonDisplayed());
} catch (NoSuchElementException e){
    System.out.println("Profile button shouldn't be visible to a logged out user");
}
    }
    @Test(dataProvider = "generateLoginData")
    public void testPostPagePageAfterLogout(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.verifyHomepage(), "The URL is incorrect");

        Header header = new Header(getDriver());
        header.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        Assert.assertTrue(homePage.verifyHomepage(), "User was not redirected to the Homepage after login");

        HeaderRegisteredUser registeredUser = new HeaderRegisteredUser(getDriver());
        registeredUser.logOut();
        try {
            registeredUser.clickNewPost();
            Assert.assertFalse(registeredUser.isPostButtonDisplayed());
        } catch (NoSuchElementException e){
            System.out.println("New Post button shouldn't be visible to a logged out user");
        }
    }
}
