import org.example.HoversPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class HoversTest {
    @Test
    public void testHovers() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/hovers");
        HoversPage page = new HoversPage(driver);

        for (int i = 1; i <= 3; i++) {
            page.hoverOverProfile(i);
            String name = page.getProfileName(i);
            Assert.assertFalse(name.isEmpty());
        }
        driver.quit();
    }
}
