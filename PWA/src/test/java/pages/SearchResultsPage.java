package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver){super((driver));}

    public boolean isSearchResultsPage() {
        var list = driver.findElements(By.xpath("//h2[text()='SEARCH RESULTS FOR: ']"));
        return list.size() == 1;
    }
    public String query(){
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'breadcrumbs-current')]")));
    String url = driver.getCurrentUrl();
    return url.split("/")[5];
    }

    public String checkQuery() {
        return getCurrentBreadcrumbs().getText().split("'")[1];
    }
}

