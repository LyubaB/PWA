package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PLPPage extends BasePage {
    public PLPPage(WebDriver driver) {
        super(driver);
    }

    //    maybe need to delete
    private WebElement getProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']")));
        return driver.findElement(By.xpath("//*[@class='ais-InfiniteHits-item']"));
    }

    private List<WebElement> getProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']")));
        var list = driver.findElements(By.xpath("//*[@class='ais-InfiniteHits-item']"));
        return list;
    }

    public PDPPage openPDP() {
            for (int i = 0; i < getProducts().size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(getProducts().get(i)).build().perform();
            getProducts().get(i).click();
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='tile-root-1nG']")));
            } catch (TimeoutException ignored) {
            }
            var listAllSizes  = driver.findElements(By.cssSelector("[class='tile-root-1nG']"));
            var listUnactiveSizes = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/button[@disabled]"));
            if (listAllSizes.size() - listUnactiveSizes.size() < 2 ) {
                driver.navigate().back();
            } else {
                break;
            }
        }
        return new PDPPage(driver);
    }
}
