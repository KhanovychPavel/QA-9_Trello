package com.company.tests;

import com.company.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = new MenuPageHelper(driver);
        activityPage = new ActivityPageHelper(driver);

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
        menuPage.pressActivityMenuItem();
        activityPage.waitUntilPageIsLoaded();
        activityPage.waitUntilAllElementsPresents();
    }

    @Test
    public void confirmInfoNewListCreated() {
        activityPage.jampTo("QA Haifa9");
        String listTitle = "Sel-14";
        qa9HaifaPage.newListCreating(listTitle);
        driver.navigate().back();
        activityPage.waitUntilPageIsLoaded();
        activityPage.waitUntilAllElementsPresents();

        Assert.assertTrue(activityPage.textReceiveLastListAdded().contains(listTitle),
                "Information about the newly created list is not displayed");
    }


}
