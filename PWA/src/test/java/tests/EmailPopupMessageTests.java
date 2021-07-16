package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EmailPopupWindow;
import pages.PLPPage;
import pages.WomensShoesPage;

public class EmailPopupMessageTests extends BaseTest {
    @BeforeMethod
    public void openHomepage() {
        driver.get("https://shoebacca.com");
    }
    @Test
    public void EmailPopupWindow_EmailPopupAppears_C138() {
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        Assert.assertTrue(emailPopupWindow.isEmailPopup());
    }
    @Test
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed1_C138() {
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.closeEmailPopup();
        Assert.assertFalse(emailPopupWindow.isGrayFieldAroundPopup());
    }
    @Test
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed2_C138() {
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        emailPopupWindow.clickOutside();
        Assert.assertFalse(emailPopupWindow.isGrayFieldAroundPopup());
    }
    @Test
    public void EmailPopupWindow_EmailPopupAppears_EmailPopupClosed3_C139() {
        EmailPopupWindow emailPopupWindow = new EmailPopupWindow(driver);
        driver.manage().window().maximize();
        emailPopupWindow.clickSearchBar();
        Assert.assertFalse(emailPopupWindow.suggestionsExist());
    }
}
