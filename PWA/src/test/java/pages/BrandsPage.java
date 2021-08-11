package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrandsPage extends BasePage {
    public BrandsPage(WebDriver driver) {
        super(driver);
    }
    public boolean isBrandsPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("content")));
        var list = driver.findElements(By.className("content"));
        return list.size() == 1 && driver.getCurrentUrl().contains("shoebacca.com/brands");
    }
}
