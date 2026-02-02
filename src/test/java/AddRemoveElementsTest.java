import org.example.AddRemoveElementsPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AddRemoveElementsTest {

    @Test
    public void testAddRemoveElements() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver);
        
        page.addElement();
        page.addElement();
        Assert.assertEquals(page.getElementsCount(), 2);

        page.removeElement();
        Assert.assertEquals(page.getElementsCount(), 1);
        driver.quit();
    }
}
