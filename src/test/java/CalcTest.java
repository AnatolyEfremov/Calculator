import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcTest {

    @Test
    public void test1() {
        Assertions.assertEquals(Main.calc("1+2"),"3");

    }
    @Test
    public void test2() {
        Assertions.assertEquals(Main.calc("VI/III"),"II");

    }
    @Test
    public void test3() {
        Assertions.assertThrows(RuntimeException.class,()->Main.calc("I-II") );

    }
    @Test
    public void test4() {
        Assertions.assertThrows(RuntimeException.class,()->Main.calc("I+1") );

    }
    @Test
    public void test5() {
        Assertions.assertThrows(RuntimeException.class,()->Main.calc("1") );

    }
    @Test
    public void test6() {
        Assertions.assertThrows(RuntimeException.class,()->Main.calc("1+2+3") );

    }
}
