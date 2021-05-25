package com.chapecoence;

/**
 * Clase Operando
 */
public class Operando {
    private double number;
    /**
     * Constructor que no recibe parámetros, pone un valor por defecto
     */
    Operando(){
        this.number = 0;
    }

    /**
     * Constructor que pone el valor recibido
     * @param number que es el operando
     */
    Operando(double number){
        this.number = number;
    }

    /**
     * Pone el numero que es usado
     * @param number
     */
    public void setNumber(double number) {
        this.number = number;
    }

    /**
     * Obtiene el numero
     * @return double valor del operando
     */
    public double getNumber() {
        return number;
    }

    /**
     * Representa el operando como string
     * @return el valor dado en formato string
     */
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
