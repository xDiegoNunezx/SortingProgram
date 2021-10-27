
package polifase;
import dato.*;
import java.util.ArrayList;
/**
 *  Clase que almacena diferentes métodos para hacer Insertion Sort con los
 * diferentes criterios de ordenammiento.
 * @author Ricardo López
 */
public class InsertionSort {
    /**
     * Método que aplica Insertion Sort ordenando por número de cuenta.
     * Para hacer las comparaciones necesarias, se utiliza el operador de relación
     * mayor que. 
     * @param alumnos Lista de alumnos generada en el método sortNum que se va 
     * a ordenar.
     */

    public static void inSortNum(ArrayList<Alumno> alumnos) {  
            
        int n = alumnos.size();  
        for (int j = 1; j < n; j++) {  
            Alumno key = alumnos.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( alumnos.get(i).getNoCuenta() > key.getNoCuenta() ) ){  
                alumnos.set(i+1,alumnos.get(i));  
                i--;  
            }  
            alumnos.set(i+1 , key); //array[i+1] = key;  
        }  
    }

    /**
     * Método que aplica Insertion Sort ordenando por apellido.
     * Para hacer las comparaciones necesarias, se utiliza el atributo de
     * apellido de los alumnos y el método compareTo de las cadenas.
     * @param alumnos Lista de alumnos generada en el método sortApe que se va 
     * a ordenar.
     */
    public static void inSortApe(ArrayList<Alumno> alumnos) {  
        int n = alumnos.size();  
        for (int j = 1; j < n; j++) {  
            Alumno key = alumnos.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( alumnos.get(i).getApellido().toUpperCase().compareTo(key.getApellido().toUpperCase())>0 ) ){  
                alumnos.set(i+1,alumnos.get(i));  
                i--;  
            }  
            alumnos.set(i+1 , key); //array[i+1] = key;  
        }  
    }
        /**
     * Método que aplica Insertion Sort ordenando por nombre.
     * Para hacer las comparaciones necesarias, se utiliza el atributo de
     * nombre de los alumnos y el método compareTo de las cadenas.
     * @param alumnos Lista de alumnos generada en el método sortNom que se va 
     * a ordenar.
     */
        public static void inSortNom(ArrayList<Alumno> alumnos) {  
        int n = alumnos.size();  
        for (int j = 1; j < n; j++) {  
            Alumno key = alumnos.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( alumnos.get(i).getNombre().toUpperCase().compareTo(key.getNombre().toUpperCase())>0 ) ){  
                alumnos.set(i+1,alumnos.get(i));  
                i--;  
            }  
            alumnos.set(i+1 , key); //array[i+1] = key;  
        }  
    }


}
