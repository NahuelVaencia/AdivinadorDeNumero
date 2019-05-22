package adivinadornumero;

import java.util.ArrayList;

public class GenerarNumero {       
    /*
    numAleatorio: guarda todos los digitos generados, su tamaño depende de cantDigitos
    */
    ArrayList<Integer> numAleatorio = new ArrayList<>();
    
    //METODO PARA GENERAR EL NÚMERO ALEATORIO
    public ArrayList<Integer> generarNumero(int cantDigitos) throws InterruptedException{
        System.out.println("Generando un numero de 4 digitos para que usted lo adivine. Por favor aguarde");
        Thread.sleep(1000);
        
        /*
        cantDigitos: cantidad de digitos que tendrá el numero generado
        digitoAleatorio: variable donde guardo el dígito aleatorio generado
        */
        int digitoAleatorio;
        
        for (int i = 0; i < cantDigitos;) {
            digitoAleatorio = (int) (Math.random()*10);
            
            //Control para que los números no se repitan
            while(!(numAleatorio.contains(digitoAleatorio))){
                numAleatorio.add(digitoAleatorio);
                i++;
            }       
        }
        
        //convertirString(numAleatorio);
        
        return numAleatorio;
    }
    
    //este metodo no es necesario
    public void convertirString(ArrayList<Integer> numAleatorio){
        //numero: guarda el numero de 4 digitos sin separador
        String numero="";
        //Convierto todos los números del arrayList a un variable String
        for (int i = 0; i < numAleatorio.size(); i++) {
            numero = numero + String.valueOf(numAleatorio.get(i));    
        }
        System.out.println("El numero generado es: "+numero);
    }
}
