package org.example.Evaluator;

import org.example.check.Check;

import java.util.Stack;

public class MathExpressionEvaluator {
    /**
     * Вычисляет значение математического выражения, представленного в виде строки.
     *
     * @param expression строка с математическим выражением
     * @return результат вычисления выражения
     */
    public static double evaluateExpression(String expression) {
        expression = Check.doCheck(expression);

        // Удаляем все пробелы из выражения
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--;
                operands.push(Double.parseDouble(operand.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    double result = applyOperation(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.pop(); // Удаляем открывающую скобку
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    double result = applyOperation(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.push(c);
            }
        }

        while (!operators.empty()) {
            double result = applyOperation(operators.pop(), operands.pop(), operands.pop());
            operands.push(result);
        }

        return operands.pop();
    }

    /**
     * Проверяет, имеет ли оператор1 больший приоритет, чем оператор2.
     *
     * @param op1 первый оператор
     * @param op2 второй оператор
     * @return true, если оператор1 имеет больший приоритет, чем оператор2, иначе false
     */
    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 != '(' && op2 != ')') && (op1 != '*' && op1 != '/');
    }

    /**
     * Применяет операцию к двум операндам.
     *
     * @param operator оператор
     * @param b        второй операнд
     * @param a        первый операнд
     * @return результат операции
     */
    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                return a / b;
        }
        return 0;
    }
}