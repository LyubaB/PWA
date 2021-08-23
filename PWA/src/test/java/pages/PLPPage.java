package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PLPPage extends BasePage {
    public PLPPage(WebDriver driver) {
        super(driver);
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
        fluentWait.until(x -> x.findElement(By.xpath("//*[text()='Size']")));
        return driver.findElement(By.xpath("//*[text()='Size']"));
    }

    private List<WebElement> getSizeFilterButtons() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='Size']/following-sibling::div//*[contains(@class,'26W')]")));
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
        fluentWait.until(x -> x.findElement(By.xpath("//*[text()='Shoe Width']")));
        return driver.findElement(By.xpath("//*[text()='Shoe Width']"));
    }

    public boolean widthFilterDisplayed() {
        try {
            fluentWait.until(x -> x.findElement(By.xpath("//*[text()='Shoe Width']")));
        } catch (TimeoutException | NoSuchElementException ignored) {
            return false;
        }
        return true;
    }

    private List<WebElement> getWidthFilterButtons() {
        fluentWait.until(x -> x.findElement(By.xpath("//*[text()='Shoe Width']/following-sibling::div//*[contains(@class,'26W')]")));
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
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Brand']"))));
        return driver.findElement(By.xpath("//*[text()='Brand']"));
    }

    private WebElement getBrandShowMoreButton() {
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

    public void checkActiveSizes(int k, String listOfActiveSize, String brand, String product, String image, List<String> clickedButtons, List<String> listOfFailedElements) {
        List<String> listOfCorrectElements = new ArrayList<>();
        clickedButtons.clear();
        Actions actions = new Actions(driver);
        actions.moveToElement(getSizeFilter()).build().perform();
        if (getSizeFilterButtons().get(k).getText().equals(listOfActiveSize)) {
            System.out.println(listOfActiveSize);
            actions.moveToElement(getSizeFilterButtons().get(k)).perform();
            getSizeFilterButtons().get(k).click();
            clickedButtons.add(listOfActiveSize);
            for (int c = 0; c < getProducts().size(); c += 16) {
                for (int m = c; m < getProducts().size(); m++) {
                    if (getProductImages().get(m).getAttribute("src").equals(image)) {
                        System.out.println("correct");
                        listOfCorrectElements.add(product);
                        actions.moveToElement(getSizeFilter()).build().perform();
                        getSizeFilterButtons().get(k).click();
                        break;
                    }
                }
                if (listOfCorrectElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                    actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 700);");
                }
                if (listOfCorrectElements.size() == 1) {
                    break;
                }
            }
            if (listOfCorrectElements.size() == 0) {
                System.out.println("wrong");
                logger.error(brand + ", " + product + " (size " + listOfActiveSize + ") " + " is failed");
                listOfFailedElements.add(brand + product + listOfActiveSize);
                actions.moveToElement(getSizeFilter()).build().perform();
                getSizeFilterButtons().get(k).click();
            }
            listOfCorrectElements.clear();
        }
    }

    public void checkDisabledSizes(String brand, String product, String image, List<String> listOfDisabledSizes, List<String> listOfFailedElements) {
        List<String> listOfWrongElements = new ArrayList<>();
        Actions actions = new Actions(driver);
        for (String listOfDisabledSize : listOfDisabledSizes) {
            if (getSizeFilterButtons().size() == 16) {
                actions.moveToElement(getSizeFilter()).build().perform();
                clickSizesShowMoreButtonIfExists();
            }
            for (int k = 0; k < getSizeFilterButtons().size(); k++) {
                actions.moveToElement(getSizeFilter()).build().perform();
                if (getSizeFilterButtons().get(k).getText().equals(listOfDisabledSize)) {
                    System.out.println(listOfDisabledSize);
                    actions.moveToElement(getSizeFilterButtons().get(k)).perform();
                    getSizeFilterButtons().get(k).click();
                    for (int c = 0; c < getProducts().size(); c += 16) {
                        for (int m = c; m < getProducts().size(); m++) {
                            if (getProductImages().get(m).getAttribute("src").equals(image)) {
                                System.out.println("wrong");
                                listOfWrongElements.add(product);
                                break;
                            }
                        }
                        if (listOfWrongElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                            actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("window.scrollBy(0, 700);");
                        }
                        if (listOfWrongElements.size() != 0) {
                            logger.error(brand + ", " + product + " (size " + listOfDisabledSize + ") " + " is failed");
                            listOfFailedElements.add(brand + product + listOfDisabledSize);
                            break;
                        }
                    }
                    if (listOfWrongElements.size() == 0) {
                        System.out.println("correct");
                    }
                    listOfWrongElements.clear();
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
                                    System.out.println("correct");
                                    listOfCorrectElements.add(product);
                                    actions.moveToElement(getWidthFilter()).build().perform();
                                    getWidthFilterButtons().get(k).click();
                                    break;
                                }
                            }
                            if (listOfCorrectElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                                actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                js.executeScript("window.scrollBy(0, 700);");
                            }
                            if (listOfCorrectElements.size() == 1) {
                                break;
                            }
                        }
                        if (listOfCorrectElements.size() == 0) {
                            System.out.println("wrong");
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
                                    System.out.println("wrong");
                                    listOfWrongElements.add(product);
                                    break;
                                }
                            }
                            if (listOfWrongElements.size() == 0 && getProducts().size() < Integer.parseInt((getAmountOfProducts().getText().split(" "))[0])) {
                                actions.moveToElement(getProducts().get(getProducts().size() - 1)).perform();
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                js.executeScript("window.scrollBy(0, 700);");
                            }
                            if (listOfWrongElements.size() != 0) {
                                logger.error(brand + ", " + product + " (size " + listOfDisabledWidth + ") " + " is failed");
                                listOfFailedElements.add(brand + product + listOfDisabledWidth);
                                break;
                            }
                        }
                        if (listOfWrongElements.size() == 0) {
                            System.out.println("correct");
                        }
                        listOfWrongElements.clear();
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

    public void clickClearAllButtonIfExists() {
        try {
            getClearAllButton().click();
        } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException ignored) {
        }
    }

    public int sizeWidthCheck(List<String> listOfFailedElements, List<String> checkList, int count, List<Integer> index, List<Integer> newindex) {
        List<String> clickedButtons = new ArrayList<>();
        System.out.println(index);
        checkList.clear();
        newindex.clear();
        for (int j : index) {
            String brand = getProductBrands().get(j).getText();
            String productName = getProductNames().get(j).getText();
            var image = getProductImages().get(j).getAttribute("src");
            System.out.println(brand + ", " + productName);
            try {
                Actions actions = new Actions(driver);
                openPDP(j);
                PDPPage pdpPage = new PDPPage(driver);
                if (pdpPage.sizesWidthsDisplayed("- all", brand + ", " + productName, listOfFailedElements)) {
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
                    for (String listOfActiveSize : listOfActiveSizes) {
                        for (int k = 0; k < getSizeFilterButtons().size(); k++) {
                            checkActiveSizes(k, listOfActiveSize, brand, productName, image, clickedButtons, listOfFailedElements);
                            if (clickedButtons.size() > 0) {
                                break;
                            }
                        }
                        if (clickedButtons.size() == 0) {
                            actions.moveToElement(getSizeFilter()).build().perform();
                            clickSizesShowMoreButtonIfExists();
                            for (int k = 16; k < getSizeFilterButtons().size(); k++) {
                                checkActiveSizes(k, listOfActiveSize, brand, productName, image, clickedButtons, listOfFailedElements);
                                if (clickedButtons.size() > 0) {
                                    break;
                                }
                            }
                        }
                    }
                    if (listOfDisabledSizes.size() > 0) {
                        checkDisabledSizes(brand, productName, image, listOfDisabledSizes, listOfFailedElements);
                    }
                    checkActiveWidths(brand, productName, image, listOfActiveWidths, listOfFailedElements);
                    if (listOfDisabledWidths.size() > 0) {
                        checkDisabledWidths(brand, productName, image, listOfDisabledWidths, listOfFailedElements);
                    }

                    actions.moveToElement(getClearAllButton()).perform();
                    getClearAllButton().click();
                } else {
                    System.out.println("product doesn't have sizes and widths");
                    driver.navigate().back();
                }
                count++;
                System.out.println(count);
            } catch (Exception e) {
                checkList.add(brand + productName);
                newindex.add(j);
                System.out.println("something was wrong, need to check again");
                clickClearAllButtonIfExists();
            }
        }
        return count;
    }

    public boolean finalSizeWidthCheck() {
        List<String> listOfFailedElements = new ArrayList<>();
        List<String> checkList = new ArrayList<>();
        List<Integer> index = IntStream.rangeClosed(0, getProducts().size() - 1)
                .boxed().collect(Collectors.toList());
//        List<Integer> index = IntStream.rangeClosed(2, 3)
//                .boxed().collect(Collectors.toList());
        List<Integer> newindex = new ArrayList<>();
        int count = 0;
        count = sizeWidthCheck(listOfFailedElements, checkList, count, index, newindex);
        System.out.println(count);
        if (checkList.size() > 0) {
            index = newindex;
            count = sizeWidthCheck(listOfFailedElements, checkList, count, index, newindex);
        }
        if (checkList.size() > 0) {
            index = newindex;
            count = sizeWidthCheck(listOfFailedElements, checkList, count, index, newindex);
        }
        if (checkList.size() > 0) {
            listOfFailedElements.addAll(checkList);
        }
        if (listOfFailedElements.size() > 0) {
            System.out.println(listOfFailedElements + "at active size/width filtering are failed, checked " + count + " " + checkList.size() + " products");
            return false;
        } else {
            return true;
        }
    }
}

