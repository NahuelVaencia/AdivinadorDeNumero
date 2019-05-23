package adivinadornumero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParticipanteM {
    /*
    cantDigito: entero donde se almacena la cantidad de dígitos que tiene el númnero
    numAleatorio: array con los dígtos del número
    numSiguiente: array con los dígitos del número
    numero: valor del npumero que se está evaluando en un momento determinado. Es entero
    repetidos: almacena un valor booleano de acuerdo a si la variable numSiguiente
    tiene valores repetidos o no (true = valores repetidos/false = valores no repetidos)
    */
    int cantDigito;
    ArrayList<Integer> numAleatorio = new ArrayList<>();
    ArrayList<Integer> numSiguiente = new ArrayList<>();
    int numero;
    boolean repetidos = false;
    
    /*
    sc: Objeto para poder leer lo ingresado por teclado
    */
    Scanner sc = new Scanner(System.in);
    
    /*
    preguntados: para clav valor, para almacenar el número que se le preguntó a la
    persona, con su correspondiente valor
    */
    HashMap<ArrayList<Integer>, ArrayList<Integer>> preguntados = new HashMap<>();
    //////clave??
    ArrayList<Integer> clave = new ArrayList<>();
    
    public ParticipanteM(int cantDigitos) {
        cantDigito = cantDigitos;
    }

    
    /*
    Método para generar un número aleatorio que será el número de partida
    para preguntar al usuario
     */
    public ArrayList<Integer> crearNumero() throws InterruptedException {
        System.out.println("Generando un numero de 4 digitos para que usted lo adivine. Por favor aguarde");
        Thread.sleep(1000);
        
        /*Creación de un objeto GeneradorDeNumero, para posteriormente llamar al
        metodo que crea el número
        */
        GenerarNumero gdn = new GenerarNumero();
        numAleatorio = gdn.generarNumero(cantDigito);
        
        return numAleatorio;
    }

    /*
    Método que pregunta al usuario por la cantidad de dígitos que se encuentran
    en su correcto lugar (cantBien) y los que no estan en la posicion correcta
    (cantRegular)
    */
    public void adivinar(ArrayList<Integer> numAleat) {
        /*
        respuesta: almacena temporalmente las respuestas del usuario
        resp: Array donde se guarda definitivamente las respues del usuario. [bien,regular]
        */
        String respuesta;
        ArrayList<Integer> resp = new ArrayList<>();
        
        /*
        variales que almacenan la respuesta de usuario, pero en otro tipo de dato (int)
        */
        int cantBien = 0;
        int cantRegular = 0;

        numero = convertirInt(numAleat);

        System.out.println("\nEL NUMERO QUE POSIBLEMENTE PENSÓ ES " + numero);

        System.out.println("¿Cuántos dígitos están en su ubicación?");
        try {
            respuesta = sc.nextLine();
            cantBien = Integer.parseInt(respuesta);
        } catch (NumberFormatException nfe) {
            System.out.println("-----------------------------------");
            System.out.println("INGRESE SOLO NUMEROS POR FAVOR");
            System.out.println("-----------------------------------\n");
        }

        System.out.println("¿Cuántos dígitos estan en el numero pero no en su posicion correcta?");
        try {
            respuesta = sc.nextLine();
            cantRegular = Integer.parseInt(respuesta);
        } catch (NumberFormatException nfe) {
            System.out.println("-----------------------------------");
            System.out.println("INGRESE SOLO NUMEROS POR FAVOR");
            System.out.println("-----------------------------------\n");
        }

        /*Una vez ingresada la respuesta la coloco en un arreglo. De esta forma,
        para el número preguntado tengo una respuesta específica. 
        Por lo que utilizo un HasMap(clave,valor), donde clave es el arreglo con
        los dígitos del número preguntado, y valor es la respuesta dada por el 
        pensador del número.
        */
        resp.add(cantBien);
        resp.add(cantRegular);

        preguntados.put(numAleat, resp);
        //System.out.println(preguntados);

        if (cantBien == cantDigito) {
            System.out.println("Ganeee");
            System.exit(0);
        }
      
        //Verificar respuestas anteriores
        compararValorAnterior(numSiguiente);

    }

    public void nose() {

    }

    
    /*
    Método utilizado para convertir a entero el número generado aleatoriamente, y
    asi poder trabajar con él
    */
    public int convertirInt(ArrayList<Integer> numAleatorio) {
        //numeroString: guarda el numero de 4 digitos sin separador
        String numeroString = "";
        //Convierto todos los números del arrayList a un variable String
        for (int i = 0; i < numAleatorio.size(); i++) {
            numeroString = numeroString + String.valueOf(numAleatorio.get(i));
        }
        //parseo a int
        int numeroInt = Integer.parseInt(numeroString);
        

        return numeroInt;
    }

    public int incrementarAdivinar(int num) {
        num = num + 1;

        return num;
    
    }

    public ArrayList<Integer> convertirArray(int n) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < cantDigito; i++) {
            int digito = n % 10;
            n = n / 10;
            //Control dígitos todos diferentes
            a.add(0, digito);
        }
        return a;
    }

    public void compararValorAnterior(ArrayList<Integer> siguiente) {
        
        int bien = 0;
        int regular = 0;

        do {

            do {
                //incremento
                numero = incrementarAdivinar(numero);
                numSiguiente = convertirArray(numero);
                //verifico repetidos
                repetidos = verificarRepetidos(numSiguiente);

            } while (repetidos == true);
            System.out.println("El siguiente sin repetidos es " + numero);

            for (ArrayList<Integer> key : preguntados.keySet()) {
                bien = 0;
                regular = 0;
                //System.out.println(key);
                clave = key;
                for (int i = 0; i < numSiguiente.size(); i++) {
                    if (key.contains(numSiguiente.get(i))) {
                        if (key.get(i) == numSiguiente.get(i)) {
                            bien = bien + 1;
                        } else {
                            regular = regular + 1;
                        }
                    }
                }
                
                if ((preguntados.get(clave)).get(0) != bien || (preguntados.get(clave)).get(1) != regular) {
                    break;
                }
                
            }
            
        } while ((preguntados.get(clave)).get(0) != bien || (preguntados.get(clave)).get(1) != regular);

        adivinar(numSiguiente);

    }

    public boolean verificarRepetidos(ArrayList<Integer> siguiente) {
        repetidos = false;
        for (int i = 0; i < siguiente.size(); i++) {
            for (int j = 0; j < siguiente.size(); j++) {
                if (i != j) {
                    if (siguiente.get(i) == siguiente.get(j)) {
                        repetidos = true;
                    }
                }
            }
        }
        return repetidos;
    }
}
