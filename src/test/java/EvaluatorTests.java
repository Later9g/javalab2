import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static junit.framework.TestCase.assertEquals;
import static org.example.Evaluator.MathExpressionEvaluator.evaluateExpression;

public class EvaluatorTests {

    String expression1 = "2 + 3";
    String expression2 = "2 + 3 - (1 * 1)";
    String expression3 = "2 + a - 3";

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    //Тест на решение простого выражения
    @Test
    public void easyExpressionTest(){
        assertEquals(evaluateExpression(expression1),5.0d);
    }

    //Тест на решение выражений со скобками
    @Test
    public void expressionWithBracketsTest(){
        assertEquals(evaluateExpression(expression2),4.0d);
    }

    //Тест на решение выражения с пользовательским вводом
    @Test
    public void expressionWithInputTest(){
        systemInMock.provideText("3");
        double answer = evaluateExpression(expression3);
        assertEquals(answer,2.0);
    }
}
