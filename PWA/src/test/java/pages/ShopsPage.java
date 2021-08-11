package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShopsPage extends BasePage {
    public ShopsPage(WebDriver driver) {
        super(driver);
    }
    public boolean isShopsPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Shops']")));
        var list = driver.findElements(By.xpath("//div[text()='Shops']"));
        return list.size() >= 1 && driver.getCurrentUrl().contains("shoebacca.com/shops");
    }
}
