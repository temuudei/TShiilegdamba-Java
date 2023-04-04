import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


class CalculatorTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calcShouldReturnNumberIfOneNumberPassed() {
        //Assign
        String testNum = "1";
        //Act
        int result =Calculator.Add(testNum);

        assertEquals(1,result);

    }

    @Test
    void calcShouldReturnZeroIfEmptyStringIsPassed() {
        //Assign
        String testNum = "";
        //Act
        int result =Calculator.Add(testNum);

        assertEquals(0,result);

    }

    @Test
    void calcShouldReturnSumOf2StringNums() {
        //Assign
        String testNum = "3,5";
        //Act
        int result =Calculator.Add(testNum);

        assertEquals(8,result);

    }

    @Test
    void calcShouldReturnSumOf2StringNumsWithEmptyString() {
        //Assign
        String testNum = "2,,5";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(7,result);

    }

    @Test
    void calcShouldReturnSumOf2StringNumsWithNegative() {
        //Assign
        String testNum = "2,-5";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(-3,result);

    }

    @Test
    void calcShouldReturnFailureWithNewLineAtEnd() {
        //Assign
        String testNum = "2,2\n";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(4,result);

    }

    @Test
    void calcShouldReturnFailureWithNewLineAtStart() {
        //Assign
        String testNum = "\n1,1";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(2,result);

    }

    @Test
    void calcShouldReturnSumOf3ValuesWithNewLine() {
        //Assign
        String testNum = "1\n2,3";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(6,result);

    }

    @Test
    void calcShouldReturnErrorWithOnlyNewLine() {
        //Assign
        String testNum = "\n";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(0,result);
    }

    @Test
    void calcShouldReturnSumOf3ValuesWithNewLineBeingOwnValue() {
        //Assign
        String testNum = "1,\n,3";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(-1,result);

    }

    @Test
    void calcShouldReturnSumOf3ValuesWithNewDelimiter() {
        //Assign
        String testNum = "//;\n1;2";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(3,result);

    }
    @Test
    void calcShouldReturnSumOf3ValuesWithBrokenNewDelimiter() {
        //Assign
        String testNum = "//;;\n1;2";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(-1,result);

    }
    @Test
    void calcShouldReturnSumOf3ValuesWithBrokenDelimiterNotation() {
        //Assign
        String testNum = "/;\n1;2";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(-1,result);

    }

    @Test
    void calcShouldReturnSumOf3ValuesWithNumberDelimiter() {
        //Assign
        String testNum = "//1\n112";

        //Act
        int result =Calculator.Add(testNum);

        assertEquals(2,result);

    }
}