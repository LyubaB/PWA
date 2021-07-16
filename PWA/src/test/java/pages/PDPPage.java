package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PDPPage extends BasePage {
    public PDPPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPDPPage() {
        var list = driver.findElements(By.id("item0"));
        return list.size() >= 1;
    }

    private List<WebElement> getSizeButtons() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='tile-root-1nG']")));
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/*[@class='tile-root-1nG']"));
        return list;
    }

    public void chooseSize() {
        for (int i = 0; i < getSizeButtons().size(); i++)
            if (getSizeButtons().get(i).getAttribute("disabled") == null) {
                getSizeButtons().get(i).click();
                break;
            }
    }

    private List<WebElement> getWidthButtons() {
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[1]/*[@class='tile-root-1nG']"));
        return list;
    }

    public void chooseWidth() {
        for (int i = 0; i < getWidthButtons().size(); i++)
            if (getWidthButtons().get(i).getAttribute("disabled") == null) {
                getWidthButtons().get(i).click();
                break;
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
        chooseSize();
        chooseWidth();
        Actions actions = new Actions(driver);
        actions.moveToElement(getAddToCartButton());
        actions.perform();
        getAddToCartButton().click();
    }
}
