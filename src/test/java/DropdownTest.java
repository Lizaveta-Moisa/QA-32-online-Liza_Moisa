import org.example.DropdownPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DropdownTest {
    @Test
    public void testDropdown(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        DropdownPage page = new DropdownPage(driver);

        page.selectOption(1);
        Assert.assertEquals("Option 1", page.getSelectedOptionText());

        page.selectOption(2);
        Assert.assertEquals("Option 2", page.getSelectedOptionText());

        driver.quit();
    }
}
