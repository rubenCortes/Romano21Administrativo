package bennucybercafe.com.ve.romano21administrativo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rubén on 25/11/2015.
 */
public class DataBaseManager
{
    public static final String TABLE_NAME = "vendedor";

    public static final String CN_ID_VENDEDOR = "_id";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_APELLIDO = "apellido";
    public static final String CN_EDAD = "edad";
    public static final String CN_DIRECCION = "direccion";

    public static final String CREAR_TABLA_VENDEDOR = "create table " + TABLE_NAME + " ("
            + CN_ID_VENDEDOR + " integer primary key autoincrement,"
            + CN_NOMBRE + " text not null,"
            + CN_APELLIDO + " text not null,"
            + CN_EDAD + " integer,"
            + CN_DIRECCION + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager( Context context )
    {
        helper = new DbHelper( context );
        db = helper.getWritableDatabase();
    }


    private ContentValues valores( Vendedor vendedor  )
    {
        ContentValues contenido = new ContentValues();

        contenido.put(CN_NOMBRE, vendedor.getNombre());
        contenido.put(CN_APELLIDO, vendedor.getApellido());
        contenido.put(CN_EDAD, vendedor.getEdad());
        contenido.put(CN_DIRECCION, vendedor.getDireccion());

        return contenido;
    }


    public void insertar( String nombre, String apellido, int edad, String direccion )
    {
        db.insert(TABLE_NAME, null, valores(new Vendedor(nombre, apellido, edad, direccion)));
    }

    // insertar sobrecargado
    public void insertar( String nombre, String apellido, int edad )
    {
        db.insert(TABLE_NAME, null, valores(new Vendedor( nombre, apellido, edad )));
    }

    // insertar sobrecargado
    public void insertar(String nombre, String apellido)
    {
        db.insert(TABLE_NAME, null, valores(new Vendedor( nombre, apellido )));
    }

    public boolean estaAbierta()
    {
        return db.isOpen();
    }

    public void borrarVendedores()
    {
        String sentencia = "delete from vendedor;";

        db.execSQL(sentencia);
    }

    public Cursor vendedoresCursor()
    {
        return db.rawQuery("select * from vendedor;",null);
    }

    public Cursor vendedorBuscar(String nombre)
    {
        String sentencia = "SELECT * FROM VENDEDOR WHERE NOMBRE LIKE '" + nombre + "%';";

        return db.rawQuery(sentencia, null);
    }


}
