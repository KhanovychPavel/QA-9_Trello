package com.company.tests;

import com.company.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .loginAttl(LOGIN, PASSWORD);
        boardsPage
                .waitUntilPageIsLoaded()
                .boardsButtonInWorkspacesMenuClick();
        qa9HaifaPage
                .openPage()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void newListCreatingTest() {
        String listTitle = "7";
        int listsSizeBefore = qa9HaifaPage.listsQuantity();
        qa9HaifaPage.newListCreating(listTitle);
        int listsSizeAfter = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(listsSizeAfter, listsSizeBefore + 1, "New list wasn't created");
    }

    @Test
    public void addNewCardTest() {
        String cardTitle = "newCardAdded";
        String listTitle = "anyList";
        int listQuantity = qa9HaifaPage.listsQuantity();

        if(listQuantity == 0) {
            qa9HaifaPage.newListCreating(listTitle);
        }
        int cardsQuantityBefore = qa9HaifaPage.cardsQuantity();

        qa9HaifaPage.addNewCardToTheFirstList(cardTitle);

        int cardsQuantityAfter = qa9HaifaPage.cardsQuantity();

        Assert.assertEquals(cardsQuantityAfter, cardsQuantityBefore + 1, "New list wasn't created");
    }

    @Test
    public void archiveFirstList() {
        String listTitle = "anyName";
        int sizeBeforeArchiving = qa9HaifaPage.listsQuantity();
        if (sizeBeforeArchiving == 0) {
            qa9HaifaPage.newListCreating(listTitle);
            sizeBeforeArchiving++;
        }
        qa9HaifaPage.getArchiveFirstList();

        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1, "List wasn't archived");
    }

    @Test
    public void copyFirstList() {
        String listTitle = "anyName";
        int sizeBeforeCopying = qa9HaifaPage.listsQuantity();
        if (sizeBeforeCopying == 0) {
            qa9HaifaPage.newListCreating(listTitle);
            sizeBeforeCopying++;
        }
        qa9HaifaPage.getCreatingFirstListCopy();

        int sizeAfterCopying = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterCopying, sizeBeforeCopying + 1, "No copy of the list has been created");

        // the name of copy is equals (is the same) to the name of the copied from
        Assert.assertEquals(qa9HaifaPage.textTitleCopy(0), qa9HaifaPage.textTitleOriginal(0 + 1),
                "No copy of the list has been created");
    }


    @Test
    public void archiveListNameContains() {
        String name = "7";
        List<WebElement> list = qa9HaifaPage.getListsList();
        int sizeBeforeArchiving = qa9HaifaPage.listsQuantity();
        int count = 0;
        for (WebElement element : list) {
            if (element.findElement(By.cssSelector(".js-list-name-input")).getAttribute("aria-label").equals(name)) {
                element.findElement(By.cssSelector(".list-header-extras")).click();
                qa9HaifaPage.chooseArchiveThisList();
                count++;
            }
        }
        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - count,
                "Lists with the required name have not been deleted");
    }
}




    //    @Test
//    public void archiveListByName() {
//        String name = "4";
//        List<WebElement> titleList = qa9HaifaPage.getListsTitles();
//        int sizeBefore = titleList.size();
//        int count = 0;
//        for (WebElement element : titleList) {
//            if(element.getText().equals(name)) {
//             driver.findElement(By.xpath
//                     ("//*[@aria-label='name']" +
//        "[//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']]"))
//        .click();
//                qa9HaifaPage.chooseArchiveThisList();
//                count++;
//            }
//        }
//        int sizeAfter = titleList.size();
//
//        Assert.assertEquals(sizeAfter, sizeBefore - count, "List wasn't archived");
//    }



//    @Test
//    public void archiveListByName2() {
//        String name = "7";
//        List<WebElement> list = qa9HaifaPage.getListsList();
//        List<WebElement> titleList = qa9HaifaPage.getListsTitles();
//        int sizeBefore = list.size();
//        int count = 0;

//        list
//                .stream().filter(i -> i.getText().trim().equals(name))
//                .findFirst().get().click();

//        for (WebElement element : list) {
//            if(element.getAttribute("aria-label").equals(name)) {
//                System.out.println(name);
//                driver.findElement(By.xpath
//                        ("//*[@aria-label='name']" +
//                                "[//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']]"))
//                        .click();
//                qa9HaifaPage.chooseArchiveThisList();
//                count++;
//            }
//        }
//        int sizeAfter = list.size();
//
//        Assert.assertEquals(sizeAfter, sizeBefore - count, "List wasn't archived");
//    }
//        @Test
//        public void archiveListByValue () {
//            int value = 4;
//            int sizeBeforeArchiving = qa9HaifaPage.listsQuantity();
//
//            qa9HaifaPage.getArchiveListByValue(value);
//
//            int sizeAfterArchiving = qa9HaifaPage.listsQuantity();
//
//            Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1, "List wasn't archived");
//        }

//    @Test
//    public void copyListByListValue () {
//        int value = 3;
//        int sizeBeforeCopying = qa9HaifaPage.listsQuantity();
//
//        qa9HaifaPage.getCreatingCopy(value);
//
//        int sizeAfterCopying = qa9HaifaPage.listsQuantity();
//
//        Assert.assertEquals(sizeAfterCopying, sizeBeforeCopying + 1, "No copy of the list has been created");
//
//        // the name of copy is equals (is the same) to the name of the copied from
//        Assert.assertEquals(qa9HaifaPage.textTitleCopy(value), qa9HaifaPage.textTitleOriginal(value + 1),
//                "No copy of the list has been created");
//    }




