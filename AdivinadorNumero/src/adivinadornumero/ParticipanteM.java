package adivinadornumero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParticipanteM {

    /*
    cantDigito: entero donde se almacena la cantidad de dígitos que tiene el númnero
    numAleatorio: array con los dígtos del número
     */
    int cantDigito;
    ArrayList<Integer> numAleatorio = new ArrayList<>();

    public ParticipanteM(int cantDigitos) {
        cantDigito = cantDigitos;
    }

    /*
    Método para generar un número aleatorio que será el número de partida
    para preguntar al usuario
     */
    public ArrayList<Integer> crearNumero() throws InterruptedException {
        /*Creación de un objeto GeneradorDeNumero, para posteriormente llamar al
        metodo que crea el número
         */
        GenerarNumero gdn = new GenerarNumero();
        numAleatorio = gdn.generarNumero(cantDigito);

        return numAleatorio;
    }
}
