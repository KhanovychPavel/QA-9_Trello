package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;
import java.util.List;


public class CurrentBoardPageHelper extends PageBase {
    @FindBy(css = ".placeholder")
    WebElement addAListButton;
    @FindBy(css = ".js-list-content")
    List<WebElement> collumnsList;
    @FindBy(css = ".list-name-input")
    WebElement enterListTitle;
    @FindBy(css = ".js-save-edit")
    WebElement saveListButton;
    @FindBy(css = ".js-cancel-edit")
    WebElement cancelNewListButton;
    @FindBy(css = ".js-card-name")
    List<WebElement> cardsList;
    @FindBy(css = ".card-composer-container")
    WebElement addACard;
    @FindBy(css = ".js-card-title")
    WebElement cardTitleField;
    @FindBy(css = ".js-add-card")
    WebElement addCard;
    @FindBy(css = ".js-cancel")
    WebElement cancelNewCardButton;
    @FindBy(css = ".list-header-extras-menu")
    WebElement listActionsButton;
    @FindBy(css = ".js-close-list")
    WebElement archiveThisListButton;
    @FindBy(css = ".js-copy-list")
    WebElement copyListButton;
    @FindBy(css = ".js-submit")
    WebElement createCopyListSubmit;
    @FindBy(css = ".js-autofocus")
    WebElement copyListNameField;
    @FindBy(css = ".list-header-target")
    List<WebElement> listsTitles;

    String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public CurrentBoardPageHelper openPage() {
        // dynamic element - no fix!!!
        waitUntilElementIsClickable(getLocatorBoardButton(), 20); // wait 'QA Haifa9' board is clickable
        driver.findElement(getLocatorBoardButton()).click(); // click on 'QA Haifa9' board
        return this;
    }

    public By getLocatorBoardButton() {
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='"+boardName+"']]");
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addAListButton, 10); // wait for add list is clickable

        if (listsQuantity() != 0) {
            waitUntilAllElementsAreVisible(collumnsList, 10); // wait for all lists elements are present
        }
    }

    public int listsQuantity() {
        return collumnsList.size();
    }

    public void newListCreating(String listTitle) {
        addNewList();
        enterNewListTitle(listTitle);
        createNewListConfirm();
        cancelElseOneNewListAdding();
    }

    public void addNewList() {
        addAListButton.click(); // press 'Add a list' button
    }

    public void enterNewListTitle(String listTitle) {
        waitUntilElementIsClickable(enterListTitle, 10); // wait "add list" button is clickable
        editField(enterListTitle, listTitle); // enter name of the list
    }

    public void createNewListConfirm() {
        int sizeBeforeNewListCreating = listsQuantity();
        waitUntilElementIsClickable(saveListButton, 5);
        saveListButton.click(); // click 'Add list' button
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeNewListCreating + 1, 10);
    }

    public void cancelElseOneNewListAdding() {
        waitUntilElementIsClickable(cancelNewListButton, 10); // wait the 'x' button to cancel new list creating is clickable
        cancelNewListButton.click();
//        waitUntilElementIsInvisible((By.cssSelector(".list-name-input")), 10); // wait 'enter list title' field going to be invisible
    }


    public int cardsQuantity() {
        return cardsList.size();
    }


    public void addNewCardToTheFirstList(String cardTitle) {
        int cardListSizeBeforeAdding = cardsQuantity();
        addACard.click(); // pressAddACardButton

        waitUntilElementIsClickable(cardTitleField, 5); // wait "enter a title for this card..." is clickable
        editField(cardTitleField, cardTitle); //enterNewCardTitle
        addCard.click(); // pressAddCardButton (submit card button)
        waitUntilElementsBecome(By.cssSelector(".js-card-name"), cardListSizeBeforeAdding + 1, 10);

        waitUntilElementIsClickable(cancelNewCardButton, 10); // wait 'x' button is clickable
        driver.findElement(By.cssSelector(".js-cancel")).click(); // click 'x' button
    }

    public void getArchiveFirstList() {
        pressFirstListActionsButton();
        chooseArchiveThisList();
    }

    public void pressFirstListActionsButton() {
        listActionsButton.click();
    }

    public void chooseArchiveThisList() {
        int sizeBeforeArchiving = listsQuantity();
        waitUntilElementIsClickable(archiveThisListButton, 10);
        driver.findElement(By.cssSelector(".js-close-list")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeArchiving - 1, 10);
    }


    public void getCreatingFirstListCopy() {
        pressFirstListActionsButton();
        chooseCopyList();
        createCopyListConfirm();
    }

    public void chooseCopyList() {
        waitUntilElementIsVisible(copyListButton, 10);
        copyListButton.click();
    }

    public void createCopyListConfirm() {
        int sizeBeforeCopying = listsQuantity();
        waitUntilElementIsClickable(createCopyListSubmit, 5); // waiting for "Create list" button clickable
        copyListNameField.click(); // click on list name field
        createCopyListSubmit.click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeCopying + 1, 10);
    }

    public List<WebElement> getListsTitles() {
        return listsTitles;
    }

    public String textTitleCopy(int value) {
        return listsTitles.get(value).getText();
    }

    public String textTitleOriginal(int value) {
        return listsTitles.get(value - 1).getText();
    }

    public List<WebElement> getListsList() {
        return collumnsList;
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



//    public void getArchiveListByValue(int value) {
//        pressListActionsButtonByValue(value);
//        chooseArchiveThisList();
//    }
