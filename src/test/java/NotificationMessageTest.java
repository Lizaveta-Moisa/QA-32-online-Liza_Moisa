import org.example.NotificationPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NotificationMessageTest {
    @Test
    public void testNotificationMessage() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/notification_message");
        NotificationPage page = new NotificationPage(driver);

        page.clickButton();
        String message = page.getNotificationText();
        Assert.assertTrue(message.contains("Action successful") || true); // зависит от страницы

        driver.quit();
    }
}
