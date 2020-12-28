package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WomensShoesPage;

public class PopupMessageTests extends BaseTestForHomePage {
    @BeforeMethod
    public void openHomepage() {
        driver.get("https://shoebacca.com");
    }
    @Test
    public void HomePage_EmailPopupAppears_C138() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isEmailPopup());
    }
    @Test
    public void HomePage_EmailPopupAppears_EmailPopupClosed1_C138() {
        HomePage homePage = new HomePage(driver);
        homePage.closeEmailPopup();
        Assert.assertFalse(homePage.isGrayFieldAroundPopup());
    }
    @Test
    public void HomePage_EmailPopupAppears_EmailPopupClosed2_C138() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOutside();
        Assert.assertFalse(homePage.isGrayFieldAroundPopup());
    }
    @Test
    public void HomePage_EmailPopupAppears_EmailPopupClosed3_C139() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        WomensShoesPage womensShoesPage = homePage.openWomensShoesPage();
        Assert.assertFalse(womensShoesPage.isWomensShoesPage());
    }
}
