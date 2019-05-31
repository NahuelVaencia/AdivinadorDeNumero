package adivinadornumero;

import java.util.ArrayList;
import java.util.Scanner;

public class AdivinadorNumero {

    /*
    CANT_DIGITOS: constante con la cantidad de dígitos que tiene que tener el número
    numeroAleatorio: Array para guardar los dígitos del número generado
     */
    private static final int CANT_DIGITOS = 4;
    private static ArrayList<Integer> numeroAleatorio;
    private static Scanner sc = new Scanner(System.in);
    private static int respuesta;

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("----------BIENVENIDO----------\n");
        System.out.println("Ingrese el número de la opción del juego que quiere jugar");
        System.out.println("   1. Adivinar un número de 4 cifras");
        System.out.println("   2. Pensar un número de 4 cifras y que éste sea adivinado");
        
        respuesta = controlarOpcion();

        switch (respuesta) {
            case 1:
                //Creo objeto para poder generar el número a adivinar (aleatorio)
                GenerarNumero n = new GenerarNumero();
                numeroAleatorio = n.generarNumero(CANT_DIGITOS);

                //Creo un Objeto PartcipanteH para comenzar a interactuar con el usuario
                ParticipanteH p = new ParticipanteH();
                p.ingresarNumero(numeroAleatorio);

                break;
            case 2:
                System.out.println("\nPiense un número de "+CANT_DIGITOS+" dígitos, donde éstos no se repitan");
                Thread.sleep(7000);
                //Creo un Objeto ParticipanteM para comenzar a interaactuar con la máquina
                ParticipanteM pm = new ParticipanteM(CANT_DIGITOS);
                numeroAleatorio = pm.crearNumero();

                pm.adivinar(numeroAleatorio);
                break;
        }

    }

    /*
    Metodo para controlar que los valores ingresados sean solamente 1 o 2, correspondiente
    a las opciones de juego
    */
    private static int r;
    public static int controlarOpcion() {
        boolean salida = true;
        
        try{
            r = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("---Ingrese el número de una de las opciones listadas anteriormente---");
            controlarOpcion();
            
        }
        
        do {
            if(r<=0 || r>2){
                System.out.println("---Ingrese el número de una de las opciones listadas anteriormente---");
                controlarOpcion();
            }else{
                salida = false;
            }
        } while (salida==true);
        return r;
    }

}
