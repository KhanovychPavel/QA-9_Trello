package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void negativeLogin() throws InterruptedException {
        // click "Log in" button
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(2000);
        // fill email field
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("romuska");
        Thread.sleep(2000);
        // fill password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("gromuska");
        Thread.sleep(2000);
        // press "Log in" button
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);
        // Output err message:
        System.out.println("Error message: " + driver.findElements(By.cssSelector("p.error-message")).get(0).getText());
    }

    @Test
    public void positiveLogin() throws InterruptedException {
        // click "Log in" button
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(5000);
        // fill email field
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("benhakhenn@gmail.com");
        Thread.sleep(2000);
        // press "Log in with Atlassian"
        driver.findElement(By.id("login")).click();
        Thread.sleep(4000);

        // enter my own password
        WebElement enterPassword = driver.findElement(By.id("password"));
        enterPassword.click();
        enterPassword.sendKeys("windozesax");
        Thread.sleep(2000);

        // press log in button
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(12000);
    }
}
