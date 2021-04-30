package com.example.cecy_.esapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class crearSMS extends AppCompatActivity {
    private Button btnEnviarSMS;
    private Spinner spProveedores;
    private EditText mensaje;
    private int ID_Proveedor;
    private String telefono, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sms);

        btnEnviarSMS = (Button) findViewById(R.id.btnEnviar);
        spProveedores = (Spinner) findViewById(R.id.spProveedor);
        mensaje = (EditText) findViewById(R.id.etMensaje);


        llenarSpinnerResidencias();

        btnEnviarSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sms = mensaje.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(telefono, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Enviado!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS fallido, intente después!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        spProveedores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ID_Proveedor = ((Cursor) spProveedores.getSelectedItem()).getInt(0);
                telefono = ((Cursor) spProveedores.getSelectedItem()).getString(2);

                /*Toast.makeText(crearSMS.this, "" + telefono,
                        Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    protected void llenarSpinnerResidencias(){
        SqliteHelper esapp = new SqliteHelper(getApplicationContext());
        final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT ID_Proveedor as _id, Nombre, Celular FROM proveedores",null);

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spProveedores.setAdapter(null);
        spProveedores.setAdapter(adapter);

    }
}
