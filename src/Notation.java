public class Notation {

    public Notation() {

    }

    /**
     * Evaluates a postfix expression from a string to a double
     *
     * @param postfixExpr The postfix expression in String format
     * @return The evaluation of the postfix expression as a double
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        NotationStack<Double> evaluate = new NotationStack<>();
        for (int i = 0; i < postfixExpr.length(); i++) {
            char check = postfixExpr.charAt(i);
            try {
                //checking for only numbers 1 through 9 on ASCII table
                if (check < 58 && check > 47)
                    evaluate.push((double) check - 48);
                switch (check) {
                    case '/':
                    case '*':
                    case '+':
                    case '-':
                        //must have at least 3 characters, 2 numbers and one mutator
                        //otherwise invalid
                        if (evaluate.size() < 2)
                            throw new InvalidNotationFormatException(postfixExpr);
                        else {
                            double a = evaluate.pop();
                            double b = evaluate.pop();
                            switch (check) {
                                case '+' -> evaluate.push(a + b);
                                case '/' -> evaluate.push(b / a);
                                case '-' -> evaluate.push(b - a);
                                case '*' -> evaluate.push(a * b);
                            }
                        }
                        //break;
                }
            } catch (StackOverflowException | StackUnderflowException e) {
                e.printStackTrace();
            }
        }

        if (evaluate.size() != 1)
            throw new InvalidNotationFormatException(postfixExpr);

        return Double.parseDouble(evaluate.toString());

    }

    /**
     * Convert the Postfix expression to the Infix expression
     *
     * @param postfix The postfix expression in string format
     * @return The infix expression in string format
     * @throws InvalidNotationFormatException If the Postfix expression format is invalid
     */
    public static String convertPostfixToInfix(String postfix)
            throws InvalidNotationFormatException {
        NotationStack<String> changed = new NotationStack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);
            try {
                if (current < 58 && current > 47)
                    changed.push(String.valueOf(current));
                switch (current) {
                    case  '/', '*', '+', '-' -> {
                        if (changed.size() < 2)
                            throw new InvalidNotationFormatException(postfix);
                        String a = changed.pop();
                        changed.push("(" + changed.pop() + current + a + ")");
                    }
                }
            } catch (StackOverflowException | StackUnderflowException e) {
                e.printStackTrace();
            }

        }
        if (changed.size() != 1)
            throw new InvalidNotationFormatException(postfix);
        return changed.toString();
    }

    /**
     * Convert an infix expression into a postfix expression
     *
     * @param infix The infix expression in string format
     * @return The postfix expression in string format
     * @throws InvalidNotationFormatException If the infix expression format is invalid
     */
    public static String convertInfixToPostfix(String infix)
            throws InvalidNotationFormatException {
        NotationQueue<Character> convert = new NotationQueue<>();
        NotationStack<Character> symbol = new NotationStack<>();

        for (int i = 0; i < infix.length(); i++) {
            char current = infix.charAt(i);

            try {

                /*If the current character in the infix is a digit,
                copy it to the postfix solution queue*/
                if (current < 58 && current > 47)
                    convert.enqueue(current);
                /*If the current character in the infix is a left
                parenthesis, push it onto the stack*/
                if (current == '(')
                    symbol.push(current);
                /*
                If the current character in the infix is an
                operator
                1) Pop operators(if there are any) at the top
                of the stack while they have equal or higher
                precedence than the current operator, and
                insert the popped operators in postfix solution
                queue
                 */

                switch (current) {
                    case '/':
                    case '*':
                    case '+':
                    case '-':
                        /*
                            2)Push the current character in the
                            infix onto the stack
                             */
                        if (symbol.size() != 0) {
                            /*
                                2)Push the current character in the
                                infix onto the stack
                                */
                            if (symbol.top() == '/') {
                                symbol.push(current);
                            } else {
                                symbol.push(current);
                            }
                        }
                         /*2)Push the current character in the
                            infix onto the stack*/

                        else
                            symbol.push(current);
                }
                /*
                If the current character in the infix is a right
                parenthesis
                1) Pop operators from the top of the stack and insert
                them in postfix solution queue until a left parenthesis
                is at the top of the stack, if no left parenthesis throw
                error
                 */
                if (current == ')') {
                    while (symbol.top() != '(') {
                        convert.enqueue(symbol.pop());
                        if (symbol.size() == 0)
                            throw new InvalidNotationFormatException(infix);
                    }
                    /*2)Pop(and discard) the left parenthesis from the stack*/
                    symbol.pop();
                }
            } catch (QueueOverflowException | StackOverflowException | StackUnderflowException e) {
                e.printStackTrace();
            }
        }
        try {
            /*When the infix expression has been read,
            Pop any remaining operators and insert them
            in postfix solution queue.*/
            while (symbol.size() != 0)
                convert.enqueue(symbol.pop());
        } catch (QueueOverflowException | StackUnderflowException e) {
            e.printStackTrace();

        }


        return convert.toString();

    }
}
