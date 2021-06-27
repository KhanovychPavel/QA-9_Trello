package com.company.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void startUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.trello.com/");
        Thread.sleep(5000);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
