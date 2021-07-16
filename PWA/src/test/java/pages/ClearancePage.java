package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClearancePage extends BasePage {
    public ClearancePage(WebDriver driver) {
        super(driver);
    }
    public boolean isClearancePage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Clearance')]")));
        return driver.getCurrentUrl().contains("shoebacca.com/clearance-warehouse");
    }
}
