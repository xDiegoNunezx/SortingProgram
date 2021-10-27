package dato;

/**
 * Esta clase representa a un alumno con un nombre, apellido y número de cuenta.
 * @author Equipo 8
 * @version 1.0
 */


public class Alumno {
    private String nombre;
    private String apellido;
    private int noCuenta;

    /**
     * Constructor que recibe una cadena para el nombre, una cadena para el
     * apellido y un número entero para el número de cuanta del alumno.
     * @param nombre Nombre del alumno. 
     * @param apellido Apellido del alumno.
     * @param noCuenta Número de cuenta del alumno.
     */
    public Alumno(String nombre, String apellido, int noCuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.noCuenta = noCuenta;
    }    
    
    /**
     * Getter nombre.
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter nombre.
     * @param nombre Nombre del alumno.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter apellido.
     * @return apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Setter Apellido
     * @param apellido Apellido del alumno.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Getter número de cuenta.
     * @return Número de cuenta.
     */
    public int getNoCuenta() {
        return noCuenta;
    }
    
    /**
     * Setter número de cuenta.
     * @param noCuenta  Número de cuenta del alumno.
     */
    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }        
}
