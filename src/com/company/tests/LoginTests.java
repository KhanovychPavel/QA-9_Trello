package com.company.tests;

import com.company.pages.BoardsPageHelper;
import com.company.pages.HomePageHelper;
import com.company.pages.LoginPageHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);

        homePage.waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE HOMEPAGE
        loginPage.openPage(); // wait for "Log in" ON THE HOMEPAGE is clickable + find and click on "Log in"
        loginPage.waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE LOG IN PAGE BEFORE EMAIL & PASSWORD FIELDS FILLING

    }

     @Test
    public void negativeLogin() {
//      loginPage.fillInEmailField("romuska");
//      loginPage.fillInPasswordField("gromuska");
//      loginPage.submitLoginNotAtlassian();
        loginPage.LoginNotAttl("romuska", "gromuska"); //this method replaces 3 methods above
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username", "The error message isn't correct");
    }

    @Test
    public void positiveLogin() {
//      loginPage.fillInEmailField(LOGIN);
//      loginPage.pressLoginAsAttlButton();
//      loginPage.fillInPasswordAttl(PASSWORD);
//      loginPage.submitLoginAttl(); // press Atlassian log in button
        loginPage.loginAttl(LOGIN, PASSWORD); //this method replaces 4 methods above
        boardsPage.waitUntilPageIsLoaded(); // wait for "Boards" button is clickable
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards", "Name of the button isn't 'Boards'");
    }
}
