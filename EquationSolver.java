import java.util.*; // import java utilities

public class EquationSolver {

    /**
     * Entry method to evaluate a mathematical expression.
     * Converts infix to postfix, then evaluates postfix.
     */
    public double compute(String expr) {
        DSAQueue postfix = convertToPostfix(expr);
        return processPostfix(postfix);
    }

    /**
     * Converts an infix expression (normal math format)
     * into postfix notation using a stack.
     */
    private DSAQueue convertToPostfix(String expr) {
        DSAStack operatorStack = new DSAStack();   // Stores operators and brackets
        CircularQueue postfixQueue = new CircularQueue(); // Stores postfix output

        String[] parts = expr.split(" "); // Tokenize input by spaces

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue; // skip empty tokens

            char firstChar = part.charAt(0); // store first character of string

            // Case 1: Token is a number
            if (Character.isDigit(firstChar) || (firstChar == '.' && part.length() > 1)) {
                try {
                    postfixQueue.enqueue(Double.valueOf(part));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number: " + part);
                }
            }
            // Case 2: Token is operator or bracket
            else {
                switch (firstChar) {
                    case '(': // Opening bracket
                        operatorStack.push(firstChar);
                        break;

                    case ')': // Closing bracket → pop until '(' is found
                        while (!operatorStack.isEmpty() && (char) operatorStack.top() != '(') {
                            postfixQueue.enqueue(operatorStack.pop());
                        }
                        if (operatorStack.isEmpty()) {
                            throw new IllegalArgumentException("Mismatched parentheses");
                        }
                        operatorStack.pop(); // Remove '('
                        break;

                    // Operators: +, -, *, /
                    case '+': case '-': case '*': case '/':
                        while (!operatorStack.isEmpty()
                                && (char) operatorStack.top() != '('
                                && getPriority((char) operatorStack.top()) >= getPriority(firstChar)) {
                            postfixQueue.enqueue(operatorStack.pop());
                        }
                        operatorStack.push(firstChar);
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid token: " + part);
                }
            }
        }

        // Move any remaining operators to postfix queue
        while (!operatorStack.isEmpty()) {
            char op = (char) operatorStack.pop();
            if (op == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            postfixQueue.enqueue(op);
        }

        displayPostfix(postfixQueue);
        return postfixQueue;
    }

    /**
     * Evaluates a postfix expression using a stack.
     */
    private double processPostfix(DSAQueue queue) {
        DSAStack valueStack = new DSAStack(); // Stores operands

        while (!queue.isEmpty()) {
            Object item = queue.dequeue();

            if (item instanceof Double) { // If number: push to stack
                valueStack.push(item);
            } else { // If operator: pop two operands and evaluate
                double b = (double) valueStack.pop();
                double a = (double) valueStack.pop();
                char operator = (char) item;

                double result = applyOperator(operator, a, b);
                valueStack.push(result);
            }
        }

        return (double) valueStack.pop(); // Final result
    }

    /**
     * Returns operator precedence level.
     * Higher value = higher priority.
     */
    private int getPriority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        throw new IllegalArgumentException("Invalid operator: " + op);
    }

    /**
     * Performs arithmetic operation on two operands.
     */
    private double applyOperator(char op, double x, double y) {
        switch (op) {
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
            case '/': return x / y;
            default: throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    /**
     * Displays postfix expression without losing queue data.
     */
    private void displayPostfix(DSAQueue queue) {
        System.out.print("\nConverted (Postfix): ");
        CircularQueue backup = new CircularQueue(); // Temporary storage

        while (!queue.isEmpty()) {
            Object val = queue.dequeue();
            System.out.print("[" + val + "] ");
            backup.enqueue(val);
        }
        System.out.println();

        // Restore original queue
        while (!backup.isEmpty()) {
            queue.enqueue(backup.dequeue());
        }
    }

    /**
     * Main method for testing multiple expressions.
     */
    public static void main(String[] args) {
        EquationSolver calc = new EquationSolver();

        String[] samples = {
            "( 5 * 2 ) + 12 / ( 3 + 1 )",
            "12 + ( 7 - 3 ) / ( 2 + 4 ) - 5",
            "8 + 2 * ( 3 + 5 ) / ( 7 - 3 )",
            "( 1 + 2 ) * 3 - 4 + ( 5 - 3 ) * 6 + ( 7 - 2 )",
            "10 + 5 * 3 - 8 + 2 / ( 1 + 1 )",
            "5 * ( 3 - 2 + 1 ) + 4"
        };

        for (String s : samples) {
            double result = calc.compute(s);
            System.out.println("\nExpression : " + s);
            System.out.println("Final Answer = " + result);
            System.out.println("\n=====================================");
        }
    }
}