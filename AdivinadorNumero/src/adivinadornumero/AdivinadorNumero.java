package adivinadornumero;

import java.util.ArrayList;

public class AdivinadorNumero {
    /*
    CANT_DIGITOS: constante con la cantidad de dígitos que tiene que tener el número
    numeroAleatorio: Array para guardar los dígitos del número generado
    */
    public static final int CANT_DIGITOS=4;
    public static ArrayList<Integer> numeroAleatorio;
    
    public static void main(String[] args) throws InterruptedException {
        //Creo objeto para poder generar el número a adivinar (aleatorio)
        GenerarNumero n = new GenerarNumero();
        numeroAleatorio = n.generarNumero(CANT_DIGITOS);
        
        //Creo un Objeto Partcipante para comenzar a interactuar con el usuario
        ParticipanteH p = new ParticipanteH();
        p.ingresarNumero(numeroAleatorio);
        
    }
    
}
