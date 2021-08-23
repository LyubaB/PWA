package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;
    protected WebDriverWait wait;
    Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        fluentWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .withMessage("Find Exception");
        wait = new WebDriverWait(driver, 20);


    }

    private WebElement getSearchBar() {
        return driver.findElement(By.name("search_query"));
    }

    public void clickSearchBar() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
    }

    public void useSearchQueryForBiggerScreen1(String search1) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        clickSearchBar();
        getSearchBar().sendKeys(search1);
    }

    private WebElement getSuggestionsSearch() {
        return driver.findElement(By.xpath("//h4[text()=\"SUGGESTIONS\"]"));
    }

    public boolean suggestionsExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getSuggestionsSearch()));
            getSuggestionsSearch().isDisplayed();
            logger.info("Suggestions for search are shown");
        } catch (NoSuchElementException suggestions) {
            logger.error("Suggestions for search are not shown");
            return false;
        }
        return true;
    }

    public boolean boldAlphabetsForSuggestionsExist1() {
        var list = driver.findElements(By.xpath("//a/span/*[contains(@class,'highlighted') and text()='sp']"));
        logger.info("Expecting to see bold alphabets in 6 lines for Suggestions");
        return list.size() == 6;
    }

    private WebElement getPopularBrandsSearch() {
        return driver.findElement(By.xpath("//h4[text()=\"POPULAR BRANDS\"]"));
    }

    public boolean popularBrandsExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getPopularBrandsSearch()));
            getPopularBrandsSearch().isDisplayed();
            logger.info("Popular Brands for search are shown");
        } catch (NoSuchElementException suggestions) {
            logger.error("Popular Brands for search are not shown");
            return false;
        }
        return true;
    }

    public boolean boldAlphabetsForPopularBrandsExist1() {
        var list = driver.findElements(By.xpath("//a/span/*[contains(@class,'highlighted') and text()='Sp']"));
        logger.info("Expecting to see bold alphabets in 6 lines for Popular Brands");
        return list.size() == 6;
    }

    private WebElement getTopSuggestionsSearch() {
        return driver.findElement(By.xpath("//h4[text()=\"Top Suggestions\"]"));
    }

    public boolean topSuggestionsExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getTopSuggestionsSearch()));
            getTopSuggestionsSearch().isDisplayed();
            logger.info("Top Suggestions for search are shown");
        } catch (NoSuchElementException suggestions) {
            logger.error("Top Suggestions for search are not shown");
            return false;
        }
        return true;
    }

    private WebElement getSixthLineForSuggestionsSearch() {
        return driver.findElement(By.xpath("//*[contains(@id,\"section-0-item-5\")]"));
    }

    public boolean sixthLineForSuggestionsSearchExist() {
        try {
            Thread.sleep(2000);
            getSixthLineForSuggestionsSearch().isDisplayed();
            logger.info("Sixth line for Suggestions search exists");
        } catch (NoSuchElementException | StaleElementReferenceException | InterruptedException suggestions) {
            logger.error("Sixth line for Suggestions search doesn't exist");
            return false;
        }
        return true;
    }

    private WebElement getSixthLineForPopularBrandsSearch() {
        return driver.findElement(By.xpath("//*[contains(@id,\"section-2-item-5\")]"));
    }

    public boolean sixthLineForPopularBrandsSearchExist() {
        try {
            Thread.sleep(2000);
            getSixthLineForPopularBrandsSearch().isDisplayed();
            logger.info("Sixth line for Popular Brands search exists");
        } catch (NoSuchElementException | StaleElementReferenceException | InterruptedException suggestions) {
            logger.error("Sixth line for Popular Brands search doesn't exist");
            return false;
        }
        return true;
    }

    private WebElement getMagnifier() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='icon-search']")));
        return driver.findElement(By.cssSelector("[class='icon-search']"));
    }

    public void useSearchQueryForSmallerScreen1(String search1) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        wait.until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        getSearchBar().click();
        getSearchBar().sendKeys(search1);
    }

    public void useSearchQueryForBiggerScreen2(String search2) {
        wait.until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        clickSearchBar();
        getSearchBar().sendKeys(search2);
    }

    public boolean boldAlphabetsForSuggestionsExist2() {
        var list = driver.findElements(By.xpath("//*[contains(@class,\"highlighted\") and text()=\"spr\"]"));
        logger.info("Expecting to see bold alphabets in less than 6 lines for Suggestions");
        return list.size() < 6;
    }

    public boolean boldAlphabetsForPopularBrandsExist2() {
        var list = driver.findElements(By.xpath("//*[contains(@class,\"highlighted\") and text()=\"Spr\"]"));
        logger.info("Expecting to see bold alphabets in less than 6 lines for Popular Brands");
        return list.size() < 6;
    }

    public void useSearchQueryForSmallerScreen2(String search2) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        wait.until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        getSearchBar().click();
        getSearchBar().sendKeys(search2);
    }

    public void deleteOneLetterFromSearch() {
        getSearchBar().sendKeys(Keys.BACK_SPACE);
    }

    public SearchResultsPage openSearchResultsPageForBiggerScreen1(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
        getSearchBar().sendKeys(search3);
        getMagnifier().click();
        return new SearchResultsPage(driver);
    }

    private WebElement getMagnifierForSmallerScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-search']")));
        return driver.findElement(By.xpath("//span[@class='icon-search']"));
    }

    public SearchResultsPage openSearchResultsPageForSmallerScreen1(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        clickSearchBar();
        getSearchBar().sendKeys(search3);
        Actions actions = new Actions(driver);
        actions.moveToElement((getMagnifierForSmallerScreen()));
        actions.perform();
        getMagnifierForSmallerScreen().click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage openSearchResultsPageForBiggerScreen2(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        clickSearchBar();
        getSearchBar().sendKeys(search3);
        getSearchBar().sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage openSearchResultsPageForSmallerScreen2(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        clickSearchBar();
        getSearchBar().sendKeys(search3);
        getSearchBar().sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    private WebElement getLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='header-logo-2jr']")));
        return driver.findElement(By.xpath("//a[@class='header-logo-2jr']"));
    }

    public boolean logoExist() {
        var list = driver.findElements(By.xpath("//a[@class='header-logo-2jr']"));
        return list.size() == 1;
    }

    public boolean logoLocation() {
        return getLogo().getLocation().getX() < 30 && getLogo().getLocation().getY() < 105;
    }

    public HomePage openHomePage() {
        getLogo().click();
        return new HomePage(driver);
    }

    private WebElement getCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='button-root_normalPriority-1xS']")));
        return driver.findElement(By.cssSelector("[class='button-root_normalPriority-1xS']"));
    }

    private WebElement getGuestCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='guestCheckout']")));
        return driver.findElement(By.cssSelector("[class='guestCheckout']"));
    }

    public CheckoutPage guestCheckout() {
        getCheckoutButton().click();
        getGuestCheckoutButton().click();
        return new CheckoutPage(driver);
    }

    private WebElement getMemberCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".memberCheckoutBtn")));
        return driver.findElement(By.cssSelector(".memberCheckoutBtn"));
    }

    private WebElement getEmailAddressField() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        return driver.findElement(By.cssSelector("[type='email']"));
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.cssSelector("[type='password']"));
    }

    private WebElement getSigninButton() {
        return driver.findElement(By.xpath("//button/*[text()='Sign In']"));
    }

    public CheckoutPage login(String email, String password) {
        getCheckoutButton().click();
        getMemberCheckoutButton().click();
        getEmailAddressField().click();
        getEmailAddressField().sendKeys(email);
        getPasswordField().sendKeys(password);
        getSigninButton().click();
        return new CheckoutPage(driver);
    }

    private WebElement getMiniCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='cartTrigger-trigger-1hC']")));
        return driver.findElement(By.cssSelector("[class='cartTrigger-trigger-1hC']"));
    }

    public boolean minicartExist() {
        var list = driver.findElements(By.cssSelector("[class='icon-cart cartTrigger-cartIcon-oTF']"));
        return list.size() == 1;
    }

    public boolean minicartLocation() {
        return getMiniCartIcon().getLocation().getX() > 900 && getMiniCartIcon().getLocation().getY() < 105;
    }

    public boolean isMiniCartBubble() {
        var list = driver.findElements(By.cssSelector("[class='cartTrigger-counter-1Fo']"));
        return list.size() == 1;
    }

    public void clickMiniCart() {
        getMiniCartIcon().click();
    }

    public boolean isMiniCartOpened() {
        var list = driver.findElements(By.xpath("//*[@class='miniCart-contents_open-ubc']"));
        return list.size() == 1;
    }

    public boolean isMiniCartEmpty() {
        var list = driver.findElements(By.xpath("//*[text()='YOUR CART IS EMPTY']"));
        return list.size() == 1;
    }

    private WebElement getLoginButtonMiniCart() {
        return driver.findElement(By.xpath("//*[text()='Log in']"));
    }

    public void openSigninWindowFromMiniCart() {
        getLoginButtonMiniCart().click();
    }

    public boolean isSigninPopup() {
        var list = driver.findElements(By.cssSelector("[class='signIn-title-zrC']"));
        return list.size() == 1;
    }

    private WebElement getCrossButtonMiniCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside/div/div//*[@class='icon-root-2Hz']")));
        return driver.findElement(By.xpath("//aside/div/div//*[@class='icon-root-2Hz']"));
    }

    public void closeMiniCart() {
        getCrossButtonMiniCart().click();
    }

    public void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }

    private WebElement getBubble() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='cartTrigger-counter-1Fo']")));
        return driver.findElement(By.cssSelector("[class='cartTrigger-counter-1Fo']"));
    }

    public int numberInBubble() {
        String number = getBubble().getText();
        return Integer.parseInt(number);
    }

    public int isNumberOfProducts() {
        var list = driver.findElements(By.cssSelector("[class='item-root-18B']"));
        return list.size();
    }

    public boolean isImageInMiniCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='undefined image-container-2jo']")));
        var list = driver.findElements(By.cssSelector("[class='undefined image-container-2jo']"));
        return list.size() >= 1;
    }

    public boolean isBrandInMiniCart() {
        var list = driver.findElements(By.cssSelector("[class='item-brandName-1JN']"));
        return list.size() >= 1;
    }

    public boolean isProductNameInMiniCart() {
        var list = driver.findElements(By.cssSelector("[class='item-name-1dB']"));
        return list.size() >= 1;
    }

    public boolean isColorInMiniCart() {
        var list = driver.findElements(By.cssSelector("[class='item-colorName-1xx']"));
        return list.size() >= 1;
    }

    public boolean isSizeWidthInMiniCart() {
        var list = driver.findElements(By.cssSelector("[class='item-options-1pj']"));
        return list.size() >= 1;
    }

    public boolean isPriceInMiniCart() {
        var list = driver.findElements(By.cssSelector("[class='item-priceBundle-2l0']"));
        return list.size() >= 1;
    }

    private WebElement getImageInMiniCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='undefined image-container-2jo']")));
        return driver.findElement(By.cssSelector("[class='undefined image-container-2jo']"));
    }

    public PDPPage openPDPThroughMiniCartImage() {
        getImageInMiniCart().click();
        return new PDPPage(driver);
    }

    private WebElement getProductNameInMiniCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='item-name-1dB']")));
        return driver.findElement(By.cssSelector("[class='item-name-1dB']"));
    }

    public PDPPage openPDPThroughMiniCartProductName() {
        getProductNameInMiniCart().click();
        return new PDPPage(driver);
    }

    private WebElement getMegaMenuButtonHeader() {
        return driver.findElement(By.cssSelector("[class='menuItem-menuLevel1-3yq']"));
    }

    public PLPPage openPLPPage() {
        getMegaMenuButtonHeader().click();
        return new PLPPage(driver);
    }

    public boolean isScroll() {
        var list = driver.findElements(By.xpath("//*[@class='productList-items_container-2q_']//*[contains(@style,'overflow: scroll')]"));
        return list.size() >= 1;
    }

    private WebElement getLastRemoveButton() {
        return driver.findElement(By.xpath("(//*[@class='item-deleteButton-2XH'])[2]"));
    }

    public void scrollToLastProduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getLastRemoveButton());
    }

    public double getScrollBarPosition() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='productList-items_container-2q_']//*[contains(@style,'transform')]")));
        String str = driver.findElement(By.xpath("//*[@class='productList-items_container-2q_']//*[contains(@style,'translateY')]")).getAttribute("style").split("Y")[1];
        String position = str.substring(1, str.length() - 4);
        return Double.parseDouble(position);
    }

    private WebElement getFreeShippingBanner() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Free Shipping']")));
        return driver.findElement(By.xpath("//*[text()='Free Shipping']"));
    }

    public boolean isFreeShippingBanner() {
        var list = driver.findElements(By.xpath("//*[text()='Free Shipping']"));
        return list.size() >= 1;
    }

    private WebElement getReturnPolicyBanner() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='365-day Return Policy']")));
        return driver.findElement(By.xpath("//*[text()='365-day Return Policy']"));
    }

    public boolean isReturnPolicyBanner() {
        var list = driver.findElements(By.xpath("//*[text()='365-day Return Policy']"));
        return list.size() >= 1;
    }

    private WebElement getPriceMatchBanner() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='110% Price Match']")));
        return driver.findElement(By.xpath("//*[text()='110% Price Match']"));
    }

    public boolean isPriceMatchBanner() {
        var list = driver.findElements(By.xpath("//*[text()='110% Price Match']"));
        return list.size() >= 1;
    }

    public double locationFreeShippingBanner() {
        return getFreeShippingBanner().getLocation().getY();
    }

    public double locationReturnPolicyBanner() {
        return getReturnPolicyBanner().getLocation().getY();
    }

    public double locationPriceMatchBanner() {
        return getPriceMatchBanner().getLocation().getY();
    }

    public double locationLogoY() {
        return getLogo().getLocation().getY();
    }

    public GuaranteesPoliciesPage openGuaranteesPoliciesPage1() {
        getFreeShippingBanner().click();
        return new GuaranteesPoliciesPage(driver);
    }

    public GuaranteesPoliciesPage openGuaranteesPoliciesPage2() {
        getReturnPolicyBanner().click();
        return new GuaranteesPoliciesPage(driver);
    }

    public GuaranteesPoliciesPage openGuaranteesPoliciesPage3() {
        getPriceMatchBanner().click();
        return new GuaranteesPoliciesPage(driver);
    }

    public boolean isMegaMenu() {
        var list = driver.findElements(By.cssSelector("[class='megaMenu-root-2gx']"));
        return list.size() >= 1;
    }

    public WebElement getCurrentBreadcrumbs() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'breadcrumbs-current')]")));
        return driver.findElement(By.xpath("//*[contains(@class,'breadcrumbs-current')]"));
    }

    public boolean allPLPCorrect() {
        for (int i = 0; i < 5; i++) {
            var list = driver.findElements(By.cssSelector("[class='menuItem-menuLevel1-3yq']"));
            list.get(i).click();
            if (!list.get(i).getText().contains(getCurrentBreadcrumbs().getText())) {
                return false;
            }

        }
        return true;
    }

    private WebElement getBrandsMegaMenu() {
        return driver.findElement(By.cssSelector("[href='/brand.html']"));
    }

    private WebElement getShopsMegaMenu() {
        return driver.findElement(By.cssSelector("[href='/shops.html']"));
    }

    private WebElement getClearanceMegaMenu() {
        return driver.findElement(By.cssSelector("[href='/clearance-warehouse.html']"));
    }

    public BrandsPage openBrandsPage() {
        getBrandsMegaMenu().click();
        return new BrandsPage(driver);
    }

    public ShopsPage openShopsPage() {
        getShopsMegaMenu().click();
        return new ShopsPage(driver);
    }

    public ClearancePage openClearancePage() {
        getClearanceMegaMenu().click();
        return new ClearancePage(driver);
    }

    private WebElement getEntireBreadcrumbs() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='breadcrumbs-root-3O-']")));
        return driver.findElement(By.cssSelector("[class='breadcrumbs-root-3O-']"));
    }

    private WebElement getCategoryHeading() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='category-heading-33G']")));
        return driver.findElement(By.cssSelector("[class='category-heading-33G']"));
    }

    private List<WebElement> getlist1() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='menuItem-menuLevel1-3yq']/a")));
        var list = driver.findElements(By.xpath("//*[@class='menuItem-menuLevel1-3yq']/a"));
        return list;
    }

    private WebElement getHoverUnderline() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".menuItem-menuLevel1-3yq:hover")));
        return driver.findElement(By.cssSelector(".menuItem-menuLevel1-3yq:hover"));
    }

    public boolean underlinePresent() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        for (int i = 0; i < getlist1().size(); i++) {
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.moveToElement(getlist1().get(i)).perform();
            String redLine = getHoverUnderline().getCssValue("border-bottom-color");
            String text = getlist1().get(i).getText();
            System.out.println(text + " at Mega Menu");
            if (!getHoverUnderline().isDisplayed() || redLine.contains("transparent")) {
                logger.error(getlist1().get(i).getText() + " is failed");
                listOfFailedElements.add(text);
            }
            actions.moveToElement(getlist1().get(i)).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " at Mega Menu are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist2() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()='TRENDING NOW']/preceding::li[@class='megamenu-list-item']/a")));
        var list = driver.findElements(By.xpath("//h4[text()='TRENDING NOW']/preceding::li[@class='megamenu-list-item']/a"));
        return list;
    }

    public boolean allMegaMenuPLPCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.moveToElement(getlist1().get(i)).perform();
            for (int j = 0; j < getlist2().size() - 1; j++) {
                String text = getlist2().get(j).getText();
                if (text.contains("ALL CLOTHING")) {
                } else {
                    if (text.contains("All")) {
                        getlist2().get(j).click();
                        System.out.println(text + " " + getlist1().get(i).getText());
                        if (!getEntireBreadcrumbs().getText().contains(text.substring(4, 7)) && !getCategoryHeading().getText().contains(text.substring(4, 7).toUpperCase())) {
                            logger.error(text + " in " + getlist1().get(i).getText() + " is failed");
                            listOfFailedElements.add(text);
                        }
                    } else {
                        getlist2().get(j).click();
                        System.out.println(text.substring(0, 4) + " " + getlist1().get(i).getText());
                        if (!getEntireBreadcrumbs().getText().contains(text.substring(0, 4)) && !getCategoryHeading().getText().contains(text.substring(0, 4).toUpperCase())) {
                            logger.error(text + " in " + getlist1().get(i).getText() + " is failed");
                            listOfFailedElements.add(text);
                        }
                    }
                }
                actions.moveToElement(getlist1().get(i)).perform();
            }
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " at Mega Menu are failed");
            return false;
        } else {
            return true;
        }
    }

    private WebElement getViewAllBrandsButton() {
        return driver.findElement(By.cssSelector("[href*='/brands']"));
    }

    public void openBrandsPage2() {
        getViewAllBrandsButton().click();
    }

    public boolean popularBrandsPageCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.moveToElement(getlist1().get(i)).perform();
            openBrandsPage2();
            if (!driver.getCurrentUrl().contains("shoebacca.com/brands")) {
                logger.error("Brands in " + getlist1().get(i).getText() + " is failed");
                listOfFailedElements.add(getlist1().get(i).getText());
            }
            driver.navigate().back();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println("View All Brands buttons at " + listOfFailedElements + " (Mega Menu) are failed");
            return false;
        } else {
            return true;
        }
    }

    private WebElement getApparelButtonMegaMenu() {
        return driver.findElement(By.xpath("//a[@href='/apparel.html']"));
    }

    private WebElement getAllClothingButton() {
        return driver.findElement(By.xpath("//*[@class='menuItem-menuLevel1-3yq']//a[contains(@href,'com/apparel.html')]"));
    }

    public boolean isApparelPage() throws InterruptedException {
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getApparelButtonMegaMenu()).perform();
        getAllClothingButton().click();
        return driver.getCurrentUrl().contains("shoebacca.com/apparel");
    }

    private List<WebElement> getlist3() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()='TRENDING NOW']/following::li[@class='megamenu-list-item']/a")));
        var list = driver.findElements(By.xpath("//h4[text()='TRENDING NOW']/following::li[@class='megamenu-list-item']/a"));
        return list;
    }

    public boolean trendingNowMegaMenuPLPWomenMenKidsCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.moveToElement(getlist1().get(i)).perform();
            for (int j = 0; j < getlist3().size(); j++) {
                String text = getlist3().get(j).getText();
                getlist3().get(j).click();
                System.out.println(text.substring(0, 4) + " " + getlist1().get(i).getText());
                if (!getCategoryHeading().getText().contains(text.substring(0, 4).toUpperCase())) {
                    logger.error(text + " in " + getlist1().get(i).getText() + " is failed");
                    listOfFailedElements.add(text);
                }
                actions.moveToElement(getlist1().get(i)).perform();
            }
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Trending Now (Women, Men, Kids) are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist4() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='megamenu-list-item']/a")));
        var list = driver.findElements(By.xpath("//*[@class='megamenu-list-item']/a"));
        return list;
    }

    public boolean shopsSubmenuPagesCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getShopsMegaMenu()).perform();
        for (int i = 0; i < getlist4().size() - 4; i++) {
            String text = getlist4().get(i).getText();
            getlist4().get(i).click();
            System.out.println(text.substring(0, 6) + " in Shops");
            if (!getEntireBreadcrumbs().getText().contains(text.substring(0, 6)) && !getCategoryHeading().getText().contains(text.substring(0, 6).toUpperCase())) {
                logger.error(getlist4().get(i).getText() + " is failed");
                listOfFailedElements.add(text);
            }
            actions.moveToElement(getShopsMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Shops are failed");
            return false;
        } else {
            return true;
        }
    }

    public boolean shopsSubmenuPagesCorrect2() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getShopsMegaMenu()).perform();
        for (int i = getlist4().size() - 3; i < getlist4().size(); i++) {
            String text = getlist4().get(i).getText();
            getlist4().get(i).click();
            System.out.println(text.substring(0, 6) + " in Shops");
            if (!getEntireBreadcrumbs().getText().contains(text.substring(0, 6)) && !getCategoryHeading().getText().contains(text.substring(0, 6).toUpperCase())) {
                logger.error(getlist4().get(i).getText() + " is failed");
                listOfFailedElements.add(text);
            }
            actions.moveToElement(getShopsMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Shops are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist5() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CLEARANCE CLOTHING']/preceding::li[@class='megamenu-list-item']/a")));
        var list = driver.findElements(By.xpath("//*[text()='CLEARANCE CLOTHING']/preceding::li[@class='megamenu-list-item']/a"));
        return list;
    }

    public boolean clearanceSubmenuPagesCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getClearanceMegaMenu()).perform();
        for (int i = 0; i < getlist5().size(); i++) {
            String text = getlist5().get(i).getText();
            String id = getlist5().get(i).getAttribute("id");
            getlist5().get(i).click();
            if (!text.contains("All")) {
                System.out.println(text + " " + id);
                if (!getEntireBreadcrumbs().getText().contains(text) && !getEntireBreadcrumbs().getText().contains(getClearanceMegaMenu().getText())) {
                    logger.error(text + " (" + id + ") in Clearance is failed");
                    listOfFailedElements.add(text);
                }
            } else {
                System.out.println(text + " " + id);
                if (!getEntireBreadcrumbs().getText().contains(text.substring(4, 7)) && !getEntireBreadcrumbs().getText().contains(getClearanceMegaMenu().getText())) {
                    logger.error(text + " (" + id + ") in Clearance is failed");
                    listOfFailedElements.add(text);
                }
            }
            if (id.contains("clearance-women")) {
                if (!getEntireBreadcrumbs().getText().contains("Women's Shoes")) {
                    logger.error(text + " (" + id + "!) in Clearance is failed");
                    listOfFailedElements.add(text);
                }
            }
            if (id.contains("clearance-men")) {
                if (!getEntireBreadcrumbs().getText().contains("Men's Shoes")) {
                    logger.error(text + " (" + id + "!) in Clearance is failed");
                    listOfFailedElements.add(text);
                }
            }
            actions.moveToElement(getClearanceMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Clearance are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist6() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CLEARANCE CLOTHING']/following::li[@class='megamenu-list-item']/a")));
        var list = driver.findElements(By.xpath("//*[text()='CLEARANCE CLOTHING']/following::li[@class='megamenu-list-item']/a"));
        return list;
    }

    public boolean clearanceSubmenuPagesCorrect2() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getClearanceMegaMenu()).perform();
        for (int i = 0; i < getlist6().size(); i++) {
            String text = getlist6().get(i).getText();
            getlist6().get(i).click();
            if (!text.contains("Clothing")) {
                System.out.println(text + " in Clearance Clothing");
                if (!getCategoryHeading().getText().contains(text.substring(0, 4).toUpperCase()) || !getEntireBreadcrumbs().getText().contains(getClearanceMegaMenu().getText())) {
                    logger.error(text + "  in Clearance Clothing is failed");
                    listOfFailedElements.add(text);
                }
            } else {
                System.out.println(text + " in Clearance Clothing");
                if (!getCategoryHeading().getText().contains("CLOTHING") || !getEntireBreadcrumbs().getText().contains(getClearanceMegaMenu().getText())) {
                    logger.error(text + " in Clearance Clothing is failed");
                    listOfFailedElements.add(text);
                }
            }
            actions.moveToElement(getClearanceMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Clearance are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist7() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ca-item-main']/a")));
        var list = driver.findElements(By.xpath("//*[@class='ca-item-main']/a"));
        return list;
    }

    public boolean brandsSubmenuPagesCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getBrandsMegaMenu()).perform();
        for (int i = 0; i < getlist7().size(); i++) {
            String url = getlist7().get(i).getAttribute("href");
            getlist7().get(i).click();
            System.out.println(url);
            if (!driver.getCurrentUrl().contains(url)) {
                logger.error(url + " is failed");
                listOfFailedElements.add(url);
            }
            actions.moveToElement(getBrandsMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Shops are failed");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getlist8() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='brand-ltr']")));
        var list = driver.findElements(By.xpath("//*[@class='brand-ltr']"));
        return list;
    }

    private List<WebElement> getlist9() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='brand-brandsListingGroup-1q9']>a")));
        } catch (TimeoutException e) {
            System.out.println("No brands for this letter");
        }
        var list = driver.findElements(By.cssSelector("[class='brand-brandsListingGroup-1q9']>a"));
        return list;
    }

    private WebElement getLetterHeaderForBrands() {
        return driver.findElement(By.cssSelector("[class='brand-brandsListingGroup-1q9']>h3"));
    }

    private List<WebElement> getlist10() {
        var list = driver.findElements(By.cssSelector("[class='brand-brandsListing-1W1']>button"));
        return list;
    }

    public boolean brandsAlphabetMegaMenuPagesCorrect() throws InterruptedException {
        List<String> listOfFailedElements = new ArrayList<>();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getBrandsMegaMenu()).perform();
        for (int i = 15; i < getlist8().size(); i++) {
            String letter = getlist8().get(i).getText();
            getlist8().get(i).click();
            System.out.println(letter);
            if (getlist9().size() == 0) {
                if (!letter.equals("#")) {
                    if (!getLetterHeaderForBrands().getText().equals(letter) || !getlist10().get(i).getAttribute("class").endsWith("m-active")) {
                        logger.error(letter + "  in Brands alphabet is failed");
                        listOfFailedElements.add(letter);
                    }
                } else {
                    if (!getLetterHeaderForBrands().getText().equals(letter) || !getlist10().get(i).getAttribute("class").endsWith("m-active")) {
                        logger.error(letter + "  in Brands alphabet is failed");
                        listOfFailedElements.add(letter);
                    }
                }
            }
            if (getlist9().size() > 0) {
                for (WebElement brand : getlist9()) {
                    if (!letter.equals("#")) {
                        if (!brand.getText().startsWith(letter) || !getLetterHeaderForBrands().getText().equals(letter) || !getlist10().get(i).getAttribute("class").endsWith("m-active")) {
                            logger.error(letter + "  in Brands alphabet is failed");
                            listOfFailedElements.add(letter);
                        }
                    } else {
                        if (!Character.isDigit(brand.getText().charAt(0)) || !getLetterHeaderForBrands().getText().equals("0-9") || !getlist10().get(i).getAttribute("class").endsWith("m-active")) {
                            logger.error(letter + "  in Brands alphabet is failed");
                            listOfFailedElements.add(letter);
                        }
                    }
                }
            }
            actions.moveToElement(getBrandsMegaMenu()).perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + " in Brands alphabet are failed");
            return false;
        } else {
            return true;
        }
    }
    private WebElement getSeeAllBrandsbutton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.seeall")));
        return driver.findElement(By.cssSelector(".button.seeall"));
    }
    public BrandsPage openBrandsPage3() throws InterruptedException {
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(getBrandsMegaMenu()).perform();
        getSeeAllBrandsbutton().click();
        return new BrandsPage(driver);
    }
}
