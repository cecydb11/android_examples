package com.example.cecy_.esapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class listaPedidos extends AppCompatActivity {
    private Spinner spProveedores;
    private ListView lvPedidos;
    private int ID_Proveedor, ID_Pedido;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        spProveedores = (Spinner) findViewById(R.id.spProveedorPedido);
        lvPedidos = (ListView) findViewById(R.id.lvPedidos);

        llenarSpinnerResidencias();

        spProveedores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ID_Proveedor = ((Cursor) spProveedores.getSelectedItem()).getInt(0);

                SqliteHelper esapp = new SqliteHelper(getApplicationContext());
                final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT ID_Pedido AS _id, ('Pedido #' || ID_Pedido || ' - ' || Fecha_Pedido) as Pedido, Fecha_Pedido, Fecha_Entrega, proveedores.Nombre FROM pedidos LEFT JOIN proveedores ON Proveedor_FK = ID_Proveedor WHERE Proveedor_FK = " + ID_Proveedor,null);

                adapter = new SimpleCursorAdapter(listaPedidos.this,android.R.layout.simple_list_item_1,c,new String[]{"Pedido"},new int[] {android.R.id.text1});

                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                lvPedidos.setAdapter(null);
                lvPedidos.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lvPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Cursor item = (Cursor) adapter.getItem(position);
                ID_Pedido = item.getInt(0);

                //Toast.makeText(categorias.this, "" + ID_Proveedor, Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(listaPedidos.this);
                dialog.setContentView(R.layout.detallespedido);
                dialog.setTitle("Detalles de pedido");

                //Elementos del dialog
                ListView detallesP = (ListView) dialog.findViewById(R.id.lvDetallesPedido);

                SqliteHelper esapp = new SqliteHelper(getApplicationContext());
                final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT ID_Pedido_Detalle AS _id, (Cantidad || ' ' || Nombre || ' ' || Descripcion) AS Nombre, Precio FROM pedidos_detalles LEFT JOIN catalogo ON Catalogo_Detalle_FK = ID_Catalogo LEFT JOIN productos ON Producto_FK = ID_Producto WHERE Pedido_FK = " + ID_Pedido,null);
                /*Toast.makeText(listaPedidos.this, "SELECT ID_Pedido_Detalle AS _id, (Cantidad || '' || Nombre || ' ' || Descripcion) AS Nombre, Precio FROM pedidos_detalles LEFT JOIN catalogo ON Catalogo_Detalle_FK = ID_Catalogo LEFT JOIN productos ON Producto_FK = ID_Producto WHERE Pedido_FK = " + ID_Pedido,
                        Toast.LENGTH_LONG).show();*/
                adapter = new SimpleCursorAdapter(listaPedidos.this,android.R.layout.simple_list_item_1,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                detallesP.setAdapter(null);
                detallesP.setAdapter(adapter);

                //dialog.show();
            }
        });

    }

    protected void llenarSpinnerResidencias(){
        SqliteHelper esapp = new SqliteHelper(getApplicationContext());
        final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT ID_Proveedor as _id, Nombre FROM proveedores",null);

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spProveedores.setAdapter(null);
        spProveedores.setAdapter(adapter);

    }
}
