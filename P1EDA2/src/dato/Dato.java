package dato;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta clase se encarga del manejo de los archivos de texto. Contiene métodos
 * para escribir y para leer datos específicos.
 * @author Equipo 8
 * @version 1.0
 */
public class Dato {
    /**
     * Este método lee varias líneas de un archivo de texto y obtiene un arreglo
     * de objetos de tipo alumno.
     * @param inicio Número de línea desde donde se empezará a leer.
     * @param fin Número de la última línea a leer.
     * @param archivoE Nombre del archivo de donde se obtendrán los datos.
     * @return ArraList de tipo Alumno con los alumnos leídos.
     * @throws FileNotFoundException Excepción.
     * @throws IOException Excepción.
     */
    public ArrayList<Alumno> leerDato(int inicio, int fin, String archivoE) throws FileNotFoundException, IOException{
        ArrayList<Alumno> datos = new ArrayList<>();
        String cadena;
        
        File archivo = new File(archivoE);        
        BufferedReader lectura;
        FileReader lector = new FileReader(archivo);
        lectura = new BufferedReader(lector);
        for(int i=0; i<inicio; i++){
            lectura.readLine();
        }   
        while(inicio<fin){            
            cadena = lectura.readLine();                
            Alumno alumno = obtenerDato(cadena);
            datos.add(alumno);
            inicio++;
        }        
        lectura.close();
        return datos;
    }
    
    /**
     * Este método escribe una lista de objetos de tipo alumno en un archivo de texto.
     * @param alumnos ArrayList de alumnos a escribir.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @throws IOException Excepción.
     */
    public void escribirDatos(ArrayList<Alumno> alumnos, String archivoD) throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo);
        for(int i=0; i<alumnos.size(); i++){
            escribir.write(alumnos.get(i).getNombre().concat(", ")
                    .concat(alumnos.get(i).getApellido().concat(", ")
                            .concat(String.valueOf(alumnos.get(i).getNoCuenta())).concat("\n")));                       
        }
        escribir.close();
    }
    
    /**
     * Este método escribe una lista de objetos de tipo alumno en un ardchivo de texto agrgando
     * un salto de línea al final.
     * @param alumnos ArrayList de alumnos a escribir.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @throws IOException Excepción.
     */
    public void escribirDatos2(ArrayList<Alumno> alumnos, String archivoD) throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo,true);
        for(int i=0; i<alumnos.size(); i++){
            escribir.write(alumnos.get(i).getNombre().concat(", ")
                    .concat(alumnos.get(i).getApellido().concat(", ")
                            .concat(String.valueOf(alumnos.get(i).getNoCuenta())).concat("\n")));                       
        }
        escribir.write("\n");  
        escribir.close();               
    }

    /**
     * Este método recibe una cadena con formato "Nombre, Apellido, NoCuenta" y la 
     * convierte en un objeto de tipo alumno.
     * @param cadena Cadena a transformar.
     * @return Objeto de tipo alumno.
     */
    public Alumno obtenerDato(String cadena){
        String datos[] = cadena.split(", ");
        Alumno alumno = new Alumno(datos[0], datos[1], Integer.valueOf(datos[2]));
        return alumno;
    }
    
    /**
     * Este método recibe una cadena y la escribe en el archivo especificado.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @param string Cadena a escribir.
     * @throws IOException Excepción.
     */
    public void addString(String archivoD,String string)throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo,true);
        escribir.write(string);            
        escribir.close();                
    }
    
    /**
     * Este método escribe el símbolo "#" en un archivo especificado.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @throws IOException Excepción.
     */
    public void addHash(String archivoD)throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo,true);
        escribir.write("#\n");
        escribir.close();
    }

    /**
     * Este método recibe un objeto de tipo Alumno y lo escribe en un archivo de texto.
     * @param alumno Objeto de tipo alumno a escribir.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @throws IOException Excepción.
     */
    public void escribirDato(Alumno alumno, String archivoD) throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo,true);
        escribir.write(alumno.getNombre().concat(", ")
                    .concat(alumno.getApellido().concat(", ")
                            .concat(String.valueOf(alumno.getNoCuenta())).concat("\n")));
        escribir.close();
    }
    
    /**
     * Este método recibe un objeto de tipo Alumno y lo escribe en un archivo de texto 
     * agregando un salto de línea al final.
     * @param alumno Objeto de tipo alumno a escribir.
     * @param archivoD Nombre del archivo donde se va a escribir.
     * @throws IOException Excepción.
     */
    public void escribirDato2(Alumno alumno, String archivoD) throws IOException{
        File archivo = new File(archivoD);     
        FileWriter escribir = new FileWriter(archivo,true);
            escribir.write(alumno.getNombre().concat(", ")
                        .concat(alumno.getApellido().concat(", ")
                                .concat(String.valueOf(alumno.getNoCuenta())).concat("\n")));    
        escribir.write("\n");        
        escribir.close();
    }
}
