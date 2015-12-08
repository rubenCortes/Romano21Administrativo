package bennucybercafe.com.ve.romano21administrativo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Rubén on 3/12/2015.
 */
public class VendedorCursorAdapter extends CursorAdapter
{
    public VendedorCursorAdapter(Context context, Cursor c)

    {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.vista_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView tvApellido = (TextView) view.findViewById(R.id.tvApellido);
        // Extrae propiedades del cursor
        String nombre = cursor.getString( cursor.getColumnIndexOrThrow("nombre") );
        String apellido = cursor.getString( cursor.getColumnIndexOrThrow("apellido") );

        // Llena los campos con el valor extraido
        tvNombre.setText(nombre);
        tvApellido.setText(apellido);
    }
}
