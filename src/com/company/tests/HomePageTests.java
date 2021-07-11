package com.company.tests;

import com.company.pages.HomePageHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void verifyApplTest() {
        Assert.assertTrue(homePage.isCorrectPage());

    }
}
