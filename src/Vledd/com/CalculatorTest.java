package Vledd.com;

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
        c.calc(c.list);
        Assertions.assertEquals(otvet, c.getResult());
    }

    @ParameterizedTest
    @MethodSource("DataValidate")
    public void TestValidate(String primer, boolean otvet) throws Exception {
        Assertions.assertEquals(otvet, !new Calculator(primer).validate(primer).existError);
    }

    public static List<Object[]> DataValidate() {
        return Arrays.asList(new Object[][]{
                {"0+0", true},
        });
    }

    public static List<Object[]> Data() {
        return Arrays.asList(new Object[][]{
                {"1--1","2"},
                {"1+1","2"},
                {"[2-4]","2"},
                {"2+(sin30*8)/2","4"},
                {"(1+1)","2"},
                {"(2*(16/2)+[3-10*sin30])","18"},
                {"[-4*(32-28)/sin30]","32"},
                {"[-4*(32-(28-2))/sin30]","48"}
        });
    }
}