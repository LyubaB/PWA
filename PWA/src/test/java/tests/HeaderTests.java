package tests;

import listeners.RetryAnalyzer;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.awt.*;

public class HeaderTests extends BaseTest {
    @BeforeMethod
    public void openWomensShoesPage() {
        driver.get("https://shoebacca.com/womens-shoes.html");
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
    }
    @Test
    public void Header_Logo_C141() {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.logoExist());
        Assert.assertTrue(basePage.logoLocation());
    }
    @Test
    public void Header_LogoRedirectsToHomepage_C141() {
        BasePage basePage = new BasePage(driver);
        HomePage homePage = basePage.openHomePage();
        Assert.assertTrue(homePage.isHomePage());
    }
    @Test
    public void Header_ReturnShippingBanersExist_C146() {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isFreeShippingBanner());
        Assert.assertTrue(basePage.isReturnPolicyBanner());
        Assert.assertTrue(basePage.isPriceMatchBanner());
    }
    @Test
    public void Header_ReturnShippingBanersOnOneLine_C146() {
        BasePage basePage = new BasePage(driver);
        double position1 = basePage.locationFreeShippingBanner();
        double position2 = basePage.locationReturnPolicyBanner();
        double position3 = basePage.locationPriceMatchBanner();
        Assert.assertTrue(position1<=position2+2 || position1>=position2-2);
        Assert.assertTrue(position3<=position2+2 || position3>=position2-2);
    }
    @Test
    public void Header_ReturnShippingBanersUnderMegaMenu_C146() {
        BasePage basePage = new BasePage(driver);
        double position1 = basePage.locationFreeShippingBanner();
        double position2 = basePage.locationLogoY();
        Assert.assertTrue(position1>position2);
    }
    @Test
    public void Header_FreeShippingOpensGuaranteesPoliciesPage_C146() {
        BasePage basePage = new BasePage(driver);
        GuaranteesPoliciesPage guaranteesPoliciesPage = basePage.openGuaranteesPoliciesPage1();
        Assert.assertTrue(guaranteesPoliciesPage.isGuaranteesPoliciesPage());
    }
    @Test
    public void Header_ReturnPolicyOpensGuaranteesPoliciesPage_C146() {
        BasePage basePage = new BasePage(driver);
        GuaranteesPoliciesPage guaranteesPoliciesPage = basePage.openGuaranteesPoliciesPage2();
        Assert.assertTrue(guaranteesPoliciesPage.isGuaranteesPoliciesPage());
    }
    @Test
    public void Header_PriceMatchOpensGuaranteesPoliciesPage_C146() {
        BasePage basePage = new BasePage(driver);
        GuaranteesPoliciesPage guaranteesPoliciesPage = basePage.openGuaranteesPoliciesPage3();
        Assert.assertTrue(guaranteesPoliciesPage.isGuaranteesPoliciesPage());
    }
    @Test
    public void Header_MegaMenuExists_C147() {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isMegaMenu());
    }
    @Test
    public void Header_MegaMenuLinksOpenRelevantPLPs_C147() {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.allPLPCorrect());
    }
    @Test
    public void Header_BrandsMegaMenuOpensBrandsPage_C147() {
        BasePage basePage = new BasePage(driver);
        BrandsPage brandsPage = basePage.openBrandsPage();
        Assert.assertTrue(brandsPage.isBrandsPage());
    }
    @Test
    public void Header_ShopsMegaMenuOpensShopsPage_C147() {
        BasePage basePage = new BasePage(driver);
        ShopsPage shopsPage = basePage.openShopsPage();
        Assert.assertTrue(shopsPage.isShopsPage());
    }
    @Test
    public void Header_ClearanceMegaMenuOpensClearancePage_C147() {
        BasePage basePage = new BasePage(driver);
        ClearancePage clearancePage = basePage.openClearancePage();
        Assert.assertTrue(clearancePage.isClearancePage());
    }
    @Test
    public void Header_HoveredCategoriesUnderlined_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.underlinePresent());
    }
    @Test
    public void Header_MegaMenuSubcategoriesLinksOpenRelevantPLPs_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.allMegaMenuPLPCorrect());
    }
    @Test
    public void Header_ViewAllBrandsLinksOpenRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.popularBrandsPageCorrect());
    }
    @Test
    public void Header_ApparelMegaMenuOpensRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isApparelPage());
    }
    @Test
    public void Header_TrendingNowSubmenuWomenMenKidsOpensRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.trendingNowMegaMenuPLPWomenMenKidsCorrect());
    }
    @Test
    public void Header_AllSubcategoriesShopsMegaMenuOpenRelevantPLPs_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(basePage.shopsSubmenuPagesCorrect());
        soft.assertTrue(basePage.shopsSubmenuPagesCorrect2());
        soft.assertAll();
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_AllSubcategoriesClearanceMegaMenuOpenRelevantPLPs_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(basePage.clearanceSubmenuPagesCorrect());
        soft.assertTrue(basePage.clearanceSubmenuPagesCorrect2());
        soft.assertAll();
    }
    @Test
    public void Header_BrandsLogosMegaMenuOpensRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(basePage.brandsSubmenuPagesCorrect());
    }
    @Test
    public void Header_BrandsAlphabetMegaMenuOpensRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(basePage.brandsAlphabetMegaMenuPagesCorrect());
    }
    @Test
    public void Header_SeeAllBrandsButtonMegaMenuOpensRelevantPLP_C147() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        BrandsPage brandsPage = basePage.openBrandsPage3();
        Assert.assertTrue(brandsPage.isBrandsPage());
    }

}


