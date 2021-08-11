package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailPopupWindow extends BasePage {
    public EmailPopupWindow(WebDriver driver) {
        super(driver);
    }
    private WebElement getIframeEmailPopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='attentive_creative']")));
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("No email popup window");
        }
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

    public boolean emailPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getIframeEmailPopup()));
            getIframeEmailPopup().isDisplayed();
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void closeEmailPopup() {
        if (emailPopupDisplayed()) {
            driver.switchTo().frame(getIframeEmailPopup());
            getCrossEmailPopupIcon().click();
            driver.switchTo().defaultContent();
        }
    }

    public void clickOutside() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id='attentive_creative']")));
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
}
