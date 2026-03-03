package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class DivisionTest extends BaseTest {

    @Test(description = "Проверка операции деления",
            priority = 6)
    public void testDivide() {
        assertThat(calculator.divide(10, 2))
                .as("Проверка деления 10 на 2. Ожидаемый результат: 5")
                .isEqualTo(5);
    }

    @Test(description = "Проверка выброса исключения при делении на ноль",
            priority = 7)
    public void testDivideByZero() {
        assertThatThrownBy(() -> calculator.divide(10, 0))
                .as("Проверка, что при делении на 0 выбрасывается ArithmeticException")
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
    }
}
