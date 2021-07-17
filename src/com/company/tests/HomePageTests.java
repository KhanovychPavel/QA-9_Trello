package com.company.tests;

import com.company.pages.HomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
//    HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
//        homePage = new HomePageHelper(driver);
//        homePage = PageFactory.initElements(driver, HomePageHelper.class);
//        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void verifyApplTest() {
        Assert.assertTrue(homePage.isCorrectPage());

    }
}
