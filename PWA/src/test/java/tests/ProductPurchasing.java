package tests;

import org.testng.annotations.Test;
import pages.WomensShoesPage;

public class ProductPurchasing extends BaseTestForHomePage{
    @Test
    public void WomensShoesPage_ProductPurchasing() {
        WomensShoesPage womensShoesPage = new WomensShoesPage(driver);
        womensShoesPage.open();
        driver.manage().window().maximize();
        womensShoesPage.addProductToCart();
    }
}
