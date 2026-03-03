package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestListener.class)
public class AdditionTest extends BaseTest {

    @DataProvider(name = "additionData")
    public Object[][] additionData() {
        return new Object[][]{
                {2, 3, 5},
                {-1, 1, 0},
                {0, 0, 0},
                {5.5, 4.5, 10}
        };
    }

    @Test(dataProvider = "additionData",
            description = "Проверка операции сложения",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class,
            invocationCount = 2,
            threadPoolSize = 2)
    public void testSum(double a, double b, double expected) {
        assertThat(calculator.sum(a, b))
                .as("Проверка суммы чисел %s и %s. Ожидаемый результат: %s", a, b)
                .isEqualTo(expected);
    }
}
