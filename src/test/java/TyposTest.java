import org.example.TyposPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TyposTest {
    @Test
    public void testTypos(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/typos");
        TyposPage page = new TyposPage(driver);

        String text = page.getParagraphText();
        Assert.assertTrue(text.contains("left" /* или нужное слово */));

        driver.quit();
    }
}
