package tests;

import helpers.TestData;
import listeners.RetryAnalyzer;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WomensShoesPage;

public class HomePageTests extends BaseTestForHomePage {
    @BeforeMethod
    public void closePopup() {
        driver.get("https://shoebacca.com");
        HomePage homePage = new HomePage(driver);
        homePage.closeEmailPopup();
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void HomePage_SearchBarForBiggerScreen_ProvidesSuggestion_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        homePage.useSearchQueryForBiggerScreen1("sp");
        Assert.assertTrue(homePage.suggestionsExist());
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(homePage.popularBrandsExist());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(homePage.topSuggestionsExist());
        Assert.assertTrue(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void HomePage_SearchBarForSmallerScreen_ProvidesSuggestion_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
        capabilities.setCapability("opera.arguments", "-screenwidth 1100 -screenheight 600");
        homePage.useSearchQueryForSmallerScreen1("sp");
        Assert.assertTrue(homePage.suggestionsExist());
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(homePage.popularBrandsExist());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(homePage.topSuggestionsExist());
        Assert.assertTrue(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void HomePage_SearchBarForBiggerScreen_ProvidesNewSuggestions_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        homePage.useSearchQueryForBiggerScreen2("spr");
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist2());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist2());
        Assert.assertFalse(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertFalse(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void HomePage_SearchBarForSmallerScreen_ProvidesNewSuggestions_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        homePage.useSearchQueryForSmallerScreen2("spr");
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist2());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist2());
        Assert.assertFalse(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertFalse(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void HomePage_SearchBarForBiggerScreen_DeleteOneLetter_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        homePage.useSearchQueryForBiggerScreen2("spr");
        homePage.deleteOneLetterFromSearch();
        Assert.assertTrue(homePage.suggestionsExist());
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(homePage.popularBrandsExist());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(homePage.topSuggestionsExist());
        Assert.assertTrue(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void HomePage_SearchBarForSmallerScreen_DeleteOneLetter_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        homePage.useSearchQueryForSmallerScreen2("spr");
        homePage.deleteOneLetterFromSearch();
        Assert.assertTrue(homePage.suggestionsExist());
        Assert.assertTrue(homePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(homePage.popularBrandsExist());
        Assert.assertTrue(homePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(homePage.topSuggestionsExist());
        Assert.assertTrue(homePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(homePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void HomePage_SearchBarForBiggerScreen_gotSearchResult1_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        String search3 = TestData.randomString(2);
        String query = homePage.searchQueryForBiggerScreen1(search3);
        homePage.checkQuery(search3);
        Assert.assertEquals(query, search3);
        Assert.assertTrue(homePage.checkQuery(search3));
    }
    @Test
    public void HomePage_SearchBarForSmallerScreen_gotSearchResult1_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        String search3 = TestData.randomString(2);
        String query = homePage.searchQueryForSmallerScreen1(search3);
        homePage.checkQuery(search3);
        Assert.assertEquals(query, search3);
        Assert.assertTrue(homePage.checkQuery(search3));
    }
    @Test
    public void HomePage_SearchBarForBiggerScreen_gotSearchResult2_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        String search3 = TestData.randomString(2);
        String query = homePage.searchQueryForBiggerScreen2(search3);
        homePage.checkQuery(search3);
        Assert.assertEquals(query, search3);
        Assert.assertTrue(homePage.checkQuery(search3));
    }
    @Test
    public void HomePage_SearchBarForSmallerScreen_gotSearchResult2_C140() {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        String search3 = TestData.randomString(2);
        String query = homePage.searchQueryForSmallerScreen2(search3);
        homePage.checkQuery(search3);
        Assert.assertEquals(query, search3);
        Assert.assertTrue(homePage.checkQuery(search3));
    }
}
