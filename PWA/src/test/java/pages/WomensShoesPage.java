package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WomensShoesPage extends BasePage {
    public WomensShoesPage (WebDriver driver){
        super(driver);
    }
    private WebElement getWomensShoesCategoryHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]")));
        return driver.findElement(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
    }
    public boolean isWomensShoesPage () {
        var list = driver.findElements(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
        return list.size()==1;
    }
    public void open() {
        driver.get("https://stg.shoebacca.com/womens-shoes.html");
    }
    private WebElement getFirstProductOnPage(){
        return driver.findElement(By.xpath("//*[@class='ais-InfiniteHits-item']"));
    }
    private WebElement getSizeButton(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='tile-root-1nG']")));
        return driver.findElement(By.xpath("(//*[@class='tileList-root-3cR'])[2]/*[@class='tile-root-1nG']"));
        }
    public void chooseSize (){
        if (getSizeButton().getAttribute("disabled")==null) {
            getSizeButton().click();
        }
    }
    private WebElement getWidthButton(){
        return driver.findElement(By.xpath("//*[@class='tileList-root-3cR'][1]/*[@class='tile-root-1nG']"));
    }
    public void chooseWidth (){
        if (getWidthButton().getAttribute("disabled")==null) {
            getWidthButton().click();
        }
    }
    private WebElement getAddToCartButton(){
        return driver.findElement(By.xpath("(//button/*[text()='Add to Cart'])[2]"));
    }
    private WebElement getCheckoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='button-root_normalPriority-1xS']")));
        return driver.findElement(By.cssSelector("[class='button-root_normalPriority-1xS']"));
    }
    private WebElement getGuestCheckoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='guestCheckout']")));
        return driver.findElement(By.cssSelector("[class='guestCheckout']"));
    }
    private WebElement getEmailField(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        return driver.findElement(By.cssSelector("[type='email']"));
    }
    private WebElement getFirstNameField(){
        return driver.findElement(By.name("firstname"));
    }
    private WebElement getLastNameField(){
        return driver.findElement(By.name("lastname"));
    }
    private WebElement getAddressField(){
        return driver.findElement(By.name("street[0]"));
    }
    private WebElement getCityField(){
        return driver.findElement(By.name("city"));
    }
    private WebElement getStateField(){
        return driver.findElement(By.name("region"));
    }
    private WebElement getZipCodeField(){
        return driver.findElement(By.name("postcode"));
    }
    private WebElement getPhoneNumberField(){
        return driver.findElement(By.cssSelector("[field='telephone']"));
    }
    private WebElement getSaveContinueButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        return driver.findElement(By.xpath("//*[@type='submit']"));
    }
    private WebElement getCardNumberField(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#credit-card-number")));
        return driver.findElement(By.id("credit-card-number"));
    }
    private WebElement getExpDateField(){
        return driver.findElement(By.xpath("//*[@data-braintree-id='expiration-date-field-group']"));
    }
    private WebElement getCVVField(){
        return driver.findElement(By.xpath("//*[@data-braintree-id='cvv-field-group']"));
    }
    private WebElement getPlaceOrderButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Place Order']")));
        return driver.findElement(By.cssSelector("//*[class='button-root_highPriority-3-H']"));
    }
    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(getFirstProductOnPage()));
        Actions actions = new Actions(driver);
        actions.moveToElement((getFirstProductOnPage()));
        actions.perform();
        getFirstProductOnPage().click();
        chooseSize();
        chooseWidth();
        getAddToCartButton().click();
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
        js.executeScript("arguments[0].scrollIntoView();",getSaveContinueButton());
        getSaveContinueButton().click();
//        getCardNumberField().sendKeys("1111222233334444");
//        getExpDateField().sendKeys("12/24");
//        getCVVField().sendKeys("999");
//        getPlaceOrderButton().click();

    }
}
