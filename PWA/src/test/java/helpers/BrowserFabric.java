package helpers;

import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserFabric {
    public static WebDriver getDriver(BrowserType type) {
        switch (type) {
            case CHROME: return getChromeDriver();
            case EDGE: return getEdgeDriver();
            case OPERA: return getOperaDriver();
            default: return getFirefoxDriver();
        }
    }
    private static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new ChromeDriver();
    }
    private static WebDriver getEdgeDriver(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        return new EdgeDriver();
    }
    private static WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        return new FirefoxDriver();
    }
    private static WebDriver getOperaDriver(){
        System.setProperty("webdriver.opera.driver", "operadriver.exe");
        return new OperaDriver();
    }
}
