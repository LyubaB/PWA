package tests;

import listeners.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class MiniCartTests extends BaseTest {
    Logger logger = LogManager.getLogger(MiniCartTests.class);
    @BeforeMethod
    public void openWomensShoesPage() {
        driver.get("https://shoebacca.com/womens-shoes.html");
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
    }
    @Test
    public void Header_MiniCart_C142() {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.minicartExist());
        logger.info("Empty Mini Cart exists on Womens Shoes page");
        Assert.assertTrue(basePage.minicartLocation());
        logger.info("Empty Mini Cart is in correct position");
        Assert.assertFalse(basePage.isMiniCartBubble());
        logger.info("No bubble is near Mini Cart");
    }
    @Test
    public void Header_openEmptyMiniCart_C143() {
        BasePage basePage = new BasePage(driver);
        basePage.clickMiniCart();
        Assert.assertTrue(basePage.isMiniCartOpened());
        Assert.assertTrue(basePage.isMiniCartEmpty());
    }
    @Test
    public void Header_openSigninWindowFromMiniCart_C143() {
        BasePage basePage = new BasePage(driver);
        basePage.clickMiniCart();
        basePage.openSigninWindowFromMiniCart();
        Assert.assertTrue(basePage.isSigninPopup());
    }
    @Test
    public void Header_EmptyMiniCartClosed1_C143() {
        BasePage basePage = new BasePage(driver);
        basePage.clickMiniCart();
        basePage.clickMiniCart();
        Assert.assertFalse(basePage.isMiniCartOpened());
    }
    @Test
    public void Header_EmptyMiniCartClosed2_C143() {
        BasePage basePage = new BasePage(driver);
        basePage.clickMiniCart();
        basePage.closeMiniCart();
        Assert.assertFalse(basePage.isMiniCartOpened());
    }
    @Test
    public void Header_EmptyMiniCartClosed3_C143() {
        BasePage basePage = new BasePage(driver);
        basePage.clickMiniCart();
        basePage.clickOutside();
        Assert.assertFalse(basePage.isMiniCartOpened());
    }
    @Test
    public void Header_MiniCartWithProductsOpened_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        pdpPage.clickMiniCart();
        int number = pdpPage.numberInBubble();
        int num = pdpPage.isNumberOfProducts();
        Assert.assertTrue(pdpPage.minicartExist());
        logger.info("Mini Cart with products exists on Womens Shoes page");
        Assert.assertTrue(pdpPage.minicartLocation());
        logger.info("Mini Cart with products is in correct position");
        Assert.assertTrue(pdpPage.isMiniCartBubble());
        logger.info("Bubble exists near Mini Cart");
        Assert.assertEquals(number, num);
        logger.info("Number in bubble is equal to quantity of products at Mini Cart");
    }
    @Test
    public void Header_MiniCartWithProductsDescription_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        pdpPage.clickMiniCart();
        Assert.assertTrue(pdpPage.isImageInMiniCart());
        logger.info("Product image exists at Mini Cart on Womens Shoes page");
        Assert.assertTrue(pdpPage.isBrandInMiniCart());
        logger.info("Product brand exists at Mini Cart on Womens Shoes page");
        Assert.assertTrue(pdpPage.isProductNameInMiniCart());
        logger.info("Product name exists at Mini Cart on Womens Shoes page");
        Assert.assertTrue(pdpPage.isColorInMiniCart());
        logger.info("Product color exists at Mini Cart on Womens Shoes page");
        Assert.assertTrue(pdpPage.isSizeWidthInMiniCart());
        logger.info("Product Size/Width exists at Mini Cart on Womens Shoes page");
        Assert.assertTrue(pdpPage.isPriceInMiniCart());
        logger.info("Product price exists at Mini Cart on Womens Shoes page");
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_MiniCartWithProducts_ImageOpensPDP_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        plpPage = pdpPage.openPLPPage();
        plpPage.clickMiniCart();
        pdpPage = plpPage.openPDPThroughMiniCartImage();
        Assert.assertTrue(pdpPage.isPDPPage());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_MiniCartWithProducts_ProductNameOpensPDP_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        plpPage = pdpPage.openPLPPage();
        plpPage.clickMiniCart();
        pdpPage = plpPage.openPDPThroughMiniCartProductName();
        Assert.assertTrue(pdpPage.isPDPPage());
    }
    @Test
    public void Header_MiniCartWithProducts_ClosedByClickingMiniCard_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        plpPage = pdpPage.openPLPPage();
        plpPage.clickMiniCart();
        plpPage.clickMiniCart();
        Assert.assertFalse(plpPage.isMiniCartOpened());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_MiniCartWithProducts_ClosedByCrossIcon_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        plpPage = pdpPage.openPLPPage();
        plpPage.clickMiniCart();
        plpPage.closeMiniCart();
        Assert.assertFalse(plpPage.isMiniCartOpened());
    }
    @Test
    public void Header_MiniCartWithProducts_ClosedByClickingOutside_C144() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        plpPage = pdpPage.openPLPPage();
        plpPage.clickMiniCart();
        plpPage.clickOutside();
        Assert.assertFalse(plpPage.isMiniCartOpened());
    }
    @Test
    public void Header_MiniCartWithProducts_ScrollBarExist_C145() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        pdpPage.closeMiniCart();
        pdpPage.addProductToCart();
        pdpPage.closeMiniCart();
        pdpPage.clickMiniCart();
        Assert.assertTrue(pdpPage.isScroll());
        }
    @Test
    public void Header_MiniCartWithProducts_ScrollBarWorks_C145() {
        PLPPage plpPage = new PLPPage(driver);
        PDPPage pdpPage = plpPage.openPDPWith2orMoreSizes();
        pdpPage.addProductToCart();
        pdpPage.closeMiniCart();
        pdpPage.addProductToCart();
        pdpPage.closeMiniCart();
        pdpPage.clickMiniCart();
        double position1 = pdpPage.getScrollBarPosition();
        pdpPage.scrollToLastProduct();
        double position2 = pdpPage.getScrollBarPosition();
        Assert.assertTrue(position1 < position2);
        }
    }
