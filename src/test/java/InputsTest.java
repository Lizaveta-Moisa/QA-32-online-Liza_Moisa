import org.example.InputsPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InputsTest {
    @Test
    public void testInputs(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/inputs");
        InputsPage page = new InputsPage(driver);

        page.enterValue("123");

        page.enterValue("abc");

        driver.quit();
    }
}
