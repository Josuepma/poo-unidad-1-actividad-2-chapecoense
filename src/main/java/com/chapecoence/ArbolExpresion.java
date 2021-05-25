package com.chapecoence;

import java.util.Stack;

/**
 * Clase ArbolExpresion
 */
public class ArbolExpresion {
    final private String patron = "(sin|cos|tan|sqrt)";
    public Nodo build(Stack<Token> stack){
        Stack<Nodo> tree = new Stack<>();
        for (Token token : stack){
            add(token, tree);
        }
        return tree.pop();
    }

    private void add(Token token, Stack<Nodo> stack) {
        if (token.getType() == 0)
            stack.add(new Nodo(token));

        if (token.getData().matches("[\\+\\-\\*\\/\\^]")){
            Nodo temp = new Nodo(token);
            temp.setRight(stack.pop());
            temp.setLeft(stack.pop());
            stack.push(temp);
        }
        if (token.getData().matches(patron)){
            Nodo temp = new Nodo(token);
            temp.setRight(stack.pop());
            stack.push(temp);
        }
    }
    public double solve(Nodo root) {
        if (root == null)
            return 0;
        if (root.getLeft() == null && root.getRight() == null)
            return Double.parseDouble(root.getValue().getData());
        double left = solve(root.getLeft());
        double right = solve(root.getRight());

        switch (root.getValue().getData()) {
            case "sin":
                return Math.sin(right);
            case "cos":
                return Math.cos(right);
            case "tan":
                return Math.tan(right);
            case "sqrt":
                return Math.sqrt(right);
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "^":
                return Math.pow(left, right);
            default:
                return left / right;
        }
    }
}
