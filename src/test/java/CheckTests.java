import org.example.check.Check;
import org.junit.Test;

public class CheckTests {

    String expression1 = "1 + !";
    String expression2 = "1 + 2 * (2 - 2()";
    String expression3 = "1 ++ ";

    // Проверка на лишние символы
    @Test(expected = RuntimeException.class)
    public void badSignsTest(){
        Check.doCheck(expression1);
    }

    // Проверка на пустые скобки
    @Test(expected = RuntimeException.class)
    public void badBracketsTest(){
        Check.doCheck(expression2);
    }

    // Проверка на сдвоенные знаки
    @Test(expected = RuntimeException.class)
    public void doubledSignsTest(){
        Check.doCheck(expression3);
    }
}
