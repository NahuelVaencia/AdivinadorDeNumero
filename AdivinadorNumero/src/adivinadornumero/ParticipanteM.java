package adivinadornumero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParticipanteM {

    /*
    cantDigito: entero donde se almacena la cantidad de dígitos que tiene el númnero
    numSiguiente: array con los dígitos del número
    numero: valor del npumero que se está evaluando en un momento determinado. Es entero
    numeroString: variable para poder mostrar todos los digitos del número cuando el mismo
    empieza con 0.
    repetidos: almacena un valor booleano de acuerdo a si la variable numSiguiente
    tiene valores repetidos o no (true = valores repetidos/false = valores no repetidos)
     */
    int cantDigito;
    ArrayList<Integer> numSiguiente = new ArrayList<>();
    int numero;
    String numeroString;
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

    ArrayList<Integer> clave = new ArrayList<>();

    public ParticipanteM(int cantDigitos) {
        cantDigito = cantDigitos;
    }

    /*
    Método para generar un número aleatorio que será el número de partida
    para preguntar al usuario
     */
    public ArrayList<Integer> crearNumero() throws InterruptedException {
        //numAleatorio: array con los dígtos del número
        ArrayList<Integer> numAleatorio;
        /*
        Creación de un objeto GeneradorDeNumero, para posteriormente llamar al
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
        numeroString = convertirString(numAleat);

        System.out.println("\n-----EL NUMERO QUE POSIBLEMENTE PENSÓ ES " + numeroString + "------");

        System.out.println("¿Cuántos dígitos están en su ubicación?");
        try {
            respuesta = sc.nextLine();
            cantBien = Integer.parseInt(respuesta);
        } catch (NumberFormatException nfe) {
            System.out.println("-----------------------------------");
            System.out.println("INGRESE SOLO NUMEROS POR FAVOR");
            System.out.println("-----------------------------------\n");
            adivinar(numAleat);
        }

        if (cantBien == cantDigito) {
            System.out.println("       JUEGO TERMINADO       ");
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.out.println("------TU NÚMERO ES " + numero +"------");
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.exit(0);
        }
        /*
        Llamo al método que controla que el valor ingresado esté entre el
        rango 0-cantDigitos
         */
        controlarOpcion(cantBien, numAleat);

        System.out.println("¿Cuántos dígitos estan en el número pero no en su posición correcta?");
        try {
            respuesta = sc.nextLine();
            cantRegular = Integer.parseInt(respuesta);
        } catch (NumberFormatException nfe) {
            System.out.println("-----------------------------------");
            System.out.println("INGRESE SOLO NUMEROS POR FAVOR");
            System.out.println("-----------------------------------\n");
            adivinar(numAleat);
        }

        /*
        Llamo al método que controla que el valor ingresado esté entre el
        rango 0-cantDigitos
         */
        controlarOpcion(cantRegular, numAleat);

        /*Una vez ingresada la respuesta la coloco en un arreglo. De esta forma,
        para el número preguntado tengo una respuesta específica. 
        Por lo que utilizo un HasMap(clave,valor), llamado preguntados, donde 
        clave es el arreglo con los dígitos del número preguntado, y valor es la
        respuesta dada por el pensador del número.
         */
        resp.add(cantBien);
        resp.add(cantRegular);

        preguntados.put(numAleat, resp);
        //System.out.println(preguntados);

        //Verificar respuestas anteriores
        compararValorAnterior(numSiguiente);

    }

    /*
    Controla si los valores ingresados por el usuarios sean enteros positivos
    y que no sea mayor a la cantidad de dígits que tiene el número
     */
    public void controlarOpcion(int r, ArrayList<Integer> numAleat) {

        if (r < 0) {
            System.out.println("---Ingrese un valor positivo---");
            adivinar(numAleat);
        } else {
            if (r > cantDigito) {
                System.out.println("---Ingrese una valor que este en en rango de"
                        + " 0 a " + cantDigito + "---");
                adivinar(numAleat);
            }
        }

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

    /*
    Convierte el arreglo que tiene los digitos del número, para luego poder preguntar
    al participante. 
     */
    public String convertirString(ArrayList<Integer> numAleatorio) {
        //numero: guarda el numero de 4 digitos sin separador
        String numero = "";
        //Convierto todos los números del arrayList a un variable String
        for (int i = 0; i < numAleatorio.size(); i++) {
            numero = numero + String.valueOf(numAleatorio.get(i));
        }
        return numero;
    }

    /*
    Incrementa el número aleatorio generado al principio en un digito, para poder
    continuar con la evaluación
     */
    public int incrementarAdivinar(int num) {
        num = num + 1;
        return num;
    }

    /*Método que convierte el número entero en un arreglo con los dígitos
    del número, y además controla que el número, al ser incrementado, no tenga 
    más de 4 dígitos
     */
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

    /*
    Método que recibe el siguiente número al que se pregunto por primera vez,
    evalúa la cantidad de dígitos "bien y regular" del dicho número recibido
    y por último compara el respuesta del usuario con lo evaluado anteriormente.
    Si, tanto el valor de bien como el regular coinciden, entonces se lo pregunta
    al usuario, sino coinciden incrementa el número nuevamente y repite el proceso.
    
    Simpre evalúa el siguinte número con las condiciones de todos los número preguntados
    anteriormente
     */
    public void compararValorAnterior(ArrayList<Integer> siguiente) {
        /*
        bien y regular: variables para controlar 
         */
        int bien = 0;
        int regular = 0;

        do {
            /*
            Una vez que se preguntó el número al particpante, se debe incrementar,
            por lo menos una vez, para evaluar si el siguiente número al 
            preguntado anteriormente,es candidato o no.
            Esto lo va a repetir mientras el valor de la variable repetidos
            sea igual a true.
             */
            do {
                numero = incrementarAdivinar(numero);
                numSiguiente = convertirArray(numero);

                repetidos = verificarRepetidos(numSiguiente);

            } while (repetidos == true);

            /*
            Salida para comprobar que va evaluando todos los números incrementando de a uno
             */
            //System.out.println("El siguiente sin repetidos es " + numSiguiente);
            /*
            Por cada uno de los números ya preguntados, verifico si el siguiente
            generado cumple con las condiciones (respuestas) ingresadas por el
            participante anteriormente.
            
            Por ejemplo: 
            Ya preguntados con sus respectivas respuestas
            [5,3,2,4]->[1,2]
            [5,4,0,2]->[0,2]
            [6,2,3,4]->[] Para que éste número sea mostrado por pantalla, se compara 
            con cada uno de los número anteriores, verificando que cumpla las
            condiciones.
            6234 respecto a 5324 debe cumplir con 1Bien y 2Regular y
            respecto a 5402 debe cumplir con 0Bien y 2Regular
             */
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

                /*
                Si al valuar con el primer número preguntado no cumple las condiciones,
                no evalúo el siguiente, ya que deben cumplir para todos los números
                 */
                if ((preguntados.get(clave)).get(0) != bien || (preguntados.get(clave)).get(1) != regular) {
                    break;
                }
            }
            /*
            Mientras no cumpla las condiciones incremento el número, hasta que esto
            se cumpla
             */
        } while ((preguntados.get(clave)).get(0) != bien || (preguntados.get(clave)).get(1) != regular);

        /*
        Cuando cumple las condiciones, le pregunta al usuario si es el número
         */
        adivinar(numSiguiente);

    }

    /*
    Verifica si el número incrementado tiene dígitos repetidos, en caso afirmativo
    devuelve true.
     */
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
