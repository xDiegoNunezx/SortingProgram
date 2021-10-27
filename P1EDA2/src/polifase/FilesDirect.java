package polifase;

import java.io.File;
import java.io.IOException;

/**
*Esta clase hace el manejo de archivos específico de polifase que no hace la clase datos.
*@author Ricardo López
*/

public class FilesDirect {
    private String folderpath;

    /**
     * Este constructor llena al atributo folderpath, que recuerda la
     * ruta del folder donde se almancenan los archivos.
     */
    public FilesDirect(){
        folderpath = new File("").getAbsolutePath()+"/polifaseFiles";
    }
    
    /**
     * Metodo que devuelve la ruta al forder donde se almacenan los archivos de
     * polifase
     * @return Ruta en forma de cadena al folder donde se almacenan los 
     * archivos de polifase. 
     */
    public String rutaFolder(){
        return folderpath;
    }
/**
 * Método que crea el directorio donde se alamcenarán los archivos.
 * Si el directorio existe, es eliminado y vuelto a crear, por lo que hay que
 * sacar del directorio cualquier archivo que se quiera conservar.
 * @throws IOException 
 */
    public void crearDirectorio() throws IOException{
        File directorio = new File(folderpath);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }else{
            System.out.println("El directorio existe");
            eliminarDirectorio(directorio);
             if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }
    /**
     * Crea archivos en el directorio polifaseFiles.
     * @param nombre Nombre del archivo que se quiere crear dentro de
     * polifaseFiles
     * @throws IOException 
     */
    public void crearArchivo(String nombre) throws IOException{
        String filePath = folderpath+ "/" + nombre;
        File file = new File(filePath);
        // Si el archivo no existe es creado
        if (!file.exists()) {
            file.createNewFile();
        }
    }
    /**
     * Elimina un directorio auque no esté vacío eliminando su contenido 
     * de manera recursiva.
     * @param archivo Nombre del directorio a eliminar.
     * @throws IOException 
     */
    void eliminarDirectorio(File archivo) throws IOException {
        if (archivo.isDirectory()) {
            File[] entries = archivo.listFiles();
                if (entries != null) {
                    for (File entry : entries) {
                        eliminarDirectorio(entry);
                    }
            }
        }
        
        if (!archivo.delete()) {
          throw new IOException("Error al eliminar " + archivo);
        }
    }
}
