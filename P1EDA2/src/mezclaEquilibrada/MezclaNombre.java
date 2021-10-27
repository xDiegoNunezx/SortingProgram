package mezclaEquilibrada;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import dato.*;

/**
 * Esta clase se encarga de ordenar las claves del nombre de 
 * un alumno en un archivo de texto.
 * @author Diego Ignacio Nunez Hernandez
 * @version 1.0 
 */
public class MezclaNombre extends MezclaEquilibrada{
    /**
     * Sobreescritura de mezclaE para ordenar claves de cadenas de texto de los nombres.
     * Este método se encarga de la primera parte del algoritmo de Mezcla Equilibrada, 
     * el cual consiste en realizar particiones tomando secuencias ordenadas de
     * máxima longitud.
     * @param reader Buffer del archivo a particionar.
     * @param carpetaPath String con la direccion de la carpeta donde se encuentran
     * los archivos necesarios para ordenar.
     * @return Booleano que indica si el archivo está ordenado.
     * @throws IOException Excepción.
     */
    @Override
    public boolean mezclaE(BufferedReader reader, String carpetaPath) throws IOException {
        boolean b = true;  
        boolean isSorted = true;
        String cadena;        
        
        Alumno alumnoI;
        Alumno alumnoD;
        
        Dato dato = new Dato();                           
                                        
        cadena=reader.readLine();
        if(cadena==null)         
            return isSorted;       
        if(cadena.equals("#"))
            cadena = reader.readLine();
        if(cadena.isEmpty())
            return isSorted;
        
        do{           
            alumnoI = dato.obtenerDato(cadena);
            cadena = reader.readLine();            
            if(cadena!=null&&!(cadena.equals("#"))){
                alumnoD = dato.obtenerDato(cadena);
            } else {
                if(b==true){                                                                                                       
                    dato.escribirDato2(alumnoI, carpetaPath+"/f1.txt");
                    b=false;
                } else{
                    dato.escribirDato2(alumnoI, carpetaPath+"/f2.txt");                                                                    
                    b=true;
                }
                break;
            }
            
            if(alumnoI.getNombre().compareTo(alumnoD.getNombre())<=0){
                if(b==true){
                    dato.escribirDato(alumnoI, carpetaPath+"/f1.txt");
                } else {
                    dato.escribirDato(alumnoI, carpetaPath+"/f2.txt"); 
                    isSorted = false;
                }                
                
            } else {                
                if(b==true){                                                                                                       
                    dato.escribirDato2(alumnoI, carpetaPath+"/f1.txt");
                    b=false;
                } else{
                    dato.escribirDato2(alumnoI, carpetaPath+"/f2.txt");
                    isSorted = false;
                    b=true;
                }
            }    
        }while(cadena!=null);                           
        
        dato.addHash(carpetaPath+"/f1.txt");
        dato.addHash(carpetaPath+"/f2.txt"); 
                         
        return isSorted;
    }

