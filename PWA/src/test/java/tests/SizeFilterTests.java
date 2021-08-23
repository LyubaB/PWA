package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.EmailPopupWindow;
import pages.PLPPage;
import pages.WomensShoesPage;

public class SizeFilterTests extends BaseTest {
    Logger logger = LogManager.getLogger(MiniCartTests.class);
    @BeforeMethod
    public void openWomensShoesPage() {
//        driver.get("https://shoebacca.com/womens-shoes.html");
//        driver.get("https://shoebacca.com/mens-shoes.html");
//        driver.get("https://shoebacca.com/kids-shoes.html");
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.openSTG();
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
    }
    @Test
    public void PLP_SizesFiltering_CorrectProductsFiltered() {
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(plpPage.sizeCheck());
    }
    @Test
    public void PLP_WidthFiltering_CorrectProductsFiltered() {
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(plpPage.widthCheck());
    }
    @Test
    public void PLP_SizesWidthsFiltering_ProductAppearsInCorrectSizes(){
        PLPPage plpPage = new PLPPage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(plpPage.finalSizeWidthCheck());
    }
}
