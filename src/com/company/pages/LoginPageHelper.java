package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageHelper extends PageBase {

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        waitUntilElementIsClickable(By.cssSelector(".text-primary"), 40); // // wait for "Log in" is clickable
        driver.findElement(By.cssSelector(".text-primary")).click(); // find and click on "Log in"
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.id("login"),10); // wait for "Log in" button is is clickable ON THE LOG IN PAGE WITH EMAIL & PASSWORD FIELDS FILLING
    }

    public void LoginNotAttl(String login, String password) {
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLoginNotAtlassian();
    }

    public void loginAttl(String login, String password) {
        fillInEmailField(login);
        pressLoginAsAttlButton();
        fillInPasswordAttl(password);
        submitLoginAttl();
    }

    public String getErrorMessage () {
        waitUntilElementIsVisible(By.cssSelector("p.error-message"), 10);
        return driver.findElement(By.cssSelector("p.error-message")).getText();
    }

    public void fillInEmailField (String value) {
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, value);   // (emailField.click(); + emailField.sendKeys(".....");)
    }

    public void fillInPasswordField(String value) {
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitLoginNotAtlassian() {
        driver.findElement(By.id("login")).click(); // press "Log in" button
    }

    public void pressLoginAsAttlButton() {
        waitUntilElementIsClickable(By.cssSelector(".btn-success"), 10); // wait for "Log in with Atlassian" button is clickable
        driver.findElement(By.cssSelector(".btn-success")).click(); // press "Log in with Atlassian"

    }

    public void fillInPasswordAttl(String value) {
        waitUntilElementIsClickable(By.id("login-submit"), 10); // wait for "Log in" button is clickable
        WebElement enterPassword = driver.findElement(By.id("password")); // find the field "password"
        editField(enterPassword, value); // enter my own password & click

    }

    public void submitLoginAttl() {
        driver.findElement(By.id("login-submit")).click(); // press Atlassian log in button

    }
}