     /**
     * Sobreescritura de mezclaD para ordenar claves de cadenas de texto de los nombres.
     * Este método se encarga de mezclar las particiones contenidas en dos archivos
     * auxiliares para producir secuencias ordenadas escritas en otro archivo.
     * @param isSorted Booleano que indica si el archivo está ordenado.
     * @param lectura_f1 Buffer del primer archivo auxiliar.
     * @param lectura_f2 Buffer del segundo archivo auxiliar.
     * @param carpetaPath String con la direccion de la carpeta donde se encuentran
     * los archivos necesarios para ordenar.
     * @throws FileNotFoundException Excepción.
     * @throws IOException Excepción.
     */
    @Override
    public void mezclaD(boolean isSorted,BufferedReader lectura_f1, BufferedReader lectura_f2, String carpetaPath) throws FileNotFoundException, IOException {
        if(isSorted==true) return;
        String cadenaf1;
        String cadenaf2;
              
        Alumno alumnof1;
        Alumno alumnof2;
        
        Dato dato = new Dato();  
        
        cadenaf1=lectura_f1.readLine();   
        cadenaf2=lectura_f2.readLine(); 
        if(cadenaf1==null||cadenaf2==null){
            return;
        }
        
        if(cadenaf2.equals("#")){
            while(!(cadenaf1.isEmpty())){
                alumnof1 = dato.obtenerDato(cadenaf1);
                dato.escribirDato(alumnof1, carpetaPath+"/f0.txt");
                cadenaf1=lectura_f1.readLine();
            }
           return; 
        }
        
        alumnof1 = dato.obtenerDato(cadenaf1);   
        alumnof2 = dato.obtenerDato(cadenaf2);                 
        
        do{
           if(!(cadenaf1.isEmpty())&&!(cadenaf2.isEmpty())){
                if(alumnof1.getNombre().compareTo(alumnof2.getNombre())<=0){
                    dato.escribirDato(alumnof1, carpetaPath+"/f0.txt");
                    cadenaf1=lectura_f1.readLine();   
                    if(!(cadenaf1.isEmpty())&&!(cadenaf1.equals("#")))
                        alumnof1 = dato.obtenerDato(cadenaf1);                                  
                }else{
                    dato.escribirDato(alumnof2,carpetaPath+"/f0.txt");
                    cadenaf2=lectura_f2.readLine();   
                    if(!(cadenaf2.isEmpty())&&!(cadenaf2.equals("#")))
                        alumnof2 = dato.obtenerDato(cadenaf2);                                 
                }
            } else{  //Lo deja al inicio del nuevo bloque              
                while(cadenaf1.isEmpty()&&!(cadenaf1.equals("#"))){                        
                   cadenaf1=lectura_f1.readLine();   
                   if(!(cadenaf1.isEmpty())&&!(cadenaf1.equals("#")))
                        alumnof1 = dato.obtenerDato(cadenaf1);
                }                     
                while(cadenaf2.isEmpty()&&!(cadenaf2.equals("#"))){
                   cadenaf2=lectura_f2.readLine();   
                   if(!(cadenaf2.isEmpty())&&!(cadenaf2.equals("#")))
                        alumnof2 = dato.obtenerDato(cadenaf2);
                }
                if(cadenaf1.equals("#")&&cadenaf2.equals("#")) break;
            }
            
            //Escribir los elementos sobrantes
            if(cadenaf2.equals("#")||cadenaf2.isEmpty()){                              
                do{
                    dato.escribirDato(alumnof1, carpetaPath+"/f0.txt");
                    cadenaf1=lectura_f1.readLine();
                    if(cadenaf1!=null&&!(cadenaf1.isEmpty())&&!(cadenaf1.equals("#"))){
                        alumnof1 = dato.obtenerDato(cadenaf1);
                    }  
                } while(cadenaf1!=null&&!(cadenaf1.isEmpty())&&!(cadenaf1.equals("#")));                                    
            } else {
                if(cadenaf1.equals("#")||cadenaf1.isEmpty()){
                    do{
                       dato.escribirDato(alumnof2, carpetaPath+"/f0.txt");
                       cadenaf2=lectura_f2.readLine();
                       if(cadenaf2!=null&&!(cadenaf2.isEmpty())&&!(cadenaf2.equals("#"))){
                         alumnof2 = dato.obtenerDato(cadenaf2);
                        }  
                    } while(cadenaf2!=null&&!(cadenaf2.isEmpty())&&!(cadenaf2.equals("#"))); 
                }
            } 
        }while(cadenaf1!=null&&cadenaf2!=null&&!(cadenaf1.equals("#"))&&!(cadenaf2.equals("#")));   
        
        if(cadenaf2.isEmpty()) cadenaf2=lectura_f2.readLine();
        if(cadenaf1.isEmpty()) cadenaf1=lectura_f1.readLine();
        
        dato.addHash(carpetaPath+"/f0.txt");                
    }   
}