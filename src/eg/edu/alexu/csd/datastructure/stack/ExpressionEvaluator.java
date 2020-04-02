package eg.edu.alexu.csd.datastructure.stack;


public class ExpressionEvaluator implements IExpressionEvaluator {
    /**
     * Letters:LinkedList representing the constants in expression
     * nums:LinkedList representing corresponding value of constant.
     */
    private SingleLinked letters = new SingleLinked();
    private SingleLinked nums = new SingleLinked();

    /**
     * The infix to postfix method
     * first:We check that the expression is not empty.
     *Now we loop through the expression.
     *  if a number is found then it is added to stack.
     * By moving through the expression only * and / will be evaluated.
     *if any of the 2 symbols was found then we pop 2 expressions and add the symbol to end then push back to stack.
     * if (+)or(-) is found then they will be added to stack.
     * When loop is over.
     * We reverse the stack to get first expressions.
     * The (+) and (-) will be evaluated now.And resulted expression is added to string(postifix).
     * @param expression infix expression
     *                   expression must be a valid statement containing no spaces.
     * @return postfix :
     *      A posfix expression that has space between each operand.
     */
    @Override
    public String infixToPostfix(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new RuntimeException("Incorrect Expression!");
        }
        Stack stat = new Stack();
        Stack statement = new Stack();
        String postfix = "";
        boolean negative = false;
        for (int i = 0; i < expression.length(); i++) {
            char symb = expression.charAt(i);
            String num = "";
            if (isDigit(symb) || isLetter(symb)) {
                if (isDigit(symb)) {
                    if (negative) {
                        num += "-";
                        negative = false;
                    }
                    while (i < expression.length() && isDigit(expression.charAt(i))) {
                        num += expression.charAt(i);
                        i++;
                    }//we have number.
                    i--;
                } else {//It's a letter.
                    num = symb + "";
                    if (!isThere(symb)) {
                        letters.add(symb);
                    }
                }
                if (stat.isEmpty())
                    stat.push(num);
                else {
                    if ((stat.peek() + "").equals("*") || (stat.peek() + "").equals("/")) {
                        String operator = "" + stat.pop();
                        postfix += stat.pop() + " " + num + " " + operator;
                        stat.push(postfix);
                        postfix = "";
                    } else
                        stat.push(num);
                }

            }//Negative
            else if (symb == '-') {
                if (stat.isEmpty() || ((stat.peek() + "").equals("+") || (stat.peek() + "").equals("-") || (stat.peek() + "").equals("/") || (stat.peek() + "").equals("*"))) {
                    negative = true;
                } else {
                    stat.push(symb);
                }
            } else if (symb == '*' || symb == '/' || symb == '+') {
                if (stat.isEmpty() || (stat.peek() + "").equals("+") || (stat.peek() + "").equals("-") || (stat.peek() + "").equals("/") || (stat.peek() + "").equals("*")) {
                    throw new RuntimeException("Incorrect Expression!");
                }
                stat.push(symb);
            } else if (symb == '(') {
                String exp = "";
                if(negative){
                    exp+="-1*(";
                }
                i++;
                Stack paren = new Stack();
                paren.push('(');
                while (i < expression.length() && !paren.isEmpty()) {
                    if (expression.charAt(i) == '(')
                        paren.push('(');
                    else if (expression.charAt(i) == ')')
                        paren.pop();
                    if (paren.isEmpty())
                        break;
                    exp += expression.charAt(i);
                    i++;
                }
                if (!paren.isEmpty()) {
                    throw new RuntimeException("Incorrect Expression!");
                }
                if(negative) {
                    exp += ")";
                    negative=false;
                }
                exp = infixToPostfix(exp);
                if (stat.isEmpty())
                    stat.push(exp);
                else {
                    if ((stat.peek() + "").equals("*") || (stat.peek() + "").equals("/")) {
                        String x = "" + stat.pop();
                        postfix += stat.pop() + " " + exp + " " + x;
                        stat.push(postfix);
                        postfix = "";
                    } else
                        stat.push(exp);
                }
            } else {
                throw new RuntimeException("Incorrect Expression!");
            }
        }//Now the remaining are + and -.
        while (stat.size() != 0)
            statement.push(stat.pop());
        //It is reversed.
        int count = 0;
        String flag = "";
        while (statement.size() != 0) {
            if (count % 2 == 0) {
                if ((statement.peek() + "").equals("+") || (statement.peek() + "").equals("-")) {
                    throw new RuntimeException("Incorrect Expression!");
                }
                postfix +=statement.pop() + " " + flag;
            } else {
                if(statement.size()==2)
                    flag = statement.pop()+"" ;
                else
                    flag = statement.pop() + " ";
                if (statement.isEmpty()) {
                    throw new RuntimeException("Incorrect Expression!");
                }
            }
            count++;
        }
        return postfix;
    }

    /**
     *if constants are saved then we ask the user to get their values.
     *We push 2 expressions to stack,then they are popped when an operator is encountered.
     *Then the operation is made and expression is pushed to stack again.
     * @param expression postfix expression
     * @return
     */

    @Override
    public int evaluate(String expression) {
        assignValues();
        if (expression == null)
            throw new NullPointerException("Expression not found!");
        Stack operation = new Stack();
        //The expression is written in postfix.
        for (int i = 0; i < expression.length(); i++) {
            char symb = expression.charAt(i);
            if (symb == '-' && i + 1 < expression.length() && isDigit(expression.charAt(i + 1))) {
                String num = "-";
                i = i + 1;
                while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                    num += expression.charAt(i);
                    i++;
                }
                i--;
                int number = Integer.parseInt(num);//To convert string to int.
                operation.push(number);
            } else if (isDigit(symb) || isLetter(symb)) {
                String num = "";
                if (isDigit(symb)) {
                    while (i < expression.length() && isDigit(expression.charAt(i))) {
                        num += expression.charAt(i);
                        i++;
                    }
                    i--;
                } else {
                    num += getValue(symb);
                }
                int number = Integer.parseInt(num);//To convert string to int.
                operation.push(number);
            } else if (symb == '+' || symb == '-' || symb == '/' || symb == '*') {
                if (operation.isEmpty())
                    throw new RuntimeException("Incorrect Expression!");
                String temp = operation.pop() + "";
                float num2 = Float.parseFloat(temp);
                if (operation.isEmpty())
                    throw new RuntimeException("Incorrect Expression!");
                temp = operation.pop() + "";
                float num1 = Float.parseFloat(temp);
                float value = evaluate(num1, num2, symb);
                operation.push(value);
            } else if (symb == ' ') {
            } else {
                throw new RuntimeException("Incorrect Expression!");
            }
        }
        if (operation.size() != 1)
            throw new RuntimeException("Incorrect Expression!");
        clear();
        float value = Float.parseFloat(operation.pop() + "");
        return (int) value;
    }

    /**
     * The method returns the result of the operation between nums
     * @param num1
     *first number
     * @param num2
     * second number
     * @param operator
     * The operator
     * @return result of expression
     */

    private static float evaluate(float num1, float num2, char operator) {

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0)
                    throw new ArithmeticException("Division by 0 is not allowed!");
                return num1 / num2;
        }
        throw new RuntimeException("Incorrect Expression!");
    }

    /**
     *Return true if parameter is a letter
     * False otherwise
     * @param symb
     * The character to be examined.
     * @return true if a letter
     */
    private boolean isLetter(char symb) {
        return (symb >= 'a' && symb <= 'z') || (symb >= 'A' && symb <= 'Z');
    }

    /**
     * Check whether character is a digit or not.
     * @param symb
     * @return true if a digit
     */
    private boolean isDigit(char symb) {
        return symb >= '0' && symb <= '9';
    }

    /**
     *It gets the value of constant saved.
     * @param letter
     * The letter representing the constant.
     * @return value of letter
     */
    private int getValue(char letter) {
        for (int i = 0; i < letters.size(); i++) {
            if ((char) letters.get(i) == letter)
                return (int) nums.get(i);
        }
        throw new RuntimeException("Constant not found!");
    }

    /**
     * The method is part of UI.
     * It asks to insert the value of each constant.
     */
    public void assignValues() {
        for (int i = 0; i < letters.size(); i++) {
            int value = UI.getInt("Enter the value of " + (char) letters.get(i) + ": \r");
            nums.add(value);
        }
    }

    /**
     * Check whether the letter is already saved or not.
     * @param letter
     * @return true if letter is found.
     */

    private boolean isThere(char letter) {
        for (int i = 0; i < letters.size(); i++) {
            if (letter == (char) letters.get(i))
                return true;
        }
        return false;
    }

    /**
     * Reset letters list.
     */
    public void clear(){
        letters.clear();
        nums.clear();
    }
}
