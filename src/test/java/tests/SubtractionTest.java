package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtractionTest extends BaseTest {

    @Test(description = "Проверка вычитания положительных чисел",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class)
    public void testSubtractPositive() {
        assertThat(calculator.subtract(10, 5))
                .as("Проверка разности 10 и 5. Ожидаемый результат: 5")
                .isEqualTo(5);
    }

    @Test(description = "Проверка вычитания отрицательных чисел",
            priority = 3)
    public void testSubtractNegative() {
        assertThat(calculator.subtract(-5, -5))
                .as("Проверка разности -5 и -5. Ожидаемый результат: 0")
                .isEqualTo(0);
    }

    @Test(description = "Проверка вычитания, когда результат отрицательный",
            priority = 4)
    public void testSubtractNegativeResult() {
        assertThat(calculator.subtract(5, 10))
                .as("Проверка разности 5 и 10. Ожидаемый результат: -5")
                .isEqualTo(-5);
    }
}
