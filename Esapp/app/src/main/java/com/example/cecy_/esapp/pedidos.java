package com.example.cecy_.esapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class pedidos extends AppCompatActivity {
    private TextView proveedor, tvFecha;
    private Button fechaPedido, agregarProducto, guardarPedido;
    private Spinner productos;
    private ListView detalles;
    private EditText cant;
    private int ID_Producto;
    private String nombreProducto, Fecha;
    private double cantidad, precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        proveedor = (TextView) findViewById(R.id.tvProveedor);
        fechaPedido = (Button) findViewById(R.id.btnFecha);
        agregarProducto = (Button) findViewById(R.id.btnAgregar);
        guardarPedido = (Button) findViewById(R.id.btnGuardar);
        productos = (Spinner) findViewById(R.id.spProductos);
        detalles = (ListView) findViewById(R.id.lvDetalles);
        cant = (EditText) findViewById(R.id.etCantidad);
        tvFecha = (TextView) findViewById(R.id.tvFecha);

        proveedor.setText(categorias.nombreProveedor);

        llenarSpinnerProductos();


        final List<String> values= new ArrayList<>();
        values.add("Cantidad        Producto");
        detalles.setAdapter(new ArrayAdapter<>(pedidos.this, android.R.layout.simple_spinner_item , values));

        productos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ID_Producto = ((Cursor) productos.getSelectedItem()).getInt(3);
                nombreProducto = ((Cursor) productos.getSelectedItem()).getString(1);
                precio = Double.parseDouble(((Cursor) productos.getSelectedItem()).getString(2));

                Toast.makeText(pedidos.this, "" + nombreProducto,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        agregarProducto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cant.getText().toString() != "") {
                    cantidad = Double.parseDouble(cant.getText().toString());
                    values.add("     " + cantidad + "          " + ID_Producto + " " + nombreProducto);
                    detalles.setAdapter(new ArrayAdapter<>(pedidos.this, android.R.layout.simple_spinner_item, values));
                    cant.setText("");
                }
            }
        });

        guardarPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();

                String fechaPedido = dateFormat.format(date);

                //Obtener BD
                SqliteHelper esapp = new SqliteHelper(getApplicationContext());
                SQLiteDatabase db = esapp.getWritableDatabase();

                //Crear content values
                ContentValues valuesPedido = new ContentValues();

                //Agregar valores
                valuesPedido.put("Fecha_Pedido", fechaPedido);
                valuesPedido.put("Fecha_Entrega", Fecha);
                valuesPedido.put("Proveedor_FK", categorias.ID_Proveedor);
                valuesPedido.put("Cliente_FK", login.usuarioID);

                //Insertar registro
                long idPedido = db.insert("pedidos", null, valuesPedido);

                //Crear content values
                ContentValues valuesPedidoDetalle = new ContentValues();
                for(int x = 0; x < values.size(); x++){
                    String[] valores = values.get(x).split(" ");
                    //Agregar valores
                    valuesPedidoDetalle.put("Cantidad", valores[1]);
                    valuesPedidoDetalle.put("Pedido_FK", idPedido);
                    valuesPedidoDetalle.put("Catalogo_Detalle_FK", valores[2]);

                    //Insertar registro
                    db.insert("pedidos_detalles", null, valuesPedidoDetalle);
                }

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(categorias.Telefono, null, "¡Hola!\n Tienes un nuevo pedido de: " + login.user, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Enviado!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS fallido, intente después!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


                Toast.makeText(pedidos.this, "Pedido guardado correctamente",
                        Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }
        });

        fechaPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog dialog = new Dialog(pedidos.this);
                dialog.setContentView(R.layout.dialogcalendariopedido);
                dialog.setTitle("Fecha de pedido");
                //Elementos del dialog
                CalendarView fechaP = (CalendarView) dialog.findViewById(R.id.calendarView);

                fechaP.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        String mes, dia;
                        if(month < 9){
                            mes = "0" + (month +1);
                        }else{
                            mes = String.valueOf((month +1));
                        }
                        if(dayOfMonth < 9){
                            dia = "0" + (dayOfMonth);
                        }else{
                            dia = String.valueOf(dayOfMonth);
                        }
                        Fecha = year + "-" + mes + "-" + dia;
                        tvFecha.setText(dia + "-" + mes + "-" + year);
                        dialog.dismiss();
                        //Toast.makeText(pedidos.this, "" + Fecha, Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });

    }

    protected void llenarSpinnerProductos(){
        SqliteHelper esapp = new SqliteHelper(getApplicationContext());
        final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT catalogo.Producto_FK as _id, (Nombre || ' ' || Descripcion) AS Nombre, Precio, ID_Catalogo FROM catalogo LEFT JOIN productos ON Producto_FK = ID_Producto WHERE Proveedor_FK = " + categorias.ID_Proveedor,null);

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        productos.setAdapter(null);
        productos.setAdapter(adapter);

    }


}
