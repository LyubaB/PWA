package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private WebElement getIframeEmailPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='attentive_creative']")));
        return driver.findElement(By.xpath("//iframe[@id='attentive_creative']"));
    }

    private WebElement getEmailPopup() {
        return driver.findElement(By.id("input0"));
    }

    public boolean isEmailPopup() {
        driver.switchTo().frame(getIframeEmailPopup());
        return getEmailPopup().isEnabled();
    }

    private WebElement getCrossEmailPopupIcon() {
        return driver.findElement(By.id("closeIconSvg"));
    }

    public void closeEmailPopup() {
        driver.switchTo().frame(getIframeEmailPopup());
        getCrossEmailPopupIcon().click();
        driver.switchTo().defaultContent();
    }

    public void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }

    private WebElement getGrayFieldAroundPopup() {
        return driver.findElement(By.id("overlayContainer"));
    }

    public boolean isGrayFieldAroundPopup() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(getGrayFieldAroundPopup()));
            getGrayFieldAroundPopup().isSelected();
        } catch (NoSuchElementException | NoSuchWindowException grayFieldAroundPopup) {
            return false;
        }
        return true;
    }

    private WebElement getWomensShoesButtonHeader() {
        return driver.findElement(By.xpath("//*[@href='/womens-shoes.html']"));
    }

    public WomensShoesPage openWomensShoesPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/womens-shoes.html']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getWomensShoesButtonHeader());
        return new WomensShoesPage(driver);
    }

    private WebElement getSearchBar() {
        return driver.findElement(By.name("search_query"));
    }

    public void useSearchQueryForBiggerScreen1(String search1) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
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
        var list = driver.findElements(By.xpath("//*[contains(@class,\"highlighted\") and text()=\"sp\"]"));
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
        var list = driver.findElements(By.xpath("//*[contains(@class,\"highlighted\") and text()=\"Sp\"]"));
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
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

    public String searchQueryForBiggerScreen1(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
        getSearchBar().sendKeys(search3);
        getMagnifier().click();
        wait.until(ExpectedConditions.visibilityOf(getBreadcrumbs()));
        String url = driver.getCurrentUrl();
        logger.info("Expecting that letters at the end of URL is the same as letters at search bar");
        return url.split("/")[5];
    }

    private WebElement getBreadcrumbs() {
        return driver.findElement(By.xpath("//*[contains(@class,'breadcrumbs-current')]"));
    }

    public boolean checkQuery(String search3) {
        String searchResult = getBreadcrumbs().getText().split("'")[1];
        logger.info("Letters at breadcrumbs is the same as letters at search bar");
        return searchResult.equals(search3);
    }

    private WebElement getMagnifierForSmallerScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-search']")));
        return driver.findElement(By.xpath("//span[@class='icon-search']"));
    }

    public String searchQueryForSmallerScreen1(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        wait.until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        js.executeScript("arguments[0].click();", getSearchBar());
        getSearchBar().sendKeys(search3);
        Actions actions = new Actions(driver);
        actions.moveToElement((getMagnifierForSmallerScreen()));
        actions.perform();
        getMagnifierForSmallerScreen().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'breadcrumbs-current')]")));
        String url = driver.getCurrentUrl();
        logger.info("Expecting that letters at the end of URL is the same as letters at search bar");
        return url.split("/")[5];
    }

    public String searchQueryForBiggerScreen2(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getSearchBar());
        getSearchBar().sendKeys(search3);
        getSearchBar().sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'breadcrumbs-current')]")));
        String url = driver.getCurrentUrl();
        logger.info("Expecting that letters at the end of URL is the same as letters at search bar");
        return url.split("/")[5];

    }

    public String searchQueryForSmallerScreen2(String search3) {
        wait.until(ExpectedConditions.elementToBeClickable(getMagnifier()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getMagnifier());
        wait.until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        js.executeScript("arguments[0].click();", getSearchBar());
        getSearchBar().sendKeys(search3);
        getSearchBar().sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'breadcrumbs-current')]")));
        String url = driver.getCurrentUrl();
        logger.info("Expecting that letters at the end of URL is the same as letters at search bar");
        return url.split("/")[5];
    }

    private WebElement getLogo() {
        return driver.findElement(By.xpath("//a[@class='header-logo-2jr']"));
    }

    public boolean logoExist() {
        var list = driver.findElements(By.xpath("//a[@class='header-logo-2jr']"));
        return list.size() == 1;
    }

    public boolean logoLocation() {
        return getLogo().getLocation().getX() < 30 && getLogo().getLocation().getY() < 100;
    }

    private WebElement getLearnMoreAboutShoebacca() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']")));
        return driver.findElement(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']"));
    }

    public boolean isHomePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getLearnMoreAboutShoebacca());
        var list = driver.findElements(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']"));
        return list.size() == 1;
    }
}






















