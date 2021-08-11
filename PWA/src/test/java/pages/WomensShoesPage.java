package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WomensShoesPage extends BasePage {
    public WomensShoesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isWomensShoesPage() {
        var list = driver.findElements(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
        return list.size() == 1;
    }

    public void openSTG() {
        driver.get("https://stg.shoebacca.com/womens-shoes.html");
    }

}