package polifase;
import dato.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Clase que contiene diferentes versiones del método sort para odenar por los
 * diferentes criterios. En esta clase se encuentran los métodos principales 
 * para el ordenamineto polifase
 * @author Ricardo López
 */
public class Polifase {
    String folderpath;    
    /**
     * Método que se encaga leer al archivo original, separar los datos 
     * en bloques pequeños de 10 elementos, ordenar los bloques y escribirlos 
     * en los archivos F1 y F2.Para posterior mente unir a los archivos 
     * se llama al método mergeNum de la clase Merge.
     * Para el ordenamineto de los bloques se llama al método inSortNum de la 
     * clase InsertionSort.
     * @param nombreArch Nombre del archivo a ordenar ingresado por el usuario
     * en el main.
     * @throws IOException 
     */
    public void sortNum(String nombreArch) throws IOException{
        FilesDirect admiFiles = new FilesDirect();
        folderpath = admiFiles.rutaFolder();
        File lista = new File(nombreArch);
        FileReader lectura = new FileReader(lista);
        BufferedReader contar = new BufferedReader(lectura);
        String cadena = contar.readLine();
        int contador = 0;
        boolean bandera = true;
        int linea=0;
        boolean archivo = true;
        Merge merge = new Merge();
        Dato getDatos = new Dato();
        admiFiles.crearDirectorio();
        admiFiles.crearArchivo("F1.txt");
        admiFiles.crearArchivo("F2.txt");
        admiFiles.crearArchivo("F3.txt");
        admiFiles.crearArchivo("F4.txt");
        
        getDatos.addString(folderpath+"/F1.txt","@\n");
        getDatos.addString(folderpath+"/F2.txt","@\n");
        do{
        
            while(cadena!=null&&!cadena.isEmpty()&&contador<10) { 
                cadena = contar.readLine(); 
                contador++;
            }
            ArrayList <Alumno> bloque = getDatos.leerDato(linea,linea+contador,nombreArch);
            InsertionSort.inSortNum(bloque);
            if(archivo){
                getDatos.escribirDatos2(bloque,folderpath+"/F1.txt");
            }else{
                getDatos.escribirDatos2(bloque,folderpath+"/F2.txt");
            }
            archivo=!archivo; //En la siguiente iteración escribira en el otro archivo 
            linea += contador;
            contador=0;
            if(cadena==null||cadena.isEmpty()){
                bandera=!bandera; //l archivo acabó y se sale del ciclo
            }

        }while(bandera);
        
        merge.mergeNum(folderpath+"/F1.txt",folderpath+"/F2.txt",folderpath+"/F3.txt",folderpath+"/F4.txt",1);
        
        contar.close();
        lectura.close();
    }
        /**
     * Método que se encaga leer al archivo original, separar los datos 
     * en bloques pequeños de 10 elementos, ordenar los bloques y escribirlos 
     * en los archivos F1 y F2. Para posterior mente unir a los archivos 
     * se llama al método mergeApe de la clase Merge.
     * Para el ordenamineto de los bloques se llama al método inSortApe de la 
     * clase InsertionSort.
     * @param nombreArch Nombre del archivo a ordenar ingresado por el usuario
     * en el main.
     * @throws IOException 
     */
    public void sortApe(String nombreArch) throws IOException{
        FilesDirect admiFiles = new FilesDirect();
        folderpath = admiFiles.rutaFolder();
        File lista = new File(nombreArch);
        FileReader lectura = new FileReader(lista);
        BufferedReader contar = new BufferedReader(lectura);
        String cadena = contar.readLine();
        int contador = 0;
        boolean bandera = true;
        int linea=0;
        boolean archivo = true;
        Merge merge = new Merge();
        Dato getDatos = new Dato();
        admiFiles.crearDirectorio();
        admiFiles.crearArchivo("F1.txt");
        admiFiles.crearArchivo("F2.txt");
        admiFiles.crearArchivo("F3.txt");
        admiFiles.crearArchivo("F4.txt");
        
        getDatos.addString(folderpath+"/F1.txt","@\n");
        getDatos.addString(folderpath+"/F2.txt","@\n");
        do{
        
            while(cadena!=null&&!cadena.isEmpty()&&contador<10) { 
                cadena = contar.readLine(); 
                contador++;
            }
            ArrayList <Alumno> bloque = getDatos.leerDato(linea,linea+contador,nombreArch);
            InsertionSort.inSortApe(bloque);
            if(archivo){
                getDatos.escribirDatos2(bloque,folderpath+"/F1.txt");
            }else{
                getDatos.escribirDatos2(bloque,folderpath+"/F2.txt");
            }
            archivo=!archivo; //En la siguiente iteración escribira en el otro archivo 
            linea += contador;
            contador=0;
            if(cadena==null||cadena.isEmpty()){
                bandera=!bandera; //l archivo acabó y se sale del ciclo
            }

        }while(bandera);
        
        merge.mergeApe(folderpath+"/F1.txt",folderpath+"/F2.txt",folderpath+"/F3.txt",folderpath+"/F4.txt",1);
        
        contar.close();
        lectura.close();
    }
       /**
     * Método que se encaga leer al archivo original, separar los datos 
     * en bloques pequeños de 10 elementos, ordenar los bloques y escribirlos 
     * en los archivos F1 y F2. Para posterior mente unir a los archivos 
     * se llama al método mergeNom de la clase Merge.
     * Para el ordenamineto de los bloques se llama al método inSortNom de la 
     * clase InsertionSort.
     * @param nombreArch Nombre del archivo a ordenar ingresado por el usuario
     * en el main.
     * @throws IOException 
     */
    public void sortNom(String nombreArch) throws IOException{
        FilesDirect admiFiles = new FilesDirect();
        folderpath = admiFiles.rutaFolder();
        File lista = new File(nombreArch);
        FileReader lectura = new FileReader(lista);
        BufferedReader contar = new BufferedReader(lectura);
        String cadena = contar.readLine();
        int contador = 0;
        boolean bandera = true;
        int linea=0;
        boolean archivo = true;
        Merge merge = new Merge();
        Dato getDatos = new Dato();
        admiFiles.crearDirectorio();
        admiFiles.crearArchivo("F1.txt");
        admiFiles.crearArchivo("F2.txt");
        admiFiles.crearArchivo("F3.txt");
        admiFiles.crearArchivo("F4.txt");
        
        getDatos.addString(folderpath+"/F1.txt","@\n");
        getDatos.addString(folderpath+"/F2.txt","@\n");
        do{
        
            while(cadena!=null&&!cadena.isEmpty()&&contador<10) { 
                cadena = contar.readLine(); 
                contador++;
            }
            ArrayList <Alumno> bloque = getDatos.leerDato(linea,linea+contador,nombreArch);
            InsertionSort.inSortNom(bloque);
            if(archivo){
                getDatos.escribirDatos2(bloque,folderpath+"/F1.txt");
            }else{
                getDatos.escribirDatos2(bloque,folderpath+"/F2.txt");
            }
            archivo=!archivo; //En la siguiente iteración escribira en el otro archivo 
            linea += contador;
            contador=0;
            if(cadena==null||cadena.isEmpty()){
                bandera=!bandera; //l archivo acabó y se sale del ciclo
            }

        }while(bandera);
        
        merge.mergeNom(folderpath+"/F1.txt",folderpath+"/F2.txt",folderpath+"/F3.txt",folderpath+"/F4.txt",1);
        
        contar.close();
        lectura.close();
    }


}
