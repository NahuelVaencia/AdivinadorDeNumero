package adivinadornumero;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdivinadorNumero {

    /*
    CANT_DIGITOS: constante con la cantidad de dígitos que tiene que tener el número
    numeroAleatorio: Array para guardar los dígitos del número generado
     */
    public static final int CANT_DIGITOS = 4;
    public static ArrayList<Integer> numeroAleatorio;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        int respuesta = 0;
        System.out.println("----------BIENVENIDO----------\n");
        System.out.println("Ingrese el número de la opción del juego que quiere jugar");
        System.out.println("   1. Adivinar un número de 4 cifras");
        System.out.println("   2. Pensar un número de 4 cifras y que éste sea adivinado");

        respuesta = sc.nextInt();

        while (respuesta <= 0) {
            System.out.println("---Ingrese el número de una de las opciones listadas anteriormente---");
            respuesta = sc.nextInt();
        }

        while (respuesta > 2) {
            System.out.println("---Ingrese el número de una de las opciones listadas anteriormente---");
            respuesta = sc.nextInt();
        }

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
                //Creo un Objeto ParticipanteM para comenzar a interaactuar con la máquina
                ParticipanteM pm = new ParticipanteM(CANT_DIGITOS);
                numeroAleatorio = pm.crearNumero();

                break;
        }

    }

}
