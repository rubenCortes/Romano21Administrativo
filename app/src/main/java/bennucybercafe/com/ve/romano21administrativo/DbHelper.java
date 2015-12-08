package bennucybercafe.com.ve.romano21administrativo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rubén on 25/11/2015.
 */
public class DbHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "tienda.sqlite";
    private static final int DB_SCHEMA_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREAR_TABLA_VENDEDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
