package eg.edu.alexu.csd.datastructure.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {
    public static ExpressionEvaluator evaluator=new ExpressionEvaluator();
//
    @org.junit.Test
    public void infixToPostfix() {
     assertion("2+3*4","2 3 4 * +",14);
     assertion("(1+2)*7","1 2 + 7 * ",21);
     assertEquals("a b * c / ",evaluator.infixToPostfix("a*b/c"));
     assertEquals("a b c - d + /  e a - * c * ",evaluator.infixToPostfix("(a/(b-c+d))*(e-a)*c"));
     assertEquals("a b / c - d e * + a c * -",evaluator.infixToPostfix("a/b-c+d*e-a*c"));
     assertEquals("123 -56 -",evaluator.infixToPostfix("123--56"));
     evaluator.clear();
     System.out.println("Infix to postfix works fine");
     System.out.println("=================================");
    }
    @org.junit.Test
    public void evaluate(){
        assertEquals(0,evaluator.evaluate("2 5 /"));
        assertEquals(2,evaluator.evaluate("5 2 /"));
        assertEquals((int)(-3+6+-8.0/10),evaluator.evaluate(evaluator.infixToPostfix("-3+6+-8/10")));
        assertEquals(14,evaluator.evaluate(evaluator.infixToPostfix("066+14+-66")));
        assertEquals(677,evaluator.evaluate(evaluator.infixToPostfix("68+(245+-244+8*(66+2*5))")));
        try {
            evaluator.evaluate("5 0 /");
        }
        catch (ArithmeticException r){
            System.out.println("Test for division by zero is correct!");
        }
        System.out.println("Evaluation works fine!");
    }

    public void assertion(String expression,String expected,int result){
        expression=evaluator.infixToPostfix(expression);
        int value =evaluator.evaluate(expression);
        assertEquals(expected,expression);
        assertEquals(result,value);
    }



}