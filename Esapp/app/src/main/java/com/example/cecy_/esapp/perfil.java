package com.example.cecy_.esapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class perfil extends AppCompatActivity {
    private TextView tvNombre, tvTipo, tvDomicilio, tvTelefono, tvContacto, tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvDomicilio = (TextView) findViewById(R.id.tvDomicilio);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvContacto = (TextView) findViewById(R.id.tvContacto);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);

        SqliteHelper zipper = new SqliteHelper(getApplicationContext());
        SQLiteDatabase bd = zipper.getWritableDatabase();

        String cadena = "";
        /*if(login.usuarioTipo.equals("admin")) {
            cadena = "SELECT Domicilio, Celular, Contacto, Correo FROM proveedores WHERE Usuario_FK LIKE '" + login.user + "'";
        }else if(login.usuarioTipo.equals("normal")){
            cadena = "SELECT Domicilio, Celular, Contacto, Correo FROM clientes WHERE Usuario_FK LIKE '" + login.user + "'";
        }

        Toast.makeText(perfil.this, "" + cadena,
                Toast.LENGTH_LONG).show();
        Cursor fila = bd.rawQuery(cadena, null);

        if (fila.moveToFirst()) {*/
            tvNombre.setText(login.user);
            tvTipo.setText("Tipo: " + login.usuarioTipo);
            /*tvDomicilio.setText(fila.getString(0));
            tvTelefono.setText(fila.getString(1));
            tvContacto.setText(fila.getString(2));
            tvCorreo.setText(fila.getString(3));
        }else {
            Toast.makeText(perfil.this, "No hay datos.",
                    Toast.LENGTH_LONG).show();
            bd.close();
        }*/
    }
}