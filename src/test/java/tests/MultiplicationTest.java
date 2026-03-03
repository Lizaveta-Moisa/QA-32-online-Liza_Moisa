package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationTest extends BaseTest {

    @Test(description = "Проверка операции умножения",
            priority = 4)
    public void testMultiply() {
        assertThat(calculator.multiply(3, 4))
                .as("Проверка произведения 3 и 4. Ожидаемый результат: 12")
                .isEqualTo(12);
    }

    @Test(description = "Проверка умножения на ноль",
            priority = 5)
    public void testMultiplyByZero() {
        assertThat(calculator.multiply(5, 0))
                .as("Проверка умножения 5 на 0. Ожидаемый результат: 0")
                .isEqualTo(0);
    }

    @Test(description = "Проверка умножения отрицательного и положительного числа",
            priority = 6)
    public void testMultiplyNegative() {
        assertThat(calculator.multiply(-3, 4))
                .as("Проверка произведения -3 и 4. Ожидаемый результат: -12")
                .isEqualTo(-12);
    }
}
