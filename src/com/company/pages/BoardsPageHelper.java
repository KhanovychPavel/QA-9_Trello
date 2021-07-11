package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardsPageHelper extends PageBase {

    public BoardsPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        // wait for "Boards" button is clickable
        waitUntilElementIsClickable(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"), 30);
    }

    public String getBoardsButtonName() {
        return driver
                .findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).getText();
    }

    public void boardsButtonInWorkspacesMenuClick() {
                    // to the "Board" in workspaces screen
        waitUntilElementIsClickable(By.xpath("(//span[contains(text(), 'Boards')])[3]"), 10); // wait the "Board" is clickable
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click(); // click the "Board"
        waitUntilElementIsVisible(By.xpath("//h3[contains(text(),'Your Workspace boards')]"), 10); // wait menu "Your Workspace boards" is visible
    }

}
