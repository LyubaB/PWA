package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PDPPage extends BasePage {
    public PDPPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPDPPage() {
        var list = driver.findElements(By.id("item0"));
        return list.size() >= 1;
    }

    private List<WebElement> getSizeNotGrayButtons() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='tile-root-1nG']")));
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/*[@class='tile-root-1nG']"));
        return list;
    }

    public void chooseSize() {
        for (int i = 0; i < getSizeNotGrayButtons().size(); i++)
            if (getSizeNotGrayButtons().get(i).getAttribute("disabled") == null) {
                getSizeNotGrayButtons().get(i).click();
                break;
            }
    }

    private List<WebElement> getWidthNotGrayButtons() {
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[1]/*[@class='tile-root-1nG']"));
        return list;
    }

    public void chooseWidth() {
        for (int i = 0; i < getWidthNotGrayButtons().size(); i++)
            if (getWidthNotGrayButtons().get(i).getAttribute("disabled") == null) {
                getWidthNotGrayButtons().get(i).click();
                break;
            }
    }

    private WebElement getAddToCartButton() {
        return driver.findElement(By.xpath("(//button/*[text()='Add to Cart'])[2]"));
    }

    public void addProductToCart() {
        chooseSize();
        chooseWidth();
        Actions actions = new Actions(driver);
        actions.moveToElement(getAddToCartButton());
        actions.perform();
        getAddToCartButton().click();
    }

    private List<WebElement> getSizes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='tileList-root-3cR'])[2]/button")));
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/button"));
        return list;
    }

    public boolean sizesWidthsDisplayed(String sizewidth, String product, List<String> listOfFailedElements) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='tileList-root-3cR']/button")));
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error(product + " (size/width " + sizewidth + ") " + " is failed");
            listOfFailedElements.add(product + sizewidth);
            return false;
        }
        return true;
    }

    public void checkSizesCorrect(String size, String product, List<String> listOfFailedElements) {
        if (sizesWidthsDisplayed(size, product, listOfFailedElements)) {
            for (int k = 0; k < getSizes().size(); k++) {
                if (getSizes().get(k).getText().equals(size) && getSizes().get(k).getAttribute("disabled") != null) {
                    logger.error(product + " (size " + size + ") " + " is failed");
                    listOfFailedElements.add(product + size);
                }
                if (getSizes().get(k).getText().equals(size)) {
                    break;
                }
            }
        }
    }

    private List<WebElement> getWidths() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='tileList-root-3cR'])[1]/button")));
        var list = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[1]/button"));
        return list;
    }

    public void checkWidthsCorrect(String width, String product, List<String> listOfFailedElements) {
        if (sizesWidthsDisplayed(width, product, listOfFailedElements)) {
            for (int k = 0; k < getWidths().size(); k++) {
                if (getWidths().get(k).getText().equals(width) && getWidths().get(k).getAttribute("disabled") != null) {
                    logger.error(product + " (width " + width + ") " + " is failed");
                    listOfFailedElements.add(product + width);
                }
                if (getWidths().get(k).getText().equals(width)) {
                    break;
                }
            }
        }
    }

    public List<String> createListOfActiveSizes() {
        List<String> listOfActiveSizes = new ArrayList<>();
        for (int i = 0; i < getSizes().size(); i++) {
            if (getSizes().get(i).getAttribute("disabled") == null) {
                listOfActiveSizes.add(getSizes().get(i).getText());
            }
        }
        return listOfActiveSizes;
    }
    public List<String> createListOfDisabledSizes() {
        List<String> listOfDisabledSizes = new ArrayList<>();
        for (int i = 0; i < getSizes().size(); i++) {
            if (getSizes().get(i).getAttribute("disabled") != null) {
                listOfDisabledSizes.add(getSizes().get(i).getText());
            }
        }
        return listOfDisabledSizes;
    }
    public List<String> createListOfActiveWidths() {
        List<String> listOfActiveWidths = new ArrayList<>();
        for (int i = 0; i < getWidths().size(); i++) {
            if (getWidths().get(i).getAttribute("disabled") == null) {
                listOfActiveWidths.add(getWidths().get(i).getText());
            }
        }
        return listOfActiveWidths;
    }
    public List<String> createListOfDisabledWidths() {
        List<String> listOfDisabledWidths = new ArrayList<>();
        for (int i = 0; i < getWidths().size(); i++) {
            if (getWidths().get(i).getAttribute("disabled") != null) {
                listOfDisabledWidths.add(getWidths().get(i).getText());
            }
        }
        return listOfDisabledWidths;
    }
}
