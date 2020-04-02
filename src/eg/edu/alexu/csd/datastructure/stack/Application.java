package eg.edu.alexu.csd.datastructure.stack;


public class Application {
    public static IStack stack=new Stack();

    /**
     * The main representing an application that implements:
     * 1-Stack program
     * 2-Expressions program.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("This application is to work on stacks or expressions.");
        int choice=-1;
        while(true){
            System.out.println("********************************************");
            choice = UI.getInt("1-Stack Program\n2-Expressions program.\n0-Exit\nEnter your choice: \r");
            if(choice==1)
                stackImplement();
            else if(choice==2)
                expressionsImplement();
            else if(choice==0)
                return;
            else{
                System.out.println("Error Enter valid input!");
            }
        }


    }
//////////////////////////////Stack////////////////////////////////////////////////////

    /**
     * This is the stack application code.
     */
    public static void stackImplement () {
        System.out.println("This program is used to test the stack.");
        boolean quit = false;
        while (!quit) {
            System.out.println("1: Push\t\t" +
                    "2: Pop\t\t" +
                    "3: Peek\t\t" +
                    "4: Get size\t\t" +
                    "5: Check if empty" +
                    "\t-1:Back");
            int choice = UI.getInt("Enter your choice:\r");
            switch (choice) {
                case 1:
                    push();
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    try {
                        System.out.println("The top element is " + stack.peek());
                    }
                    catch(RuntimeException r){
                        System.out.println(r.toString());
                    }
                    break;
                case 4:
                    System.out.println("The size is " + stack.size());
                    break;

                case 5:
                    System.out.println(stack.isEmpty());
                    break;
                case -1:
                    quit = true;
                    break;
                default:
                    System.out.println("Error");

            }
        }
    }

    /**
     * Those methods represent stack operations.
     */
    public static void push() {
        Object line = UI.getLine("Enter the object please: \r");
        stack.push(line);
        System.out.println("(" + line.toString() + " ) is added.");
    }
    public static void pop() {
        try {
            System.out.println(stack.pop().toString() + " is popped.");
        } catch (RuntimeException r) {
            System.out.println(r.toString());
        }
    }
    ////////////////////////////////Expressions////////////////////////////////////

    /**
     * This is the expressions program code.
     */
    public static void expressionsImplement(){
        System.out.println("The Expression program.");
        while(true) {
            int choice=UI.getInt("1-Enter expression\t\t-1-Back\nEnter your choice: \r");
            if(choice==1) {
                String exp = UI.getLine("Enter a statement in infinix: \r") + "";
                try {
                    showResult(exp);
                }
                catch (RuntimeException r){
                    System.out.println(r.toString());
                }
            }
            else if(choice==-1)
                return;
            else
                System.out.println("Error Enter valid input!");
        }
    }

    /**
     * This methods prints the postfix expression and the it's value.
     * @param expression
     * The expression
     */
    public static void showResult(String expression){
        ExpressionEvaluator x=new ExpressionEvaluator();
//        System.out.print("The Expression is:");
        System.out.print(x.infixToPostfix(expression));
        System.out.print("\nThe result is ");
        int value=x.evaluate(x.infixToPostfix(expression));
        System.out.println("The result is "+value);
        System.out.println("=================================================");
    }

}
