package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WomensShoesPage;

public class WomensShoesPageTests extends BaseTestForHomePage{
    @BeforeMethod
    public void openHomepage() {
        driver.get("https://shoebacca.com/womens-shoes.html");
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickOutsidePopup();
    }
    @Test
    public void WomensShoesPage_Logo_C141() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        Assert.assertTrue(womensShoesPage.logoExist());
        Assert.assertTrue(womensShoesPage.logoLocation());
    }
    @Test
    public void WomensShoesPage_LogoRedirectsToHomepage_C141() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        HomePage homePage = womensShoesPage.openHomePage();
        Assert.assertTrue(homePage.isHomePage());
    }
    @Test
    public void WomensShoesPage_MiniCart_C142() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        Assert.assertTrue(womensShoesPage.minicartExist());
        Assert.assertTrue(womensShoesPage.minicartLocation());
        Assert.assertFalse(womensShoesPage.isMiniCartBubble());
    }
    @Test
    public void WomensShoesPage_openEmptyMiniCart_C143() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickMiniCart();
        Assert.assertTrue(womensShoesPage.isMiniCartOpened());
        Assert.assertTrue(womensShoesPage.isMiniCartEmpty());
    }
    @Test
    public void WomensShoesPage_openSigninWindowFromMiniCart_C143() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickMiniCart();
        womensShoesPage.openSigninWindowFromMiniCart();
        Assert.assertTrue(womensShoesPage.isSigninPopup());
    }
    @Test
    public void HomePage_EmptyMiniCartClosed1_C143() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickMiniCart();
        womensShoesPage.clickMiniCart();
        Assert.assertFalse(womensShoesPage.isMiniCartOpened());
    }
    @Test
    public void HomePage_EmptyMiniCartClosed2_C143() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickMiniCart();
        womensShoesPage.closeMiniCart();
        Assert.assertFalse(womensShoesPage.isMiniCartOpened());
    }
    @Test
    public void HomePage_EmptyMiniCartClosed3_C143() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.clickMiniCart();
        womensShoesPage.clickOutside();
        Assert.assertFalse(womensShoesPage.isMiniCartOpened());
    }
    @Test
    public void WomensShoesPage_MiniCartWithProducts_C144() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.addProductToCart();
        womensShoesPage.numberInBubble();
//        Assert.assertTrue(womensShoesPage.minicartExist());
//        Assert.assertTrue(womensShoesPage.minicartLocation());
//        Assert.assertFalse(womensShoesPage.isMiniCartBubble());
//        Assert.assertEquals(query, search3);
    }

}
