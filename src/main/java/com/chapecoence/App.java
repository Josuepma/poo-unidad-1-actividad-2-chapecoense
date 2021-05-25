package com.chapecoence;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Calculadora calculadora = new Calculadora();
        try{
            while(calculadora.run()){
            }
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }

    }
}
