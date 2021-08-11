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
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        CheckoutPage checkoutPage = pdpPage.guestCheckout();
        checkoutPage.guestContinuesCheckout("lyuba.b@shoebacca.com","Lyuba","Bal","2205 E Pioneer Dr","Irving","Texas","75061","1234567890","4444333322221111","12/24","999");
        Assert.assertTrue(checkoutPage.isReviewPopup());
    }
    @Test
    public void WomensShoesPage_ProductPurchasing_LoggedinUser() {
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        CheckoutPage checkoutPage = pdpPage.login("test115@gmail.com","!L654321");
        checkoutPage.loggedinUserCheckout("4444333322221111","12/24","999");
        Assert.assertTrue(checkoutPage.isReviewPopup());
    }
}
