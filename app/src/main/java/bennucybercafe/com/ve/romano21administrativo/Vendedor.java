package bennucybercafe.com.ve.romano21administrativo;

/**
 * Created by Rubén on 28/11/2015.
 */
public class Vendedor
{
    private int id_Vendedor;
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;

    public Vendedor(String nombre, String apellido, int edad, String direccion) {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
        this.edad = edad;
        this.direccion = direccion.toUpperCase();
    }

    public Vendedor(String nombre, String apellido, int edad) {
        this(nombre,apellido,edad,"");
    }

    public Vendedor(String nombre, String apellido) {
        this(nombre, apellido, 0, "");
    }

    public int getId_Vendedor() {
        return id_Vendedor;
    }

    public void setId_Vendedor(int id_Vendedor) {
        this.id_Vendedor = id_Vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.toUpperCase();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.toUpperCase();
    }
}
