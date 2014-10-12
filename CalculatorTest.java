import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest{

	public static void main(String args[]){
		org.junit.JUnitCore.main("~/String-Calculator.CalculatorTest");
	}

	@Test
	public void test(){
		assertEquals(Ã0, Calculator.add(""));
	}
}
