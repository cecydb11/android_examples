package com.example.cecy_.esapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class categorias extends AppCompatActivity {
    private ListView lvCategorias;
    public static String nombreProveedor, Telefono;
    public static int ID_Proveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        lvCategorias = (ListView) findViewById(R.id.lvCategorias);

        SqliteHelper esapp = new SqliteHelper(getApplicationContext());
        final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT proveedores.ID_Proveedor AS _id, proveedores.Nombre, proveedores.Celular, proveedores.Domicilio, proveedores.Contacto, proveedores.Correo, categoria_producto.Nombre AS Categoria FROM proveedores LEFT JOIN categoria_producto ON Categoria_FK = ID_Categoria",null);

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        lvCategorias.setAdapter(null);
        lvCategorias.setAdapter(adapter);

        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Cursor item = (Cursor) adapter.getItem(position);
                ID_Proveedor = item.getInt(0);
                nombreProveedor = item.getString(1);

                //Toast.makeText(categorias.this, "" + ID_Proveedor, Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(categorias.this);
                dialog.setContentView(R.layout.detallesproveedor);
                dialog.setTitle("Detalles de proveedor");

                //Elementos del dialog
                TextView nombreEmpresa = (TextView) dialog.findViewById(R.id.tvNombre);
                nombreEmpresa.setText(nombreProveedor);

                TextView direccionEmpresa = (TextView) dialog.findViewById(R.id.tvDireccion);
                direccionEmpresa.setText(item.getString(3));

                TextView contactoEmpresa = (TextView) dialog.findViewById(R.id.tvContacto);
                contactoEmpresa.setText(item.getString(4));

                TextView telefonoEmpresa = (TextView) dialog.findViewById(R.id.tvTelefono);
                telefonoEmpresa.setText(item.getString(2));
                Telefono = item.getString(2);

                TextView emailEmpresa = (TextView) dialog.findViewById(R.id.tvEmail);
                emailEmpresa.setText(item.getString(5));

                TextView categoriaEmpresa = (TextView) dialog.findViewById(R.id.tvCategoria);
                categoriaEmpresa.setText(item.getString(6));

                Button pedidos = (Button) dialog.findViewById(R.id.btnPedidos);
                pedidos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activ = new Intent(categorias.this, pedidos.class);
                        startActivity(activ);
                    }
                });

                Button ubicacion = (Button) dialog.findViewById(R.id.btnUbicacion);
                ubicacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activ = new Intent(categorias.this, ubicaciones.class);
                        startActivity(activ);
                    }
                });

                dialog.show();
            }
        });


    }
}
