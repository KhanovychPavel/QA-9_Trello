package com.company.tests;

import com.company.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = new MenuPageHelper(driver);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.boardsButtonInWorkspacesMenuClick();
        qa9HaifaPage.openPage();
        qa9HaifaPage.waitUntilPageIsLoaded();
        menuPage.openPage();
        menuPage.waitUntilPageIsLoaded();
    }

    @Test
    public void profileVisibilityMenuExists() {
        Assert.assertEquals(menuPage.getProfileVisibilityMenuName(), "Profile and visibility");
    }


}
