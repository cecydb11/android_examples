package com.example.cecy_.esapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText usuario, clave;
    private Button ingresar;
    private TextView crearCuenta;
    public static String user, pass, usuarioTipo;
    public static int usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.etUsuario);
        clave = (EditText) findViewById(R.id.etClave);
        ingresar = (Button) findViewById(R.id.Ingresar);
        crearCuenta = (TextView) findViewById(R.id.tvCrear);

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activ = new Intent(login.this, crearCuenta.class);
                startActivity(activ);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SqliteHelper zipper = new SqliteHelper(getApplicationContext());
                SQLiteDatabase bd = zipper.getWritableDatabase();
                user = usuario.getText().toString();
                pass = clave.getText().toString();

                Cursor fila = bd.rawQuery("SELECT *  FROM usuarios WHERE Usuario LIKE '" + user + "' AND Activo = 1", null);
                if (fila.moveToFirst()) {
                    if((fila.getString(2)).equals(pass)) {
                        usuarioID = fila.getInt(0);
                        usuarioTipo = fila.getString(3);

                        if(usuarioTipo.equals("admin")){
                            usuario.setText("");
                            clave.setText("");
                            Intent activ = new Intent(login.this, menuProveedor.class);
                            startActivity(activ);
                        }else{
                            usuario.setText("");
                            clave.setText("");
                            Intent activ = new Intent(login.this, menuCliente.class);
                            startActivity(activ);
                        }
                    }else{
                        Toast.makeText(login.this, "Contraseña incorrecta.",
                                Toast.LENGTH_LONG).show();
                        usuario.setText("");
                        clave.setText("");
                    }
                } else {
                    Toast.makeText(login.this, "No existe el usuario.",
                            Toast.LENGTH_LONG).show();
                    usuario.setText("");
                    clave.setText("");
                    bd.close();
                }
            }
        });
    }
}
