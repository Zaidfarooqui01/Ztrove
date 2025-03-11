package com.example.ztrove.features;

public class ExpressionEvaluator {
    public double evaluate(String expression) {
        return evaluateExpression(expression);
    }

    private double evaluateExpression(String expression) {
        // Remove spaces
        expression = expression.replaceAll("\\s+", "");

        // Handle addition and subtraction
        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            return evaluateExpression(parts[0]) + evaluateExpression(parts[1]);
        } else if (expression.contains("-")) {
            String[] parts = expression.split("\\-");
            return evaluateExpression(parts[0]) - evaluateExpression(parts[1]);
        }

        // Handle multiplication and division
        if (expression.contains("*")) {
            String[] parts = expression.split("\\*");
            return evaluateExpression(parts[0]) * evaluateExpression(parts[1]);
        } else if (expression.contains("/")) {
            String[] parts = expression.split("\\/");
            return evaluateExpression(parts[0]) / evaluateExpression(parts[1]);
        }

        // Handle parentheses
        if (expression.startsWith("(") && expression.endsWith(")")) {
            return evaluateExpression(expression.substring(1, expression.length() - 1));
        }

        // Handle numbers
        return Double.parseDouble(expression);
    }
}
