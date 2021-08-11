package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver){super((driver));}

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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='button-root_highPriority-3-H']")));
        return driver.findElement(By.cssSelector("[class='button-root_highPriority-3-H']"));
    }

    private WebElement getIframeBraintree() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='braintree-hosted-field-number']")));
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

    public void guestContinuesCheckout(String email, String name, String lastName, String streetAddress, String city, String state, String zipcode, String phone, String cardNumber, String expirationDate, String cvv) {
        getEmailField().click();
        getEmailField().sendKeys(email);
        getFirstNameField().sendKeys(name);
        getLastNameField().sendKeys(lastName);
        getAddressField().sendKeys(streetAddress);
        getCityField().sendKeys(city);
        getStateField().sendKeys(state);
        getZipCodeField().sendKeys(zipcode);
        getPhoneNumberField().sendKeys(phone);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getSaveContinueButton());
        getSaveContinueButton().click();
        js.executeScript("arguments[0].scrollIntoView();", getIframeBraintree());
        driver.switchTo().frame(getIframeBraintree());
        getCardNumberField().click();
        getCardNumberField().sendKeys(cardNumber);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeExpdate());
        getExpDateField().click();
        getExpDateField().sendKeys(expirationDate);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeCVV());
        getCVVField().sendKeys(cvv);
        driver.switchTo().defaultContent();
        getPlaceOrderButton().click();
    }
    public void loggedinUserCheckout(String cardNumber, String expirationDate, String cvv) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getSaveContinueButton());
        getSaveContinueButton().click();
//        js.executeScript("arguments[0].scrollIntoView();", getIframeBraintree());
        Actions actions = new Actions(driver);
        actions.moveToElement(getIframeBraintree()).perform();
        driver.switchTo().frame(getIframeBraintree());
        getCardNumberField().click();
        getCardNumberField().sendKeys(cardNumber);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeExpdate());
        getExpDateField().click();
        getExpDateField().sendKeys(expirationDate);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(getIframeBraintreeCVV());
        getCVVField().sendKeys(cvv);
        driver.switchTo().defaultContent();
        getPlaceOrderButton().click();
    }
    public boolean isReviewPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your Opinion Counts']")));
        var list = driver.findElements(By.xpath("//*[text()='Your Opinion Counts']"));
        return list.size() == 1;
    }
}
