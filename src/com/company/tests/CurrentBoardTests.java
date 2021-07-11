package com.company.tests;

import com.company.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;

    @BeforeMethod
    public void initTest() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.boardsButtonInWorkspacesMenuClick();
        qa9HaifaPage.openPage();
        qa9HaifaPage.waitUntilPageIsLoaded();
    }

    @Test
    public void newListCreatingTest() {
        String listTitle = "3";
        int listsSizeBefore = qa9HaifaPage.listsQuantity();
//        qa9HaifaPage.addNewList();
//        qa9HaifaPage.enterNewListTitle("listTitle");
//        qa9HaifaPage.createNewListConfirm();
//        qa9HaifaPage.cancelElseOneNewListAdding();
        qa9HaifaPage.newListCreating(listTitle);
        int listsSizeAfter = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(listsSizeAfter, listsSizeBefore + 1, "New list wasn't created");
    }

    @Test
    public void addNewCardTestByListValue () {
        int value = 1;
        String cardTitle = "33";
        int cardsQuantityBefore = qa9HaifaPage.cardsQuantity();
//        qa9HaifaPage.pressAddACardButton(value);
//        qa9HaifaPage.enterNewCardTitle(cardTitle);
//        qa9HaifaPage.pressAddCardButton();
//        qa9HaifaPage.cancelCreatingElseOneNewCard();
        qa9HaifaPage.addNewCard(value, cardTitle);

        int cardsQuantityAfter = qa9HaifaPage.cardsQuantity();

        Assert.assertEquals(cardsQuantityAfter, cardsQuantityBefore + 1, "New list wasn't created");
    }

    @Test
    public void archiveListByValue () {
        int value = 3;
        String listTitle = "anyName";
        int sizeBeforeArchiving = qa9HaifaPage.listsQuantity();
        if (sizeBeforeArchiving == 0) {
            qa9HaifaPage.newListCreating(listTitle);
            sizeBeforeArchiving++;
        }
        qa9HaifaPage.getArchiveListByValue(value);

        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1, "List wasn't archived");
    }

    @Test
    public void copyListByListValue () {
        int value = 2;
        String listTitle = "anyName";
        int sizeBeforeCopying = qa9HaifaPage.listsQuantity();
        if (sizeBeforeCopying == 0) {
            qa9HaifaPage.newListCreating(listTitle);
            sizeBeforeCopying++;
        }
        qa9HaifaPage.getCreatingCopy(value);

        int sizeAfterCopying = driver.findElements(By.className("js-list-content")).size();

        Assert.assertEquals(sizeAfterCopying, sizeBeforeCopying + 1, "No copy of the list has been created");

    // the name of copy is equals (is the same) to the name of the copied from
        Assert.assertEquals(qa9HaifaPage.textTitleCopy(value), qa9HaifaPage.textTitleOriginal(value + 1),
                "No copy of the list has been created");
    }

//    @Test
//    public void archiveListByName() {
//        String name = "3";
//        List<WebElement> titleList = qa9HaifaPage.getListsTitles();
//        int sizeBefore = qa9HaifaPage.listsQuantity();
//
//        for (WebElement element : titleList) {
//            if(element.getText().equals(name)) {
//                element.findElement(By.cssSelector(".list-header-extras-menu")).getText("3").click();
//            }
//            qa9HaifaPage.chooseArchiveThisList();
//        }
//        int sizeAfter = qa9HaifaPage.listsQuantity();
//
//        Assert.assertEquals(sizeAfter, sizeBefore - 1, "List wasn't archived");
//    }

}
