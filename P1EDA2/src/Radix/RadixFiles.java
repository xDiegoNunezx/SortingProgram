
package Radix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase en la que se crean los archivos necesarios para le funcionamiento de Radix.
 * @version 14/10/2020/2.0
 * @author Sofía Elizabeth Cabello Díaz
 */
public class RadixFiles {
        private String folderpath;
    
    /**
     * Método constructor, crea la carpeta en la que se generan todos archivos. 
     * @param archivoD Archivo que se está ordenando.
     */
    public RadixFiles(String archivoD){
        folderpath = new File("").getAbsolutePath()+"/radixFiles/"+archivoD;
    }
    
    /**
     * Método getter que regresa la ruta de la carpeta.
     * @return La ruta de la carpeta en la que se está ordenando el archivo.
     */
    public String getPath(){
        return folderpath;
    }

    /**
     * Método que crea un directorio en la ruta especificada. 
     */
    public void directory(){
        File directory = new File(folderpath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    /**
     * Método que crea nuevos archivos de texto, si ya existe lo elimina y crea uno nuevo. 
     * @param nombre Nombre del archivo a crear.
     * @return El archivo creado.
     * @throws IOException 
     */
    public File newFile(String nombre) throws IOException{
        String filePath = folderpath+ "/" + nombre+".txt";
        File file = new File(filePath);
        file.delete();
        file.createNewFile();
        return file;
    }
    
    /**
     * Método que regresa un lector de texto para un archivo creado en el directorio del archivo a ordenar.
     * @param filename Nombre del archivo que se quiere crear.
     * @param archivo Nombre del archivo que se quiere ordenar.
     * @return Un objeto Buffered Reader para el archivo. 
     * @throws IOException 
     */
    public BufferedReader files(String filename,String archivo) throws IOException{
        RadixFiles rF = new RadixFiles(archivo);
        folderpath = rF.getPath();
        rF.directory();
        File file = newFile(filename);
        FileReader reader_file = new FileReader(file);
        BufferedReader read_file = new BufferedReader(reader_file);
        return read_file;
        }
}
