package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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

    public PDPPage openPDPWith2orMoreSizes() {
        for (int i = 0; i < getProducts().size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(getProducts().get(i)).perform();
            getProducts().get(i).click();
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='tile-root-1nG']")));
            } catch (TimeoutException ignored) {
            }
            var listAllSizes = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/button"));
            var listUnactiveSizes = driver.findElements(By.xpath("(//*[@class='tileList-root-3cR'])[2]/button[@disabled]"));
            if (listAllSizes.size() - listUnactiveSizes.size() < 2) {
                driver.navigate().back();
            } else {
                break;
            }
        }
        return new PDPPage(driver);
    }

    private WebElement getSizeFilter() {
        fluentWait.until(x->x.findElement(By.xpath("//*[text()='Size']")));
        return driver.findElement(By.xpath("//*[text()='Size']"));
    }

    private List<WebElement> getSizeFilterButtons() {
        fluentWait.until(x->x.findElement(By.xpath("//*[text()='Size']/following-sibling::div//*[contains(@class,'26W')]")));
        return driver.findElements(By.xpath("//*[text()='Size']/following-sibling::div//*[contains(@class,'26W')]"));
    }

    private List<WebElement> getProductNames() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='category-productName-1R6']")));
        return driver.findElements(By.cssSelector("[class='category-productName-1R6']"));
    }

    public PDPPage openPDP(int j) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(getProducts().get(j)).perform();
        getProducts().get(j).click();
        return new PDPPage(driver);
    }

    public boolean sizeCheck() {
        List<String> listOfFailedElements = new ArrayList<>();
        int count = 0;
        Actions actions = new Actions(driver);
        actions.moveToElement(getSizeFilter()).build().perform();
        for (int i = 1; i < getSizeFilterButtons().size(); i++) {
//        for (int i=0; i<3; i++){
            String size = getSizeFilterButtons().get(i).getText();
            System.out.println(size);
            getSizeFilterButtons().get(i).click();
            for (int j = 0; j < getProducts().size(); j++) {
                String product = getProductNames().get(j).getText();
                System.out.println(product);
                openPDP(j);
                PDPPage pdpPage = new PDPPage(driver);
                pdpPage.checkSizesCorrect(size, product, listOfFailedElements);
                count++;
                driver.navigate().back();
//                if(j==2){
//                    break;
//                }
            }
            driver.navigate().back();
            actions.moveToElement(getSizeFilter()).build().perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + "at size filtering are failed, checked " + count + " products");
            return false;
        } else {
            return true;
        }
    }

    private WebElement getWidthFilter() {
        fluentWait.until(x->x.findElement(By.xpath("//*[text()='Shoe Width']")));
        return driver.findElement(By.xpath("//*[text()='Shoe Width']"));
    }
    public boolean widthFilterDisplayed() {
        try {
            fluentWait.until(x->x.findElement(By.xpath("//*[text()='Shoe Width']")));
        } catch (TimeoutException | NoSuchElementException ignored) {
            return false;
        }
        return true;
    }

    private List<WebElement> getWidthFilterButtons() {
        fluentWait.until(x->x.findElement(By.xpath("//*[text()='Shoe Width']/following-sibling::div//*[contains(@class,'26W')]")));
        return driver.findElements(By.xpath("//*[text()='Shoe Width']/following-sibling::div//*[contains(@class,'26W')]"));
    }

    public boolean widthCheck() {
        List<String> listOfFailedElements = new ArrayList<>();
        int count = 0;
        Actions actions = new Actions(driver);
        actions.moveToElement(getWidthFilter()).build().perform();
        for (int i = 8; i < getWidthFilterButtons().size(); i++) {
            String width = getWidthFilterButtons().get(i).getText();
            System.out.println(width);
            getWidthFilterButtons().get(i).click();
            for (int j = 0; j < getProducts().size(); j++) {
                String product = getProductNames().get(j).getText();
                System.out.println(product);
                openPDP(j);
                PDPPage pdpPage = new PDPPage(driver);
                pdpPage.checkWidthsCorrect(width, product, listOfFailedElements);
                count++;
                driver.navigate().back();
//                if (j == 2) {
//                    break;
//                }
            }
            driver.navigate().back();
            actions.moveToElement(getWidthFilter()).build().perform();
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + "at width filtering are failed, checked " + count + " products");
            return false;
        } else {
            return true;
        }
    }

    private List<WebElement> getProductBrands() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='category-brandName-8Hl']")));
        return driver.findElements(By.cssSelector("[class='category-brandName-8Hl']"));
    }

    private List<WebElement> getProductImages() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ais-InfiniteHits-item']//*[contains(@class,'image')]/img")));
        return driver.findElements(By.xpath("//*[@class='ais-InfiniteHits-item']//*[contains(@class,'image')]/img"));
    }

    private WebElement getSizesShowMoreButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Size']/following-sibling::div//*[@class='category-showMore-1M1']")));
        return driver.findElement(By.xpath("//*[text()='Size']/following-sibling::div//*[@class='category-showMore-1M1']"));
    }
    public void clickSizesShowMoreButtonIfExists() {
        try {
            getSizesShowMoreButton().click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    private WebElement getBrandFilter() {
//        fluentWait.until(x->x.findElement(By.xpath("//*[text()='Brand']")));
//        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Brand']")));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Brand']"))));
        return driver.findElement(By.xpath("//*[text()='Brand']"));
    }

    private WebElement getBrandShowMoreButton() {
//        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Brand']/following-sibling::div//*[@class='category-showMore-1M1']")));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Brand']/following-sibling::div//*[@class='category-showMore-1M1']"))));
        return driver.findElement(By.xpath("//*[text()='Brand']/following-sibling::div//*[@class='category-showMore-1M1']"));
    }

    public void clickBrandsShowMoreButtonIfExists() {
        try {
            getBrandShowMoreButton().click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    private List<WebElement> getBrandNamesButtons() {
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Brand']/following-sibling::div//*[contains(@class,'26W')]")));
        return driver.findElements(By.xpath("//*[text()='Brand']/following-sibling::div//*[contains(@class,'26W')]"));
    }

    public void chooseBrandsFilterButton(String brand) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getBrandFilter()).build().perform();
        clickBrandsShowMoreButtonIfExists();
//        actions.moveToElement(getBrandFilter()).build().perform();
        for (int i = 0; i < getBrandNamesButtons().size(); i++) {
            if (getBrandNamesButtons().get(i).getText().equals(brand)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", getBrandNamesButtons().get(i));
                getBrandNamesButtons().get(i).click();
                break;
            }
        }
    }
    private WebElement getAmountOfProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='category-itemsFound-38k']>span")));
        return driver.findElement(By.cssSelector("[class='category-itemsFound-38k']>span"));
    }

    public void checkActiveSizes(String brand, String product, String image, List<String> listOfActiveSizes, List<String> listOfFailedElements) {
        List<String> listOfCorrectElements = new ArrayList<>();
        Actions actions = new Actions(driver);
        actions.moveToElement(getSizeFilter()).build().perform();
        clickSizesShowMoreButtonIfExists();
        for (String listOfActiveSize : listOfActiveSizes) {
            actions.moveToElement(getSizeFilter()).build().perform();
            for (int k = 0; k < getSizeFilterButtons().size(); k++) {
                if (getSizeFilterButtons().get(k).getText().equals(listOfActiveSize)) {
                    System.out.println(listOfActiveSize);
                    actions.moveToElement(getSizeFilterButtons().get(k)).perform();
                    getSizeFilterButtons().get(k).click();
                    for (int c = 0; c < getProducts().size(); c += 16) {
                        for (int m = c; m < getProducts().size(); m++) {
                            if (getProductImages().get(m).getAttribute("src").equals(image)) {
                                System.out.println("product correct");
                                listOfCorrectElements.add(product);
                                actions.moveToElement(getSizeFilter()).build().perform();
                                getSizeFilterButtons().get(k).click();
                                break;
                            }
                        }
                        if (listOfCorrectElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                            actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("window.scrollBy(0, 2000);");
                        }
                        if (listOfCorrectElements.size() == 1) {
                            break;
                        }
                    }
                    if (listOfCorrectElements.size() == 0) {
                        logger.error(brand + ", " + product + " (size " + listOfActiveSize + ") " + " is failed");
                        listOfFailedElements.add(brand + product + listOfActiveSize);
                        actions.moveToElement(getSizeFilter()).build().perform();
                        getSizeFilterButtons().get(k).click();
                    }
                    listOfCorrectElements.clear();
                    break;
                }
            }
        }
    }
    public void checkDisabledSizes(String brand, String product, String image, List<String> listOfDisabledSizes, List<String> listOfFailedElements) {
        List<String> listOfWrongElements = new ArrayList<>();
        Actions actions = new Actions(driver);
        actions.moveToElement(getSizeFilter()).build().perform();
        clickSizesShowMoreButtonIfExists();
        for (String listOfDisabledSize : listOfDisabledSizes) {
            actions.moveToElement(getSizeFilter()).build().perform();
            for (int k = 0; k < getSizeFilterButtons().size(); k++) {
                if (getSizeFilterButtons().get(k).getText().equals(listOfDisabledSize)) {
                    System.out.println(listOfDisabledSize);
                    actions.moveToElement(getSizeFilterButtons().get(k)).perform();
                    getSizeFilterButtons().get(k).click();
                    for (int c = 0; c < getProducts().size(); c += 16) {
                        for (int m = c; m < getProducts().size(); m++) {
                            if (getProductImages().get(m).getAttribute("src").equals(image)) {
                                System.out.println("wrong product");
                                listOfWrongElements.add(product);
                                break;
                            }
                        }
                        if (listOfWrongElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                            actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("window.scrollBy(0, 2000);");
                        }
                        if (listOfWrongElements.size() != 0) {
                            logger.error(brand + ", " + product + " (size " + listOfDisabledSize + ") " + " is failed");
                            listOfFailedElements.add(brand + product + listOfDisabledSize);
                            listOfWrongElements.clear();
                            break;
                        }
                    }
                    actions.moveToElement(getSizeFilter()).build().perform();
                    getSizeFilterButtons().get(k).click();
                    break;
                }
            }
        }
    }

    public void checkActiveWidths(String brand, String product, String image, List<String> listOfActiveWidths, List<String> listOfFailedElements) {
        if (widthFilterDisplayed()) {
            List<String> listOfCorrectElements = new ArrayList<>();
            for (String listOfActiveWidth : listOfActiveWidths) {
                Actions actions = new Actions(driver);
                actions.moveToElement(getWidthFilter()).build().perform();
                for (int k = 0; k < getWidthFilterButtons().size(); k++) {
                    if (getWidthFilterButtons().get(k).getText().equals(listOfActiveWidth)) {
                        System.out.println(listOfActiveWidth);
                        getWidthFilterButtons().get(k).click();
                        for (int c = 0; c < getProducts().size(); c += 16) {
                            for (int m = c; m < getProducts().size(); m++) {
                                if (getProductImages().get(m).getAttribute("src").equals(image)) {
                                    System.out.println("product correct");
                                    listOfCorrectElements.add(product);
                                    actions.moveToElement(getWidthFilter()).build().perform();
                                    getWidthFilterButtons().get(k).click();
                                    break;
                                }
                            }
                            if (listOfCorrectElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                                actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                js.executeScript("window.scrollBy(0, 2000);");
                            }
                            if (listOfCorrectElements.size() == 1) {
                                break;
                            }
                        }
                        if (listOfCorrectElements.size() == 0) {
                            logger.error(brand + ", " + product + " (size " + listOfActiveWidth + ") " + " is failed");
                            listOfFailedElements.add(brand + product + listOfActiveWidth);
                            actions.moveToElement(getWidthFilter()).build().perform();
                            getWidthFilterButtons().get(k).click();
                        }
                        listOfCorrectElements.clear();
                        break;
                    }
                }
            }
        }
    }
    public void checkDisabledWidths(String brand, String product, String image, List<String> listOfDisabledWidths, List<String> listOfFailedElements) {
        if (widthFilterDisplayed()) {
            List<String> listOfWrongElements = new ArrayList<>();
            for (String listOfDisabledWidth : listOfDisabledWidths) {
                Actions actions = new Actions(driver);
                actions.moveToElement(getWidthFilter()).build().perform();
                for (int k = 0; k < getWidthFilterButtons().size(); k++) {
                    if (getWidthFilterButtons().get(k).getText().equals(listOfDisabledWidth)) {
                        System.out.println(listOfDisabledWidth);
                        getWidthFilterButtons().get(k).click();
                        for (int c = 0; c < getProducts().size(); c += 16) {
                            for (int m = c; m < getProducts().size(); m++) {
                                if (getProductImages().get(m).getAttribute("src").equals(image)) {
                                    System.out.println("wrong product");
                                    listOfWrongElements.add(product);
                                    break;
                                }
                            }
                            if (listOfWrongElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                                actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                js.executeScript("window.scrollBy(0, 2000);");
                            }
                            if (listOfWrongElements.size() != 0) {
                                logger.error(brand + ", " + product + " (size " + listOfDisabledWidth + ") " + " is failed");
                                listOfFailedElements.add(brand + product + listOfDisabledWidth);
                                listOfWrongElements.clear();
                                break;
                            }
                        }
                        actions.moveToElement(getWidthFilter()).build().perform();
                        getWidthFilterButtons().get(k).click();
                        break;
                    }
                }
            }
        }
    }

    private WebElement getClearAllButton() {
        return driver.findElement(By.cssSelector("[class='category-clearAll-Hm-']"));
    }

    public boolean sizeWidthCheck() {
        List<String> listOfFailedElements = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < getProducts().size(); j++) {
            String brand = getProductBrands().get(j).getText();
            String product = getProductNames().get(j).getText();
            var image = getProductImages().get(j).getAttribute("src");
            System.out.println(brand + ", " + product);
            openPDP(j);
            PDPPage pdpPage = new PDPPage(driver);
            if(pdpPage.sizesWidthsDisplayed("- all",brand+", " +product, listOfFailedElements)) {
                var listOfActiveSizes = pdpPage.createListOfActiveSizes();
                System.out.println(listOfActiveSizes);
                var listOfDisabledSizes = pdpPage.createListOfDisabledSizes();
                System.out.println(listOfDisabledSizes);
                var listOfActiveWidths = pdpPage.createListOfActiveWidths();
                System.out.println(listOfActiveWidths);
                var listOfDisabledWidths = pdpPage.createListOfDisabledWidths();
                System.out.println(listOfDisabledWidths);
                driver.navigate().back();
                chooseBrandsFilterButton(brand);
                checkActiveSizes(brand, product, image, listOfActiveSizes, listOfFailedElements);
                if(listOfDisabledSizes.size()>0) {
                    checkDisabledSizes(brand, product, image, listOfDisabledSizes, listOfFailedElements);
                }
                checkActiveWidths(brand, product, image, listOfActiveWidths, listOfFailedElements);
                if(listOfDisabledWidths.size()>0) {
                    checkDisabledWidths(brand, product, image, listOfDisabledWidths, listOfFailedElements);
                }
                Actions actions = new Actions(driver);
                actions.moveToElement(getClearAllButton()).perform();
                getClearAllButton().click();
            }else {
                driver.navigate().back();
            }
            count++;
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + "at active size/width filtering are failed, checked " + count + " products");
            return false;
        } else {
            return true;
        }
    }
}

