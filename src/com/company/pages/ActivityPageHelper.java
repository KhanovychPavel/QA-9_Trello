package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActivityPageHelper extends PageBase {

    public ActivityPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(By.cssSelector(".mod-wider"), 5);
    }

    public void waitUntilAllElementsPresents() {
        waitUntilAllElementsArePresents(By.cssSelector(".phenom-desc"), 5);
    }

    public void jampTo(String qa_haifa9) {
        WebElement search = driver.findElement(By.xpath("//input[@data-test-id='header-search-input']"));
        editField(search, qa_haifa9);
        search.sendKeys(Keys.ENTER);
    }

    public String textReceiveLastListAdded() {
        return driver.findElement
                (By.xpath("(//div[@class='phenom-desc'][contains(text(), 'added list')])[1]")).getText();
    }
}
