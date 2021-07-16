package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void guestContinuesCheckout() {
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
        getPlaceOrderButton().click();
    }
    public void loggedinUserCheckout() {
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
        getPlaceOrderButton().click();
    }
    public boolean isReviewPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your Opinion Counts']")));
        var list = driver.findElements(By.xpath("//*[text()='Your Opinion Counts']"));
        return list.size() == 1;
    }
}
