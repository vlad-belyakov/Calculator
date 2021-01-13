package Vledd.com.test;

import Vledd.com.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
class CalculatorTest {

    @ParameterizedTest
    @MethodSource("Data")
    public void TestCalc(String primer, String otvet) throws Exception {
        var c = new Calculator(primer);
        c.calc();
        Assertions.assertEquals(otvet, c.getResult());
    }

    public static List<Object[]> Data() {
        return Arrays.asList(new Object[][]{
            {"0+0","0"},
            {"0+1","1"},
            {"1+99","100"},
            {"1+1-(2+2)","-2"},
            {"1-1","0"},
            {"2*2","4"}
        });
    }
}