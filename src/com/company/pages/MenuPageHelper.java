package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPageHelper extends PageBase {

    public MenuPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.findElement(By.cssSelector(".js-open-header-member-menu")).click();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id='header-member-menu-profile']"), 5);
    }

    public String getProfileVisibilityMenuName() {
        return driver.findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']")).getText();
    }

    public void pressActivityMenuItem() {
        WebElement activity = driver.findElement(By.xpath("(//span[contains (text(), 'Activity')])[2]"));
        activity.click();
    }

}
