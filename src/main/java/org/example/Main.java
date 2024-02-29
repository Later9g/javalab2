package org.example;

import org.example.Evaluator.MathExpressionEvaluator;

public class Main {
    public static void main(String[] args) {
        String str = "5 + 2 * 3";

        System.out.println(str + " = " + MathExpressionEvaluator.evaluateExpression(str));
    }

}