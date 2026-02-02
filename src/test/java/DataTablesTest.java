import org.example.SortableDataTablesPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DataTablesTest {
    @Test
    public void testDataTables(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/tables");
        SortableDataTablesPage tablePage = new SortableDataTablesPage(driver);

        String cell1 = tablePage.getCellText(1, 0, 0);

        Assert.assertNotNull(cell1);

        driver.quit();
    }
}
