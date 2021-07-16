package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.PDPPage;
import pages.PLPPage;
import pages.WomensShoesPage;

public class ProductPurchasingTests extends BaseTest {
    @BeforeMethod
    public void openWomensShoesPage() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.openSTG();
    }
    @Test
    public void WomensShoesPage_ProductPurchasing_GuestUser() {
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        PDPPage pdpPage = plpPage.openPDP();
        pdpPage.addProductToCart();
        CheckoutPage checkoutPage = pdpPage.guestCheckout();
        checkoutPage.guestContinuesCheckout();
        Assert.assertTrue(checkoutPage.isReviewPopup());
    }
    @Test
    public void WomensShoesPage_ProductPurchasing_LoggedinUser() {
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        PDPPage pdpPage = plpPage.openPDP();
        pdpPage.addProductToCart();
        CheckoutPage checkoutPage = pdpPage.login();
        checkoutPage.loggedinUserCheckout();
        Assert.assertTrue(checkoutPage.isReviewPopup());
    }
}
