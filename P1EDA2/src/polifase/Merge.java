package polifase;
import dato.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase que contiene  a los métodos necesarios para hacer merge de los bloques
 * de datos de dos archvos. Los bloques de datos deben estár separados por un
 * salto de linea y las diferentes interaciones del archivo por un arroba.
 * 
 * En esta clase se pueden encontar tres métodos, uno para cada criterio de
 * ordenamiento.
 * @author Ricardo López
 */
public class Merge {
    String folderPath;
    /**
     * Este constructor obtiene la ruta al fonder donde están los archivos 
     * F1, F2, F3 y F4 con ayuda de una instacia de la clase FilesDirect. La ruta
     * al fonder se alamacena en como una string en el atributo foderPath.
     */
    public Merge (){
        FilesDirect ruta = new FilesDirect();
        folderPath = ruta.rutaFolder();
    }
/**
 * Este método une bloques de datos de dos archivos. Los bloques de datos deben
 * estar separados por un salto de linea y las diferentes iteraciones del archivo
 * por un arroba. Los archivos de lectura son leerR y leerL
 * La escritura de los bloques unidos se van intercambiando entre escL y escR.
 * Para hacer las comparfaciones necesarias para el merge se utiliza el operador
 * de relación "menor-igual que" y el atributo de número de cuenta de los 
 * objetos alumno.
 * Este método es recursivo. Su caso base es cuando en alguno de los archivos 
 * de lectura ya no hay más elementos en una interación, lo que significa que 
 * todos los datos están en el otro. Al hacer la llamada recursiva, leerR se 
 * convierte en escR, leerL en escL, escL en leerL y escR en leerR.
 * @param leerR Archivo de lectura 1. Archivo de "la derecha". En la primera 
 * iteración este archivo es F1
 * @param leerL Archivo de lectura 2. Archivo de "la izquierda".En la primera
 * iteración este archivo es F2.
 * @param escR Archivo de escritura 1. Archivo de escritura de "la derecha". En la
 * primera iteración este archivo es F3.
 * @param escL Archivo de escritura 2. Archivo de escritura de "la inzquierda". En la
 * primera iteración este archivo es F4.
 * @param iteration Parametro para saber en que llamada recursiva se está. 
 * @throws IOException 
 */
    public void mergeNum(String leerR, String leerL, String escR, String escL, int iteration) throws IOException{        

        File r = new File(leerR);
        File l = new File(leerL); 
        FileReader rR = new FileReader(r);
        FileReader rL = new FileReader(l);
        BufferedReader checkR = new BufferedReader(rR);
        BufferedReader checkL = new BufferedReader(rL);
        String strR = checkR.readLine();
        String strL = checkL.readLine();
        

        
             //No se crearán archivos ahora
            
        /*
        Si strL, strR ,strWL o strWR son nulos, significa que se llegó al final
        de su respectivo archivo.
        
        Si strL , strR, strWL o strWR son "@" Se llego al inicio de una
             iteración

        si strL, strR, strWL o strWR están vacías, se llegó al final de 
        un bloque.
        */     
        int numeroArrobas;
        if(iteration%2==0){
            numeroArrobas = iteration/2;
        }else{
            numeroArrobas = (iteration+1)/2;
        }
        /*
        Los siguentes while colocan al lector en la iteración correcta 
        */
        int countArrobas=1;
        while(countArrobas<numeroArrobas){
            strR = checkR.readLine();
            if(strR.equals("@")){
                countArrobas++;
            }
        }
       countArrobas=1;
        while(countArrobas<numeroArrobas){

            strL = checkL.readLine();
            if(strL.equals("@")){
                countArrobas++;
            }
        }

        //Lee la primera linea de la iteracion
        strR = checkR.readLine();
        strL = checkL.readLine();
        
        if(strR.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerL);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
        if(strL.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerR);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
        
        
        
        
        if(!strR.isEmpty()&&!strL.isEmpty()){     
            Dato transDato = new Dato();
            boolean archivo = true;
            //Añade el @indicando el incio de iteración
            transDato.addString(escR,"@\n");
            transDato.addString(escL,"@\n");
            while(strR!=null&&strL!=null){
                /*
                Mientras no se llege al final de del bloque o de la iteración
                */
                while((strR!=null&&!strR.isEmpty())&&(strL!=null&&!strL.isEmpty())){
                    Alumno alumR;
                    Alumno alumL;
                    alumR = transDato.obtenerDato(strR);
                    alumL = transDato.obtenerDato(strL);

                    if(alumL.getNoCuenta()<=alumR.getNoCuenta()){
                        if(archivo){

                            transDato.escribirDato(alumL,escL);
                            //Se elimino la repeticion de folderpath, la cadena
                            //ya lo incluye
                        }else{
                            transDato.escribirDato(alumL,escR);
                        }
                        strL=checkL.readLine();
                    }else{
                        if(archivo){
                            transDato.escribirDato(alumR,escL);
                        }else{
                            transDato.escribirDato(alumR,escR);
                        }
                        strR=checkR.readLine();
                    }

                }
                while(strR!=null&&!strR.isEmpty()){
                    Alumno alumR;
                    alumR = transDato.obtenerDato(strR); 
                    if(archivo){
                        transDato.escribirDato(alumR,escL);
                    }else{
                        transDato.escribirDato(alumR,escR);
                    }
                        strR=checkR.readLine();
                }
                while(strL!=null&&!strL.isEmpty()){
                    Alumno alumL;
                    alumL = transDato.obtenerDato(strL); 
                    if(archivo){
                        transDato.escribirDato(alumL,escL);
                    }else{
                        transDato.escribirDato(alumL,escR);
                    }
                    strL=checkL.readLine();
                }
                if(archivo){
                    transDato.addString(escL,"\n");
                }else{
                    transDato.addString(escR,"\n");
                }
                archivo=!archivo;
                strR = checkR.readLine();
                strL = checkL.readLine();
            }
            while(strR!=null&&!strR.isEmpty()){
                Alumno ultimoBloque = transDato.obtenerDato(strR);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                strR=checkR.readLine();
            }
     
            while(strL!=null&&!strL.isEmpty()){
                
                Alumno ultimoBloque = transDato.obtenerDato(strL);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                    strL=checkL.readLine();
            }
            
             if(archivo){
                transDato.addString(escL,"\n");
            }else{
                transDato.addString(escR,"\n");
                }
            rL.close();
            rR.close();
            checkL.close();
            checkR.close();
            iteration++;

            mergeNum(escR,escL,leerR, leerL, iteration);        
        }
        rL.close();
        rR.close();
        checkL.close();
        checkR.close();
    }
    /**
 * Este método une bloques de datos de dos archivos. Los bloques de datos deben
 * estar separados por un salto de linea y las diferentes iteraciones del archivo
 * por un arroba. Los archivos de lectura son leerR y leerL
 * La escritura de los bloques unidos se van intercambiando entre escL y escR.
 * Para hacer las comparaciones necesarias para el merge se utiliza el atributo 
 * de apellido de los objetos Alumno y el método compareTo de las cadenas.
 * Este método es recursivo. Su caso base es cuando en alguno de los archivos 
 * de lectura ya no hay más elementos en una interación, lo que significa que 
 * todos los datos están en el otro. Al hacer la llamada recursiva, leerR se 
 * convierte en escR, leerL en escL, escL en leerL y escR en leerR.
 * @param leerR Archivo de lectura 1. Archivo de "la derecha". En la primera 
 * iteración este archivo es F1
 * @param leerL Archivo de lectura 2. Archivo de "la izquierda".En la primera
 * iteración este archivo es F2.
 * @param escR Archivo de escritura 1. Archivo de escritura de "la derecha". En la
 * primera iteración este archivo es F3.
 * @param escL Archivo de escritura 2. Archivo de escritura de "la inzquierda". En la
 * primera iteración este archivo es F4.
 * @param iteration Parametro para saber en que llamada recursiva se está. 
 * @throws IOException 
 */
    public void mergeApe(String leerR, String leerL, String escR, String escL, int iteration) throws IOException{        

        File r = new File(leerR);
        File l = new File(leerL); 
        FileReader rR = new FileReader(r);
        FileReader rL = new FileReader(l);
        BufferedReader checkR = new BufferedReader(rR);
        BufferedReader checkL = new BufferedReader(rL);
        String strR = checkR.readLine();
        String strL = checkL.readLine();       
             //No se crearán archivos ahora
            
        /*
        Si strL, strR ,strWL o strWR son nulos, significa que se llegó al final
        de su respectivo archivo.
        
        Si strL , strR, strWL o strWR son "@" Se llego al inicio de una
             iteración

        si strL, strR, strWL o strWR están vacías, se llegó al final de 
        un bloque.
        */     
        int numeroArrobas;
        if(iteration%2==0){
            numeroArrobas = iteration/2;
        }else{
            numeroArrobas = (iteration+1)/2;
        }
        /*
        Los siguentes while colocan al lector en la iteración correcta 
        */
        int countArrobas=1;
        while(countArrobas<numeroArrobas){
            strR = checkR.readLine();
            if(strR.equals("@")){
                countArrobas++;
            }
        }
       countArrobas=1;
        while(countArrobas<numeroArrobas){

            strL = checkL.readLine();
            if(strL.equals("@")){
                countArrobas++;
            }
        }

        //Lee la primera linea de la iteracion
        strR = checkR.readLine();
        strL = checkL.readLine();
        
        if(strR.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerL);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
        if(strL.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerR);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
       
      
        if(!strR.isEmpty()&&!strL.isEmpty()){     
            Dato transDato = new Dato();
            boolean archivo = true;
            //Añade el @indicando el incio de iteración
            transDato.addString(escR,"@\n");
            transDato.addString(escL,"@\n");
            while(strR!=null&&strL!=null){
                /*
                Mientras no se llege al final de del bloque o de la iteración
                */
                while((strR!=null&&!strR.isEmpty())&&(strL!=null&&!strL.isEmpty())){
                    Alumno alumR;
                    Alumno alumL;
                    alumR = transDato.obtenerDato(strR);
                    alumL = transDato.obtenerDato(strL);
                   
                    if(alumL.getApellido().toUpperCase().compareTo(alumR.getApellido().toUpperCase())<=0){
                        if(archivo){

                            transDato.escribirDato(alumL,escL);
                            //Se elimino la repeticion de folderpath, la cadena
                            //ya lo incluye
                        }else{
                            transDato.escribirDato(alumL,escR);
                        }
                        strL=checkL.readLine();
                    }else{
                        if(archivo){
                            transDato.escribirDato(alumR,escL);
                        }else{
                            transDato.escribirDato(alumR,escR);
                        }
                        strR=checkR.readLine();
                    }

                }
                while(strR!=null&&!strR.isEmpty()){
                    Alumno alumR;
                    alumR = transDato.obtenerDato(strR); 
                    if(archivo){
                        transDato.escribirDato(alumR,escL);
                    }else{
                        transDato.escribirDato(alumR,escR);
                    }
                        strR=checkR.readLine();
                }
                while(strL!=null&&!strL.isEmpty()){
                    Alumno alumL;
                    alumL = transDato.obtenerDato(strL); 
                    if(archivo){
                        transDato.escribirDato(alumL,escL);
                    }else{
                        transDato.escribirDato(alumL,escR);
                    }
                    strL=checkL.readLine();
                }
                if(archivo){
                    transDato.addString(escL,"\n");
                }else{
                    transDato.addString(escR,"\n");
                }
                archivo=!archivo;
                strR = checkR.readLine();
                strL = checkL.readLine();
            }
            while(strR!=null&&!strR.isEmpty()){
                Alumno ultimoBloque = transDato.obtenerDato(strR);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                strR=checkR.readLine();
            }
     
            while(strL!=null&&!strL.isEmpty()){
               
                Alumno ultimoBloque = transDato.obtenerDato(strL);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                    strL=checkL.readLine();
            }
            
             if(archivo){
                transDato.addString(escL,"\n");
            }else{
                transDato.addString(escR,"\n");
                }
            rL.close();
            rR.close();
            checkL.close();
            checkR.close();
            iteration++;

            mergeApe(escR,escL,leerR, leerL, iteration);        
        }
        rL.close();
        rR.close();
        checkL.close();
        checkR.close();
    }
     /**
 * Este método une bloques de datos de dos archivos. Los bloques de datos deben
 * estar separados por un salto de linea y las diferentes iteraciones del archivo
 * por un arroba. Los archivos de lectura son leerR y leerL
 * La escritura de los bloques unidos se van intercambiando entre escL y escR.
 * Para hacer las comparaciones necesarias para el merge se utiliza el atributo 
 * de nombre de los objetos Alumno y el método compareTo de las cadenas.
 * Este método es recursivo. Su caso base es cuando en alguno de los archivos 
 * de lectura ya no hay más elementos en una interación, lo que significa que 
 * todos los datos están en el otro. Al hacer la llamada recursiva, leerR se 
 * convierte en escR, leerL en escL, escL en leerL y escR en leerR.
 * @param leerR Archivo de lectura 1. Archivo de "la derecha". En la primera 
 * iteración este archivo es F1
 * @param leerL Archivo de lectura 2. Archivo de "la izquierda".En la primera
 * iteración este archivo es F2.
 * @param escR Archivo de escritura 1. Archivo de escritura de "la derecha". En la
 * primera iteración este archivo es F3.
 * @param escL Archivo de escritura 2. Archivo de escritura de "la inzquierda". En la
 * primera iteración este archivo es F4.
 * @param iteration Parametro para saber en que llamada recursiva se está. 
 * @throws IOException 
 */
    
