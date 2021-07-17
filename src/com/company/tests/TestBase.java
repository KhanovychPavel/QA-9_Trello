package com.company.tests;

import com.company.pages.HomePageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static String LOGIN = "benhakhenn@gmail.com";
    public static String PASSWORD = "windozesax";
    HomePageHelper homePage;

    WebDriver driver;

    @BeforeMethod
    public void startUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("lang=" + "en");
        driver = new ChromeDriver(options);
        driver.get("https://www.trello.com/");

        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
