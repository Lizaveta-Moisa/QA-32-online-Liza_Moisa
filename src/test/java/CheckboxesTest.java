import org.example.CheckboxesPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CheckboxesTest {

    @Test
    public void testCheckboxes(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        CheckboxesPage page = new CheckboxesPage(driver);

        Assert.assertFalse(page.isChecked(0));
        Assert.assertTrue(page.isChecked(1));

        page.setCheckbox(0, true);
        Assert.assertTrue(page.isChecked(0));

        page.setCheckbox(1, false);
        Assert.assertFalse(page.isChecked(1));

        driver.quit();
    }

}
