package com.chapecoence;

/**
 * Clase Operador
 */
public class Operador {
    private String operador;
    private int precedence;
    /**
     * Constructor del Operador con valores nulos
     */
    Operador(){
        this.operador = null;
        this.precedence = 0;
    }

    /**
     * Constructor del operador con dos parámetros
     *
     * @param operador String que representa el operador
     * @param precedence int valor que representa la precedencia
     */
    Operador(String operador, int precedence){
        this.operador = operador;
        this.precedence = precedence;
    }

    /**
     * Pone el char del operador
     * @param operador de la operación
     */
    public void setData(String operador, int precedence) {
        this.operador = operador;
        this.precedence = precedence;
    }

    /**
     * Obtiene el valor string del operador
     * @return valor String del operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Obtiene la precedencia del operador
     * @return int valor de precedencia
     */
    public int getPrecedence() {
        return this.precedence;
    }

    /**
     * Representa el Operador como string
     * @return el valor en formato String
     */
    @Override
    public String toString() {
        return this.operador;
    }
}
