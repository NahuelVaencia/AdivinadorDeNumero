package adivinadornumero;

import java.util.ArrayList;
import java.util.Scanner;

public class ParticipanteH {
    /*
    numero: variable para guardar el valor ingresado por el usaurio
    numAdivinaor: variable para guardar el numero que el usuario ingresa convertido a entero
    Hago esta diferencia porque si lo tomo como entero directamente, si el usuario ingresa
    el número 0123 por ejemplo, se guardará 123 solamente y yo necesito el numero con
    los 4 dígitos
    cantIntento: variable que se irá incrementando por cada uno de los nitentos que realice 
    el usuario para adivinar el número
    */
    int cantIntento;
    String numero;
    int numAdivinador;
    
    // sc: objeto para leer el npumero que ingresa el usuario
    Scanner sc = new Scanner(System.in);
    
    /*
    numeroAdivinador: es un array donde se guardarán los digitos del número
    ingresado por el usuario, de forma separada
    */
    ArrayList<Integer> numeroAdivinador = new ArrayList<>();
    
    public void ingresarNumero(ArrayList<Integer> numeroAleatorio){
        cantIntento ++ ;
        //solicitar y leer el número ingresado por el usuario
        System.out.println("\nINGRESE UN NUMERO POSITIVO DE 4 DIGITOS DIFERENTES");
        System.out.println("Puede comenzar con 0(cero)");
        
        try{
            numero = sc.nextLine();
            numAdivinador = Integer.parseInt(numero);
        }catch(NumberFormatException nfe){
            System.out.println("-----------------------------------");
            System.out.println("INGRESE SOLO NUMEROS POR FAVOR");
            System.out.println("-----------------------------------\n");
            ingresarNumero(numeroAleatorio);
        }
        
        //Control para que el número ingresado sea solo positivo
        if(numAdivinador<0){
            System.out.println("-----------------------------------");
            System.out.println("EL NÚMERO DEBE SER POSITIVO");
            System.out.println("-----------------------------------\n");
            ingresarNumero(numeroAleatorio);
        }
        
        /*
        Control longitud del número ingresado
        Acá utilizo la variable numero (String) para controlar que tenga 4 números
        Si usara el entero en ingreso 0123 no tomaría el cero
        */
        int long_numAdivinador = numero.length();
        if( long_numAdivinador != numeroAleatorio.size()){
            System.out.println("-----------------------------------");
            System.out.println("EL NUMERO DEBE TENER "+ numeroAleatorio.size() +" DIGITOS SOLAMENTE");
            System.out.println("-----------------------------------\n");
            ingresarNumero(numeroAleatorio);
        }
        
        /*
        cada uno de los dígitos del numero ingresado lo guardamos por separado 
        en un Array para luego compararlo con los digitos del numero aleatrio
        generado automaticamente
        */        
        
        for (int i = 0; i < long_numAdivinador; i++) {
            int digito = numAdivinador%10;
            numAdivinador = numAdivinador/10;
            //Control dígitos todos diferentes
            if(!(numeroAdivinador.contains(digito))){
                numeroAdivinador.add(0, digito);
            }else{
                System.out.println("-----------------------------------");
                System.out.println("INGRESE TODOS DIGITOS DIFERENTES");
                System.out.println("-----------------------------------\n");
                numeroAdivinador.clear();
                ingresarNumero(numeroAleatorio);
            }
        }
        //System.out.println(numeroAdivinador);

        //Metodo usado para verificar la posicion de cada digito del numero
        comprobarNumero(numeroAdivinador, numeroAleatorio);
        
    }
    
    public void comprobarNumero(ArrayList<Integer> numAdivin, ArrayList<Integer> numAleatorio){
        int cantB=0;
        int cantR=0;
        
        /*
        Verifico si cada uno de los dígitos del número ingresado por el 
        participante se encuentran en la posición correcta o no, de acuerdo
        al número generado automaticamente
        */
        for (int i = 0; i < numAdivin.size(); i++) { 
            if(numAleatorio.contains(numAdivin.get(i))){
                if(numAleatorio.get(i) == numAdivin.get(i)){
                    cantB ++;
                }else{
                    cantR ++;
                }
            } 
        }
        
        if(cantB==numAleatorio.size()){
            System.out.println("FELICITACIONES, ADIVINASTE EL NÚMERO");
            System.out.println("La cantidad de intentos fue de " + cantIntento);
            System.exit(0);
        }else{
            System.out.println(cantB+ "Bien y " + cantR+ "Regular");
            System.out.println("\nINTENTE NUEVAMENTE");
            numAdivin.clear();
            ingresarNumero(numAleatorio);
        }
        
    }
}
