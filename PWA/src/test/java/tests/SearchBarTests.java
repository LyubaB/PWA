package tests;

import helpers.TestData;
import listeners.RetryAnalyzer;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.EmailPopupWindow;
import pages.SearchResultsPage;

public class SearchBarTests extends BaseTest {
    @BeforeMethod
    public void closePopup() {
        driver.get("https://shoebacca.com");
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
    }
    @Test
    public void Header_SearchBarForBiggerScreen_ProvidesSuggestion_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        basePage.useSearchQueryForBiggerScreen1("sp");
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(basePage.topSuggestionsExist());
        Assert.assertTrue(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForSmallerScreen_ProvidesSuggestion_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
        capabilities.setCapability("opera.arguments", "-screenwidth 1100 -screenheight 600");
        basePage.useSearchQueryForSmallerScreen1("sp");
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(basePage.topSuggestionsExist());
        Assert.assertTrue(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void Header_SearchBarForBiggerScreen_ProvidesNewSuggestions_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        basePage.useSearchQueryForBiggerScreen2("spr");
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist2());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist2());
        Assert.assertFalse(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertFalse(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForSmallerScreen_ProvidesNewSuggestions_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        basePage.useSearchQueryForSmallerScreen2("spr");
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist2());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist2());
        Assert.assertFalse(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertFalse(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForBiggerScreen_DeleteOneLetter_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        basePage.useSearchQueryForBiggerScreen2("spr");
        basePage.deleteOneLetterFromSearch();
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(basePage.topSuggestionsExist());
        Assert.assertTrue(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForSmallerScreen_DeleteOneLetter_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        basePage.useSearchQueryForSmallerScreen2("spr");
        basePage.deleteOneLetterFromSearch();
        Assert.assertTrue(basePage.suggestionsExist());
        Assert.assertTrue(basePage.boldAlphabetsForSuggestionsExist1());
        Assert.assertTrue(basePage.popularBrandsExist());
        Assert.assertTrue(basePage.boldAlphabetsForPopularBrandsExist1());
        Assert.assertTrue(basePage.topSuggestionsExist());
        Assert.assertTrue(basePage.sixthLineForSuggestionsSearchExist());
        Assert.assertTrue(basePage.sixthLineForPopularBrandsSearchExist());
    }
    @Test
    public void Header_SearchBarForBiggerScreen_gotSearchResult1_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        String search3 = TestData.randomString(2);
        SearchResultsPage searchResultsPage = basePage.openSearchResultsPageForBiggerScreen1(search3);
        String query1 = searchResultsPage.query();
        String query2 = searchResultsPage.checkQuery();
        Assert.assertTrue(searchResultsPage.isSearchResultsPage());
        Assert.assertEquals(query1, search3);
        Assert.assertEquals(query2, search3);
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForSmallerScreen_gotSearchResult1_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        String search3 = TestData.randomString(2);
        SearchResultsPage searchResultsPage = basePage.openSearchResultsPageForSmallerScreen1(search3);
        String query1 = searchResultsPage.query();
        String query2 = searchResultsPage.checkQuery();
        Assert.assertTrue(searchResultsPage.isSearchResultsPage());
        Assert.assertEquals(query1, search3);
        Assert.assertEquals(query2, search3);
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Header_SearchBarForBiggerScreen_gotSearchResult2_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().maximize();
        String search3 = TestData.randomString(2);
        SearchResultsPage searchResultsPage = basePage.openSearchResultsPageForBiggerScreen2(search3);
        String query1 = searchResultsPage.query();
        String query2 = searchResultsPage.checkQuery();
        Assert.assertTrue(searchResultsPage.isSearchResultsPage());
        Assert.assertEquals(query1, search3);
        Assert.assertEquals(query2, search3);
    }
    @Test
    public void Header_SearchBarForSmallerScreen_gotSearchResult2_C140() {
        BasePage basePage = new BasePage(driver);
        driver.manage().window().setSize(new Dimension(1100, 600));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("screen-resolution", "1100x600");
        String search3 = TestData.randomString(2);
        SearchResultsPage searchResultsPage = basePage.openSearchResultsPageForSmallerScreen2(search3);
        String query1 = searchResultsPage.query();
        String query2 = searchResultsPage.checkQuery();
        Assert.assertTrue(searchResultsPage.isSearchResultsPage());
        Assert.assertEquals(query1, search3);
        Assert.assertEquals(query2, search3);
    }

}
