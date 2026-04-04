package common.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver createChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createChrome();
        }
        return driver;
    }
}
