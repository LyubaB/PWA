package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WomensShoesPage extends BasePage {
    public WomensShoesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isWomensShoesPage() {
        var list = driver.findElements(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
        return list.size() == 1;
    }

    public void open() {
        driver.get("https://stg.shoebacca.com/womens-shoes.html");
    }

    private WebElement getProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='ais-InfiniteHits-item'])[2]")));
        return driver.findElement(By.xpath("(//*[@class='ais-InfiniteHits-item'])[2]"));
    }

    private WebElement getSizeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='tile-root-1nG']")));
        return driver.findElement(By.xpath("(//*[@class='tileList-root-3cR'])[2]/*[@class='tile-root-1nG']"));
    }

    public void chooseSize() {
        if (driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/*[@class='tile-root-1nG']")).size() > 0 && getSizeButton().getAttribute("disabled") == null) {
            getSizeButton().click();
        }
    }

    private WebElement getWidthButton() {
        return driver.findElement(By.xpath("(//*[@class='tileList-root-3cR'])[1]/*[@class='tile-root-1nG']"));
    }

    public void chooseWidth() {
        if (driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[1]/*[@class='tile-root-1nG']")).size() > 0 && getWidthButton().getAttribute("disabled") == null) {
            getWidthButton().click();
        }
    }

    private WebElement getAddToCartButton() {
        return driver.findElement(By.xpath("(//button/*[text()='Add to Cart'])[2]"));
    }

    public void addProductToCart() {
//        new WebDriverWait(driver, 20)
//                .ignoring(StaleElementReferenceException.class)
//                .until((WebDriver d) -> {
//                    d.findElement(By.xpath("(//*[@class='ais-InfiniteHits-item'])[2]")).click();
//                    return true;
//                });
        getProduct().click();
        Actions actions = new Actions(driver);
        actions.moveToElement(getSizeButton());
        actions.perform();
        chooseSize();
        chooseWidth();
        actions.moveToElement(getAddToCartButton());
        actions.perform();
        getAddToCartButton().click();
    }

    private WebElement getCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='button-root_normalPriority-1xS']")));
        return driver.findElement(By.cssSelector("[class='button-root_normalPriority-1xS']"));
    }

    private WebElement getGuestCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='guestCheckout']")));
        return driver.findElement(By.cssSelector("[class='guestCheckout']"));
    }

    private WebElement getEmailField() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        return driver.findElement(By.cssSelector("[type='email']"));
    }

    private WebElement getFirstNameField() {
        return driver.findElement(By.name("firstname"));
    }

    private WebElement getLastNameField() {
        return driver.findElement(By.name("lastname"));
    }

    private WebElement getAddressField() {
        return driver.findElement(By.name("street[0]"));
    }

    private WebElement getCityField() {
        return driver.findElement(By.name("city"));
    }

    private WebElement getStateField() {
        return driver.findElement(By.name("region"));
    }

    private WebElement getZipCodeField() {
        return driver.findElement(By.name("postcode"));
    }

    private WebElement getPhoneNumberField() {
        return driver.findElement(By.cssSelector("[field='telephone']"));
    }

    private WebElement getSaveContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }

    private WebElement getIframeBraintree() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='braintree-hosted-field-number']")));
        return driver.findElement(By.xpath("//iframe[@id='braintree-hosted-field-number']"));
    }

    private WebElement getCardNumberField() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='credit-card-number']")));
        return driver.findElement(By.id("credit-card-number"));
    }

    private WebElement getIframeBraintreeExpdate() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='braintree-hosted-field-expirationDate']")));
        return driver.findElement(By.xpath("//iframe[@id='braintree-hosted-field-expirationDate']"));
    }

    private WebElement getExpDateField() {
        return driver.findElement(By.id("expiration"));
    }

    private WebElement getIframeBraintreeCVV() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='braintree-hosted-field-cvv']")));
        return driver.findElement(By.xpath("//iframe[@id='braintree-hosted-field-cvv']"));
    }

    private WebElement getCVVField() {
        return driver.findElement(By.id("cvv"));
    }

    private WebElement getPlaceOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='button-root_highPriority-3-H']")));
        return driver.findElement(By.cssSelector("[class='button-root_highPriority-3-H']"));
    }

    public void guestCheckout() {
        getCheckoutButton().click();
        getGuestCheckoutButton().click();
        getEmailField().click();
        getEmailField().sendKeys("lyuba.b@shoebacca.com");
        getFirstNameField().sendKeys("Lyuba");
        getLastNameField().sendKeys("Bal");
        getAddressField().sendKeys("2205 E Pioneer Dr");
        getCityField().sendKeys("Irving");
        getStateField().sendKeys("Texas");
        getZipCodeField().sendKeys("75061");
        getPhoneNumberField().sendKeys("1234567890");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getSaveContinueButton());
        getSaveContinueButton().click();
        js.executeScript("arguments[0].scrollIntoView();", getIframeBraintree());
        driver.switchTo().frame(getIframeBraintree());
        getCardNumberField().click();
        getCardNumberField().sendKeys("4444333322221111");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeExpdate());
        getExpDateField().click();
        getExpDateField().sendKeys("12/24");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeCVV());
        getCVVField().sendKeys("999");
        driver.switchTo().defaultContent();
//        getPlaceOrderButton().click();
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

    public void loggedinUserCheckout() {
        getCheckoutButton().click();
        getMemberCheckoutButton().click();
        getEmailAddressField().click();
        getEmailAddressField().sendKeys("test115@gmail.com");
        getPasswordField().sendKeys("!L654321");
        getSigninButton().click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getPlaceOrderButton());
        getPlaceOrderButton().click();
        js.executeScript("arguments[0].scrollIntoView();", getIframeBraintree());
        driver.switchTo().frame(getIframeBraintree());
        getCardNumberField().click();
        getCardNumberField().sendKeys("4444333322221111");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeExpdate());
        getExpDateField().click();
        getExpDateField().sendKeys("12/24");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeCVV());
        getCVVField().sendKeys("999");
        driver.switchTo().defaultContent();
//        getPlaceOrderButton().click();
    }

    public boolean isReviewPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your Opinion Counts']")));
        var list = driver.findElements(By.xpath("//*[text()='Your Opinion Counts']"));
        return list.size() == 1;
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

    public void clickOutsidePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='attentive_creative']")));
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }

    private WebElement getMiniCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='cartTrigger-trigger-1hC']")));
        return driver.findElement(By.cssSelector("[class='cartTrigger-trigger-1hC']"));
    }

    public boolean minicartExist() {
        var list = driver.findElements(By.cssSelector("[class='icon-cart cartTrigger-cartIcon-oTF']"));
        return list.size() == 1;
    }

    public boolean minicartLocation() {
        return getMiniCart().getLocation().getX() > 900 && getMiniCart().getLocation().getY() < 105;
    }

    public void clickMiniCart() {
        getMiniCart().click();
    }

    public boolean isMiniCartOpened() {
        var list = driver.findElements(By.xpath("//*[@class='miniCart-contents_open-ubc']"));
        return list.size() == 1;
    }

    public boolean isMiniCartEmpty() {
        var list = driver.findElements(By.xpath("//*[text()='YOUR CART IS EMPTY']"));
        return list.size() == 1;
    }

    public boolean isMiniCartBubble() {
        var list = driver.findElements(By.cssSelector("[class='cartTrigger-counter-1Fo']"));
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
        return driver.findElement(By.xpath("//button/*[@class='icon-root-2Hz']"));
    }

    public void closeMiniCart() {
        getCrossButtonMiniCart().click();
    }

    public void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }

    public void numberInBubble() {
        int number = driver.findElements(By.cssSelector("[class='cartTrigger-counter-1Fo']")).size();
//        logger.info("Letters at breadcrumbs is the same as letters at search bar");
        System.out.println(number);
        ;
    }

}