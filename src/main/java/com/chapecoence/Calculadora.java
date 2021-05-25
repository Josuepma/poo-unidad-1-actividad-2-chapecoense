package com.chapecoence;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Clase Calculadora
 */
public class Calculadora {
    private String expresion;
    private ArrayList<Double> x = null;
    final private String patronTrigo = "(sin|cos|tan|sqrt)";

    /**
     * Constructor que define el valor por defecto
     */
    Calculadora (){
        this.expresion = null;
    }

    /**
     * Obtiene expresion
     * @return string of the expresion
     */
    public String getExpresion(){
        return this.expresion;
    }

    /**
     * Set the expresion
     * @param str String with the expresion
     */
    public void setExpresion(String str){
        this.expresion = str;
    }

    public boolean run() throws IOException {
        Scanner leer = new Scanner(System.in);

        if (this.expresion == null){
            System.out.println("Ingrese una expresion matemática");
            System.out.println("Utiliza !exit para salir");
            this.expresion = leer.nextLine();
        }

        if (this.expresion.contains("!exit")){
            return false;
        }

        if (this.expresion.contains("read")){
            System.out.println(this.expresion.split(""));
        }

        System.out.println("Resultado: " + compute(this.expresion));
        this.expresion = null;
        return true;
    }
    public Stack<Token> infixToPostfix(List<Token> list) {
        Stack<Token> conversion = new Stack<>();
        Stack<Token> stack = new Stack<>();
        for (Token token : list){
            if (token.getType() == 0)
                conversion.add(token);
            if (token.getData().matches(patronTrigo))
                stack.add(token);
            if (token.getData().matches("[\\+\\-\\*\\/\\^]")){
                if (token.getData().matches("[\\+\\-\\*\\/]")){
                    if (!stack.isEmpty()){
                        while (stack.peek().getPrecedence() >= token.getPrecedence()){
                            conversion.add(stack.pop());
                            if (stack.isEmpty())
                                break;
                        }
                    }
                    stack.add(token);
                }
                if (token.getData().equals("^")){
                    if (!stack.isEmpty()){
                        while (stack.peek().getPrecedence() > token.getPrecedence()){
                            conversion.add(stack.pop());
                            if (stack.isEmpty())
                                break;
                        }
                    }
                    stack.add(token);
                }
            }
            if (token.getData().matches("[\\(\\)]")){
                if (token.getData().equals("("))
                    stack.add(token);
                if (token.getData().equals(")")){
                    while(!stack.peek().getData().equals("("))
                        conversion.add(stack.pop());
                    stack.pop();
                    if (!stack.isEmpty() && stack.peek().getData().matches(patronTrigo))
                        conversion.add(stack.pop());
                }
            }
        }
        while (!stack.isEmpty())
            conversion.add(stack.pop());
        return conversion;
    }
    public List<Token> tokenization(String expresion) throws IOException{
        expresion = clean(expresion);
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(expresion));
        tokenizer.ordinaryChar('/');
        List <Token> list = new ArrayList<>();

        while(tokenizer.nextToken() != StreamTokenizer.TT_EOF){
            Token token;
            if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                token = new Token(0, String.valueOf(tokenizer.nval));
            }else if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                token = new Token(1, tokenizer.sval);
            }else{
                token = new Token(1, String.valueOf((char)tokenizer.ttype));
            }list.add(token);
        }

        return list;
    }

    /**
     *
     * Método que guarda los valores de cada X
     * @param valores el cual recibe los valores
     */
    private void setxValores(String valores){
        valores = valores.replace('[','0');
        valores = valores.replace(']','0');

        String [] auxiliar = valores.split(",");

        try {
            for (int i = 0; i < auxiliar.length; i++) {
                this.x.add(Double.parseDouble(auxiliar[i]));
                System.out.println(Double.parseDouble(auxiliar[i]));
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("El valor ingresado no es un numero" + e.getMessage());
        }
    }

    private String clean(String str) {
        String cleaned = str.replace(" ", "");

        cleaned = cleaned.replace("[", "(");
        cleaned = cleaned.replace("]", ")");
        cleaned = cleaned.toLowerCase();

        if (cleaned.contains("x") && !cleaned.contains("_")) {
            if (x == null){
                System.out.println("Ingresa el valor de x:");
                Scanner leer = new Scanner(System.in);
                String auxiliar = leer.next();
                this.setxValores(auxiliar);
            }
            cleaned = replace(cleaned);
        }
        cleaned = cleaned.replaceAll("(\\d+)-(\\d+)", "$1 - $2");
        return cleaned;
    }

    private String replace(String str) {
        str = str.replaceAll("(\\d+)x", "$1*" + x);
        return str.replace("x", String.valueOf(x));
    }

    public double compute(String expresion) throws IOException{
        List<Token> infix = tokenization(expresion);
        Stack<Token> postfix= infixToPostfix(infix);
        ArbolExpresion et = new ArbolExpresion();
        Nodo root = et.build(postfix);
        return et.solve(root);
    }

    public String toString(){
        return new StringBuilder().append("La ecuacion ingresada es ").append(this.expresion).toString();
    }
}
