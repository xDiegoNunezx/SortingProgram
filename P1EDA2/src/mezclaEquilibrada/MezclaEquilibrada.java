package mezclaEquilibrada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase es la encargada de crear el directorio y los archivos auxiliares
 * para el ordenamiento. Además, se encarga de ejecutar los métodos necesarios hasta que
 * se haya podido cumplir con el objetivo de ordenar.
 * @author Diego Ignacio Nuñez Hernandez
 * @version 1.0
 */
public abstract class MezclaEquilibrada {      
    /**
     * Este método es el principal de la clase. Aquí se instancian todos los 
     * archivos y se mandan a llamar a los métodos.
     * @param archivo Nombre del archivo a ordenar.
     * @throws IOException Excepción.
     */
    public void mezcla(String archivo) throws IOException{
        boolean isSorted;
        int iteraciones=0;
        String carpetaPath;
        
        File carpeta = new File("MezclaEquilibradaFiles");        
        carpeta.mkdir();  
        carpetaPath = carpeta.getAbsolutePath();
        
        File f = new File(archivo);
        FileReader lector = new FileReader(f);
        BufferedReader reader = new BufferedReader(lector);
        
        File f0 = new File(carpetaPath+"/f0.txt");
        f0.delete();
        f0.createNewFile();
        FileReader fr_f0 = new FileReader(f0);
        BufferedReader lectura_f0 = new BufferedReader(fr_f0);
        
        File f1 = new File(carpetaPath+"/f1.txt");
        f1.delete();
        f1.createNewFile();
        FileReader fr_f1 = new FileReader(f1);
        BufferedReader lectura_f1 = new BufferedReader(fr_f1);

        File f2 = new File(carpetaPath+"/f2.txt");
        f2.delete();
        f2.createNewFile();
        FileReader fr_f2 = new FileReader(f2);
        BufferedReader lectura_f2 = new BufferedReader(fr_f2);
        
        isSorted = mezclaE(reader, carpetaPath);    
        do{
            mezclaD(isSorted,lectura_f1,lectura_f2, carpetaPath);
            isSorted = mezclaE(lectura_f0, carpetaPath);
            iteraciones++;
        } while(isSorted==false);
        
        lectura_f0.close();
        lectura_f1.close();
        lectura_f2.close();
        reader.close();        
                
        System.out.println("Archivo correctamente ordenado");
        System.out.println("Iteraciones: "+iteraciones);
        System.out.println("El archivo ordenado de encuentra en: "+f0.getAbsolutePath());
        System.out.println("Los archivos auxiliares utilizados para el ordenamiento se encuentran en: ");
        System.out.println(f1.getAbsolutePath());
        System.out.println(f2.getAbsolutePath());
    }
    
    /**
     * Este método se encarga de la primera parte del algoritmo de Mezcla Equilibrada, 
     * el cual consiste en realizar particiones tomando secuencias ordenadas de
     * máxima longitud.
     * @param reader Buffer del archivo a particionar.
     * @param carpetaPath String con la direccion de la carpeta donde se encuentran
     * los archivos necesarios para ordenar.
     * @return Booleano que indica si el archivo está ordenado.
     * @throws IOException Excepción.
     */
    public abstract boolean mezclaE(BufferedReader reader, String carpetaPath) throws IOException;
    
    /**
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
    public abstract void mezclaD(boolean isSorted, BufferedReader lectura_f1, BufferedReader lectura_f2, String carpetaPath) throws FileNotFoundException, IOException;    
}
