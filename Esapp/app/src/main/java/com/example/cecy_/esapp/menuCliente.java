package com.example.cecy_.esapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class menuCliente extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ListView lvProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvProveedor = (ListView) findViewById(R.id.lvProvInicio);

        SqliteHelper esapp = new SqliteHelper(getApplicationContext());
        final Cursor c = esapp.getReadableDatabase().rawQuery("SELECT ID_Proveedor as _id, Nombre FROM proveedores",null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(menuCliente.this,android.R.layout.simple_list_item_1,c,new String[]{"Nombre"},new int[] {android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        lvProveedor.setAdapter(null);
        lvProveedor.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent activ = new Intent(menuCliente.this, crearSMS.class);
                startActivity(activ);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView nombre = (TextView) headerView.findViewById(R.id.tvUsuario);
        nombre.setText("Bienvenido " + login.user);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent activ = new Intent(menuCliente.this, perfil.class);
            startActivity(activ);
        } else if (id == R.id.nav_categorias) {
            Intent activ = new Intent(menuCliente.this, categorias.class);
            startActivity(activ);

        } else if (id == R.id.nav_pedidos) {
            Intent activ = new Intent(menuCliente.this, listaPedidos.class);
            startActivity(activ);

        } else if (id == R.id.nav_mensajes) {
            Intent activ = new Intent(menuCliente.this, crearSMS.class);
            startActivity(activ);

        } else if (id == R.id.nav_salir) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
