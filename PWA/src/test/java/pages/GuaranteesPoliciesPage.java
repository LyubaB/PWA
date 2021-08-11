package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GuaranteesPoliciesPage extends BasePage {
    public GuaranteesPoliciesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGuaranteesPoliciesPage() {
        var list = driver.findElements(By.xpath("//h1[text()='Guarantees & Policies']"));
        return list.size() == 1;
    }

}
