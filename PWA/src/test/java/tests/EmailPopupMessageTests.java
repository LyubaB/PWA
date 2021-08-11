package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EmailPopupWindow;

public class EmailPopupMessageTests extends BaseTest {
    @DataProvider(name="Links")
    public Object[][] createData(){
        return new Object[][]{
                {"https://www.shoebacca.com/"},
                {"https://www.shoebacca.com/womens-shoes.html"},
                {"https://www.shoebacca.com/mens-shoes.html"}
        };
    }
    @Test(dataProvider = "Links")
    public void EmailPopupWindow_EmailPopupAppears_C138(String link) {
        driver.get(link);
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        Assert.assertTrue(emailPopupWindow.isEmailPopup());
    }
    @Test(dataProvider = "Links")
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed1_C138(String link) {
        driver.get(link);
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
        Assert.assertFalse(emailPopupWindow.isGrayFieldAroundPopup());
    }
    @Test(dataProvider = "Links")
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed2_C138(String link) {
        driver.get(link);
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.clickOutside();
        Assert.assertFalse(emailPopupWindow.isGrayFieldAroundPopup());
    }
    @Test(dataProvider = "Links")
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed3_C139(String link) {
        driver.get(link);
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        driver.manage().window().maximize();
        emailPopupWindow.clickSearchBar();
        Assert.assertFalse(emailPopupWindow.suggestionsExist());
    }
}
