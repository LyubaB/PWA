package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private WebElement getLearnMoreAboutShoebacca() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']")));
        return driver.findElement(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']"));
    }

    public boolean isHomePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getLearnMoreAboutShoebacca());
        var list = driver.findElements(By.xpath("//*[text()='LEARN MORE ABOUT SHOEBACCA']"));
        return list.size() == 1;
    }
}






















