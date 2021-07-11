package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CurrentBoardPageHelper extends PageBase {
    String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
    }

    public void openPage() {
        waitUntilElementIsClickable(getLocatorBoardButton(), 20); // wait 'QA Haifa9' board is clickable
        driver.findElement(getLocatorBoardButton()).click(); // click on 'QA Haifa9' board
    }

    public By getLocatorBoardButton() {
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='"+boardName+"']]");
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".placeholder"), 10); // wait for add list is clickable

        if (listsQuantity() != 0) {
            waitUntilAllElementsArePresents(By.cssSelector(".js-list-content"), 10); // wait for all lists elements are present
        }
    }

    public int listsQuantity() {
        return driver.findElements(By.cssSelector(".js-list-content")).size();
    }

    public void newListCreating(String listTitle) {
        addNewList();
        enterNewListTitle(listTitle);
        createNewListConfirm();
        cancelElseOneNewListAdding();
    }

    public void addNewList() {
        WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
        createListButton.click(); // press 'Add a list' button
    }

    public void enterNewListTitle(String listTitle) {
        waitUntilElementIsClickable(By.cssSelector(".js-save-edit"), 10); // wait "add list" button is clickable
        WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
        editField(nameListField, listTitle); // enter name of the list
    }

    public void createNewListConfirm() {
        WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        saveListButton.click(); // click 'Add list' button
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), listsQuantity() + 1, 10);
    }

    public void cancelElseOneNewListAdding() {
        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"), 10); // wait the 'x' button to cancel new list creating is clickable
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit")); // click 'x' button to cancel new list creating
        cancelListCreatingButton.click();
        waitUntilElementIsInvisible(By.cssSelector(".js-cancel-edit"), 10); // wait 'x' button to cancel new list creating going to be invisible
    }


    public int cardsQuantity() {
        return driver.findElements(By.cssSelector(".js-card-name")).size();
    }


    public void addNewCard(int value, String cardTitle) {

        pressAddACardButton(value);
        enterNewCardTitle(cardTitle);
        pressAddCardButton();
        cancelCreatingElseOneNewCard();
    }

    public void pressAddACardButton(int value) {
        driver.findElements(By.cssSelector(".js-add-a-card")).get(value).click();
    }

    public void enterNewCardTitle(String cardTitle) {
        waitUntilElementIsVisible(By.cssSelector(".js-card-title"), 5); // wait "enter a title for this card..." is visible
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, cardTitle);
    }

    public void pressAddCardButton() {
        driver.findElement(By.cssSelector(".js-add-card")).click();
        waitUntilElementsBecome(By.cssSelector(".js-card-name"), cardsQuantity() + 1, 10);
    }

    public void cancelCreatingElseOneNewCard() {
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"), 5); // wait 'x' button is clickable
        driver.findElement(By.cssSelector(".js-cancel")).click(); // click 'x' button
        waitUntilElementIsInvisible(By.cssSelector(".js-cancel"), 5); // wait 'x' button going to be invisible
    }


    public void getArchiveListByValue(int value) {
        pressListActionsButtonByValue(value);
        chooseArchiveThisList();
    }

    public void pressListActionsButtonByValue(int value) {
        driver.findElements(By.className("list-header-extras")).get(value - 1).click();
    }

    public void chooseArchiveThisList() {
        waitUntilElementIsVisible(By.xpath("//a[@class='js-close-list']/."), 5);
        driver.findElement(By.xpath("//a[@class='js-close-list']/.")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), listsQuantity() - 1, 10);
    }


    public void getCreatingCopy(int value) {
        pressListActionsButtonByValue(value);
        chooseCopyList();
        createCopyListConfirm();
    }

    public void chooseCopyList() {
        waitUntilElementIsVisible(By.xpath("//a[@class='js-copy-list']/."), 10);
        driver.findElement(By.xpath("//a[contains(text(),'Copy list…')]/..")).click();
    }

    public void createCopyListConfirm() {
        waitUntilElementIsClickable(By.cssSelector(".js-submit"), 5); // waiting for "Create list" button clickable
        driver.findElement(By.cssSelector(".js-autofocus")).click(); // click on list name field
        driver.findElement(By.cssSelector(".js-submit")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), listsQuantity() + 1, 10);
    }

    public String textTitleCopy(int value) {
        return driver.findElements(By.cssSelector(".js-list-name-input")).get(value).getText();
    }

    public String textTitleOriginal(int value) {
        return driver.findElements(By.cssSelector(".js-list-name-input")).get(value - 1).getText();
    }

    public List<WebElement> getListsList() {
        return driver.findElements(By.cssSelector(".js-list-content"));
    }

    public List<WebElement> getListsTitles() {
        return driver.findElements(By.cssSelector(".list-header-name"));
    }

    public int getNumberOfElements(String name) {
        List<WebElement> titleList = getListsTitles();
        int number = -1;
        int count = 0;
        for (WebElement element : titleList) {
            if (element.getText().equals(name)) {
                number = count;
            }
            count++;
        }
        return number;


    }
}
