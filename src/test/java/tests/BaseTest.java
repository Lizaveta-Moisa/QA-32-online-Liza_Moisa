package tests;

import org.example.Calculator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected Calculator calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();

    }

    @AfterMethod
    public void tearDown() {
        calculator = null;
    }
}