    public void mergeNom(String leerR, String leerL, String escR, String escL, int iteration) throws IOException{        

        File r = new File(leerR);
        File l = new File(leerL); 
        FileReader rR = new FileReader(r);
        FileReader rL = new FileReader(l);
        BufferedReader checkR = new BufferedReader(rR);
        BufferedReader checkL = new BufferedReader(rL);
        String strR = checkR.readLine();
        String strL = checkL.readLine();       
             //No se crearán archivos ahora
            
        /*
        Si strL, strR ,strWL o strWR son nulos, significa que se llegó al final
        de su respectivo archivo.
        
        Si strL , strR, strWL o strWR son "@" Se llego al inicio de una
             iteración

        si strL, strR, strWL o strWR están vacías, se llegó al final de 
        un bloque.
        */     
        int numeroArrobas;
        if(iteration%2==0){
            numeroArrobas = iteration/2;
        }else{
            numeroArrobas = (iteration+1)/2;
        }
        /*
        Los siguentes while colocan al lector en la iteración correcta 
        */
        int countArrobas=1;
        while(countArrobas<numeroArrobas){
            strR = checkR.readLine();
            if(strR.equals("@")){
                countArrobas++;
            }
        }
       countArrobas=1;
        while(countArrobas<numeroArrobas){

            strL = checkL.readLine();
            if(strL.equals("@")){
                countArrobas++;
            }
        }

        //Lee la primera linea de la iteracion
        strR = checkR.readLine();
        strL = checkL.readLine();
        
        if(strR.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerL);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
        if(strL.isEmpty()){
            System.out.println("Los datos ordenados están en "+leerR);
            System.out.println(" ");
            System.out.println("Los archivos están en: ");
            System.out.println(folderPath);
        }
        
        
        if(!strR.isEmpty()&&!strL.isEmpty()){     
            Dato transDato = new Dato();
            boolean archivo = true;
            //Añade el @indicando el incio de iteración
            transDato.addString(escR,"@\n");
            transDato.addString(escL,"@\n");
            while(strR!=null&&strL!=null){
                /*
                Mientras no se llege al final de del bloque o de la iteración
                */
                while((strR!=null&&!strR.isEmpty())&&(strL!=null&&!strL.isEmpty())){
                    Alumno alumR;
                    Alumno alumL;
                    alumR = transDato.obtenerDato(strR);
                    alumL = transDato.obtenerDato(strL);

                    System.out.print(alumL.getApellido().toUpperCase()+" ");
                    System.out.print(alumR.getApellido().toUpperCase()+" ");
                   
                    if(alumL.getNombre().toUpperCase().compareTo(alumR.getNombre().toUpperCase())<=0){
                        if(archivo){

                            transDato.escribirDato(alumL,escL);
                            //Se elimino la repeticion de folderpath, la cadena
                            //ya lo incluye
                        }else{
                            transDato.escribirDato(alumL,escR);
                        }
                        strL=checkL.readLine();
                    }else{
                        if(archivo){
                            transDato.escribirDato(alumR,escL);
                        }else{
                            transDato.escribirDato(alumR,escR);
                        }
                        strR=checkR.readLine();
                    }

                }
                while(strR!=null&&!strR.isEmpty()){
                    Alumno alumR;
                    alumR = transDato.obtenerDato(strR); 
                    if(archivo){
                        transDato.escribirDato(alumR,escL);
                    }else{
                        transDato.escribirDato(alumR,escR);
                    }
                        strR=checkR.readLine();
                }
                while(strL!=null&&!strL.isEmpty()){
                    Alumno alumL;
                    alumL = transDato.obtenerDato(strL); 
                    if(archivo){
                        transDato.escribirDato(alumL,escL);
                    }else{
                        transDato.escribirDato(alumL,escR);
                    }
                    strL=checkL.readLine();
                }
                if(archivo){
                    transDato.addString(escL,"\n");
                }else{
                    transDato.addString(escR,"\n");
                }
                archivo=!archivo;
                strR = checkR.readLine();
                strL = checkL.readLine();
            }
            while(strR!=null&&!strR.isEmpty()){
                Alumno ultimoBloque = transDato.obtenerDato(strR);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                strR=checkR.readLine();
            }
     
            while(strL!=null&&!strL.isEmpty()){

                Alumno ultimoBloque = transDato.obtenerDato(strL);
                if(archivo){
                    transDato.escribirDato(ultimoBloque,escL);
                }else{
                    transDato.escribirDato(ultimoBloque,escR);
                }
                    strL=checkL.readLine();
            }
            
             if(archivo){
                transDato.addString(escL,"\n");
            }else{
                transDato.addString(escR,"\n");
                }
            rL.close();
            rR.close();
            checkL.close();
            checkR.close();
            iteration++;

            mergeNom(escR,escL,leerR, leerL, iteration);        
        }
        rL.close();
        rR.close();
        checkL.close();
        checkR.close();
    }
}
