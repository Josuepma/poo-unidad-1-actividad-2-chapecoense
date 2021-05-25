package com.chapecoence;

/**
 * Clase Nodo
 */
public class Nodo {
    private Token value;
    private Nodo left, right;

    /**
     * Constructor que toma el nodo
     * @param token que contiene el valor
     */
    Nodo(Token token){
        this.value = token;
        this.right = null;
        this.left = null;
    }

    /**
     * Pone el valor del nodo derecho
     * @param Nodo Del nodo padre a la derecha
     */
    public void setRight(Nodo Nodo){
        this.right = Nodo;
    }

    /**
     * Pone el valor del nodo izquierdo
     * @param Nodo del nodo padre a la izquierda
     */
    public void setLeft(Nodo Nodo){
        this.left = Nodo;
    }

    /**
     * Obtiene el valor del nodo izquierdo
     * @return
     */
    public Nodo getLeft() {
        return this.left;
    }

    /**
     * Obtiene el valor del nodo derecho
     * @return
     */
    public Nodo getRight() {
        return this.right;
    }

    /**
     * Obtiene el valor del nodo
     * @return
     */
    public Token getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        String str;
        if (right == null && left == null) {
            str = value.toString();
        } else if (left == null) {
            str = value.toString() + "(" + right.toString() + ")";
        } else if (right == null) {
            str = "(" + left.toString() + ")" + value.toString();
        } else {
            str = "(" + left.toString() + value.toString() + right.toString() + ")";
        }
        return str;
    }
}
