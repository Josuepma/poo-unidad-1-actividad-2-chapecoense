package com.chapecoence;

import java.io.IOException;

/**
 * Clase Token
 */
public class Token {
    private int type;
    private Operador operador = new Operador();
    private Operando operando = new Operando();

    Token(int type, String value) throws IOException {
        this.type = type;
        if (this.type == 1){
            operador.setData(value, (int)setPrecedence(value));
        }else{
            operando.setNumber(Double.parseDouble(value));
        }
    }

    private int setPrecedence(String value) throws IOException {
        int precedence = -1;
        if (value.matches("[\\(\\)]"))
            precedence = 0;
        if (value.matches("(sin|cos|tan)"))
            precedence = 4;
        else if (value.matches("(\\^|sqrt)"))
            precedence = 3;
        else if (value.matches("[\\/\\*]"))
            precedence = 2;
        else if (value.matches("[\\+\\-]"))
            precedence = 1;
        if (precedence == -1)
            throw new IOException("Operador inválido \"" + value + "\".");
        else
            return precedence;
    }

    /**
     * Obtiene el valor del operador o operando
     * @return String que representa el valor del operador o operando
     */
    public String getData(){
        if (this.type == 1)
            return operador.toString();
        else
            return operando.toString();
    }

    /**
     * Obtiene el valor precedente del operador
     * @return int Valor entero de presedencia
     */
    public int getPrecedence(){
        return operador.getPrecedence();
    }

    /**
     * Obtiene el tipo
     * @return int que es el tipo
     */
    public int getType() {
        return this.type;
    }

    /**
     * Token toString método
     * @return string de la información del token
     */
    @Override
    public String toString() {
        if (this.type == 1)
            return operador.toString();
        else
            return operando.toString();
    }
}
