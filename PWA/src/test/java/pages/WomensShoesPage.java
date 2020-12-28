package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WomensShoesPage extends BasePage {
    public WomensShoesPage (WebDriver driver){
        super(driver);
    }
    private WebElement getWomensShoesCategoryHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]")));
        return driver.findElement(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
    }
    public boolean isWomensShoesPage () {
        var list = driver.findElements(By.xpath("//h2[text()=\"WOMEN'S SHOES\"]"));
        return list.size()==1;
    }
}
