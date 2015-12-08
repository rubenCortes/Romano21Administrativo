package bennucybercafe.com.ve.romano21administrativo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {

    private ListView lista;
    private DataBaseManager datos;
    private Cursor vendedores;
    private Cursor vendedorBusqueda;
    private TextView tvResultado;
    private VendedorCursorAdapter vendedorCursorAdapter;
    private EditText etCampoBusquedaNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        datos = new DataBaseManager(this);

        lista = (ListView) findViewById(R.id.lvVendedores);

        tvResultado = (TextView) findViewById(R.id.tvResultado);

        etCampoBusquedaNombre = (EditText) findViewById(R.id.etNombreBusqueda);

        vendedores = datos.vendedoresCursor();

        tvResultado.setText(String.valueOf(vendedores.getCount()));

        Toast.makeText(this, "Número de registros: " + vendedores.getCount(),Toast.LENGTH_LONG).show();

        vendedorCursorAdapter = new VendedorCursorAdapter(this, vendedores);

        lista.setAdapter(vendedorCursorAdapter);

        lista.setOnItemClickListener(this);

        etCampoBusquedaNombre.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void cargarDatos()
    {
        Long tiempoInicial, tiempoFinal;

        Toast.makeText(this, "Inicio del proceso", Toast.LENGTH_LONG).show();

        tiempoInicial= System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++ ) {
            datos.insertar("Nombre "+i, "Apellido "+i , 22+i, "Dirección " + i);
        }
        tiempoFinal = System.currentTimeMillis();

        Double duracion = (tiempoFinal.doubleValue()-tiempoInicial.doubleValue())/1000;

        tvResultado.setText(duracion.toString());

        Toast.makeText(this,"Duración del proceso: " + duracion,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mnuAgregar:
                Intent intent = new Intent(this,DatosVendedor.class);
                startActivity(intent);
                return true;
            case R.id.mnuBuscar:
                vendedorBusqueda = datos.vendedorBuscar(etCampoBusquedaNombre.getText().toString().toUpperCase());
                vendedorCursorAdapter.changeCursor(vendedorBusqueda);
                return true;
            case R.id.mnuCarga:
                cargarDatos();
                vendedores = datos.vendedoresCursor();
                vendedorCursorAdapter.changeCursor(vendedores);
                return true;
            case R.id.mnuLimpiarTablaVendedor:
                datos.borrarVendedores();
                vendedores = datos.vendedoresCursor();
                vendedorCursorAdapter.changeCursor(vendedores);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
       /* String v =  adapterView.getItemAtPosition(i).getClass().getName();
        Toast.makeText(getApplicationContext(),v,Toast.LENGTH_LONG).show();
        String data =  adapterView.getAdapter().getItem(i).getClass().getName();
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
 */
        Cursor vendedorCursor = (Cursor) adapterView.getItemAtPosition(i);

   //     TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
   //     VendedorCursorAdapter vca = (VendedorCursorAdapter) adapterView.getAdapter();
   //     Cursor c = vca.getCursor()
   // ;
        //Cursor vendedorCursor = datos.vendedorBuscar(String.valueOf(tvNombre.getText()));
        //vendedorCursor.moveToFirst();
        String nombre = vendedorCursor.getString(vendedorCursor.getColumnIndexOrThrow("nombre"));
        String apellido = vendedorCursor.getString(vendedorCursor.getColumnIndexOrThrow("apellido"));
        int edad = vendedorCursor.getInt(vendedorCursor.getColumnIndexOrThrow("edad"));

        Toast.makeText(getApplicationContext(), "Item: " + nombre + " " + apellido + " " + String.valueOf(edad), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        vendedorBusqueda = datos.vendedorBuscar(etCampoBusquedaNombre.getText().toString().toUpperCase());
        vendedorCursorAdapter.changeCursor(vendedorBusqueda);
       // Toast.makeText(this,"Texto " + etCampoBusquedaNombre.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
