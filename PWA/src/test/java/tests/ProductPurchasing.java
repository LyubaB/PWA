package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WomensShoesPage;

public class ProductPurchasing extends BaseTestForHomePage{
    @BeforeMethod
    public void openWomensShoesPage() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.open();
    }
    @Test
    public void WomensShoesPage_ProductPurchasing_GuestUser() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        driver.manage().window().maximize();
        womensShoesPage.addProductToCart();
        womensShoesPage.guestCheckout();
//        Assert.assertTrue(womensShoesPage.isReviewPopup());
    }
    @Test
    public void WomensShoesPage_ProductPurchasing_LoggedinUser() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        driver.manage().window().maximize();
        womensShoesPage.addProductToCart();
        womensShoesPage.loggedinUserCheckout();
//        Assert.assertTrue(womensShoesPage.isReviewPopup());
    }
}
