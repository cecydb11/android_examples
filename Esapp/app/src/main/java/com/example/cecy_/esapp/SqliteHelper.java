package com.example.cecy_.esapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cecy_ on 22/05/2018.
 */
public class SqliteHelper extends SQLiteOpenHelper{
    //Nombre DB
    public static final String DATABASE_NAME = "esapp";
    //Version DB
    public static final int DATABASE_VERSION = 14;

    //Crear Tablas
    public static final String sqlCatalogo = "CREATE TABLE catalogo (ID_Catalogo INTEGER PRIMARY KEY AUTOINCREMENT, Proveedor_FK INTEGER, Producto_FK INTEGER, Precio, FOREIGN KEY(Proveedor_FK) REFERENCES proveedores(ID_Proveedor), FOREIGN KEY(Producto_FK) REFERENCES productos(ID_Producto))";
    public static final String sqlCategoriaProducto = "CREATE TABLE categoria_producto (ID_Categoria INTEGER PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR)";
    public static final String sqlClientes = "CREATE TABLE clientes (ID_Clientes INTEGER PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR, RazonSocial VARCHAR, RFC VARCHAR, Telefono VARCHAR, Celular VARCHAR, Ciudad VARCHAR, Estado VARCHAR, Pais VARCHAR, CP VARCHAR, Domicilio VARCHAR, Contacto VARCHAR, Correo VARCHAR, Usuario_FK VARCHAR)";
    public static final String sqlPedidos = "CREATE TABLE pedidos (ID_Pedido INTEGER PRIMARY KEY AUTOINCREMENT, Fecha_Pedido DATE, Fecha_Entrega DATE, Proveedor_FK INTEGER, Cliente_FK INTEGER, FOREIGN KEY(Proveedor_FK) REFERENCES proveedores(ID_Proveedor), FOREIGN KEY(Cliente_FK) REFERENCES clientes(ID_Clientes))";
    public static final String sqlPedidosDetalles = "CREATE TABLE pedidos_detalles (ID_Pedido_Detalle INTEGER PRIMARY KEY AUTOINCREMENT, Cantidad VARCHAR, Pedido_FK INTEGER, Catalogo_Detalle_FK INTEGER, FOREIGN KEY(Pedido_FK) REFERENCES pedidos(ID_Pedido), FOREIGN KEY(Catalogo_Detalle_FK) REFERENCES catalogo_detalle(ID_Catalogo_Detalle))";
    public static final String sqlProductos = "CREATE TABLE productos (ID_Producto INTEGER PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR, Descripcion VARCHAR, Imagen VARCHAR, Categoria_FK INTEGER, FOREIGN KEY(Categoria_FK) REFERENCES categoria_producto(ID_Categoria))";
    public static final String sqlProveedores = "CREATE TABLE proveedores (ID_Proveedor INTEGER PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR, RazonSocial VARCHAR, RFC VARCHAR, Telefono VARCHAR, Celular VARCHAR, Ciudad VARCHAR, Estado VARCHAR, Pais VARCHAR, CP VARCHAR, Domicilio VARCHAR, Contacto VARCHAR, Correo VARCHAR, Categoria_FK INTEGER, Usuario_FK VARCHAR, FOREIGN KEY(Categoria_FK) REFERENCES categoria_producto(ID_Categoria))";
    public static final String sqlUsuarios = "CREATE TABLE usuarios (ID_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, Usuario VARCHAR, Contra VARCHAR, Tipo VARCHAR, Activo INTEGER)";

    public SqliteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCatalogo);
        sqLiteDatabase.execSQL(sqlCategoriaProducto);
        sqLiteDatabase.execSQL(sqlClientes);
        sqLiteDatabase.execSQL(sqlPedidos);
        sqLiteDatabase.execSQL(sqlPedidosDetalles);
        sqLiteDatabase.execSQL(sqlProductos);
        sqLiteDatabase.execSQL(sqlProveedores);
        sqLiteDatabase.execSQL(sqlUsuarios);

        //Insertar valores predeterminados de prueba

        //Tabla usuarios
        //Crear content values
        ContentValues valUsuarios = new ContentValues();

        //Agregar valores
        valUsuarios.put("Usuario", "Cecy");
        valUsuarios.put("Contra", "1104");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "User");
        valUsuarios.put("Contra", "123");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "usuario");
        valUsuarios.put("Contra", "123");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Carlos");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Jose");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Juan");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Maria");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Guadalupe");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Gabriel");
        valUsuarios.put("Contra", "321");
        valUsuarios.put("Tipo", "admin");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Agregar valores
        valUsuarios.put("Usuario", "Lalo");
        valUsuarios.put("Contra", "1234");
        valUsuarios.put("Tipo", "normal");
        valUsuarios.put("Activo", "1");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("usuarios", null, valUsuarios);

        //Tabla proveedores
        //Crear content values
        ContentValues valProveedores = new ContentValues();

        //Agregar valores
        valProveedores.put("Nombre", "Empresa1");
        valProveedores.put("RazonSocial", "Empresa1 SA de CV");
        valProveedores.put("RFC", "EM12345DSDA");
        valProveedores.put("Telefono", "7857485");
        valProveedores.put("Celular", "3485937781");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "La calle de arriba #123");
        valProveedores.put("Contacto", "José López");
        valProveedores.put("Correo", "empresa1@gmail.com");
        valProveedores.put("Categoria_FK", "1");
        valProveedores.put("Usuario_FK", "Cecy");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Fruteria don Luis");
        valProveedores.put("RazonSocial", "Fruteria don Luis");
        valProveedores.put("RFC", "FHFGHGHFG54645645");
        valProveedores.put("Telefono", "34234235345");
        valProveedores.put("Celular", "3485937781");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Avenida roja #555");
        valProveedores.put("Contacto", "Luis Farias");
        valProveedores.put("Correo", "donluis@gmail.com");
        valProveedores.put("Categoria_FK", "2");
        valProveedores.put("Usuario_FK", "User");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Dulceria Grande");
        valProveedores.put("RazonSocial", "Dulceria Grande");
        valProveedores.put("RFC", "UYTUY345YUYT");
        valProveedores.put("Telefono", "765756757");
        valProveedores.put("Celular", "3485937781");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Avenida del norte #123");
        valProveedores.put("Contacto", "Gabriela Fernandez");
        valProveedores.put("Correo", "dulceriagrande@gmail.com");
        valProveedores.put("Categoria_FK", "3");
        valProveedores.put("Usuario_FK", "Usuario");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Hueveria de Tepa");
        valProveedores.put("RazonSocial", "Hueveria de Tepa");
        valProveedores.put("RFC", "V9SDV89SDV9SD8");
        valProveedores.put("Telefono", "6454524234");
        valProveedores.put("Celular", "3481295730");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Callezota #165");
        valProveedores.put("Contacto", "Jazmin Dominguez");
        valProveedores.put("Correo", "hueveriadetepa@gmail.com");
        valProveedores.put("Categoria_FK", "4");
        valProveedores.put("Usuario_FK", "Carlos");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Sopas frescas");
        valProveedores.put("RazonSocial", "Sopas frescas");
        valProveedores.put("RFC", "H1B32JH3B123");
        valProveedores.put("Telefono", "6546456");
        valProveedores.put("Celular", "3481295730");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Calle de alla #551");
        valProveedores.put("Contacto", "Flor Macias");
        valProveedores.put("Correo", "sopasfrescas@gmail.com");
        valProveedores.put("Categoria_FK", "5");
        valProveedores.put("Usuario_FK", "Jose");


        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Latas de comida");
        valProveedores.put("RazonSocial", "Latas de comida");
        valProveedores.put("RFC", "N45H4IUH53");
        valProveedores.put("Telefono", "143467654");
        valProveedores.put("Celular", "3481295730");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Colon #777");
        valProveedores.put("Contacto", "Juanito Garcia");
        valProveedores.put("Correo", "latasdecomida@gmail.com");
        valProveedores.put("Categoria_FK", "6");
        valProveedores.put("Usuario_FK", "Juan");


        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Jugos naturales");
        valProveedores.put("RazonSocial", "Jugos naturales");
        valProveedores.put("RFC", "SDFSDF564SD6F5");
        valProveedores.put("Telefono", "565688568");
        valProveedores.put("Celular", "3481295730");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Calle de alla #551");
        valProveedores.put("Contacto", "Flor Macias");
        valProveedores.put("Correo", "jugosnaturales@gmail.com");
        valProveedores.put("Categoria_FK", "7");
        valProveedores.put("Usuario_FK", "Maria");


        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Maquillajes Marta");
        valProveedores.put("RazonSocial", "Maquillajes Marta");
        valProveedores.put("RFC", "B1GBTB1T65BT65");
        valProveedores.put("Telefono", "8565677656");
        valProveedores.put("Celular", "3481266788");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Dr Perez #198");
        valProveedores.put("Contacto", "Jesus Boites");
        valProveedores.put("Correo", "maquillajesmarta@gmail.com");
        valProveedores.put("Categoria_FK", "8");
        valProveedores.put("Usuario_FK", "Guadalupe");


        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Limpios siempre");
        valProveedores.put("RazonSocial", "Limpios siempre");
        valProveedores.put("RFC", "302T1H301T1GRG");
        valProveedores.put("Telefono", "35165400");
        valProveedores.put("Celular", "3481266788");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Callezota #165");
        valProveedores.put("Contacto", "Jazmin Dominguez");
        valProveedores.put("Correo", "limpiossiempre@gmail.com");
        valProveedores.put("Categoria_FK", "9");
        valProveedores.put("Usuario_FK", "Gabriel");


        //Insertar registro en: usuarios
        sqLiteDatabase.insert("proveedores", null, valProveedores);

        //Agregar valores
        valProveedores.put("Nombre", "Jesus Eduardo Garcia Becerra");
        valProveedores.put("RazonSocial", "Jesus Eduardo Garcia Becerra");
        valProveedores.put("RFC", "GSGRGDRGD453534");
        valProveedores.put("Telefono", "7845888");
        valProveedores.put("Celular", "3481266788");
        valProveedores.put("Ciudad", "Arandas");
        valProveedores.put("Estado", "Jalisco");
        valProveedores.put("Pais", "Mexico");
        valProveedores.put("CP", "47180");
        valProveedores.put("Domicilio", "Av del Rio #190");
        valProveedores.put("Contacto", "Eduardo Becerra");
        valProveedores.put("Correo", "lalobecerra7@gmail.com");
        valProveedores.put("Usuario_FK", "Lalo");

        //Insertar registro en: usuarios
        sqLiteDatabase.insert("clientes", null, valProveedores);

        //Tabla usuarios
        //Crear content values
        ContentValues valCategorias = new ContentValues();

        //Agregar valores
        valCategorias.put("Nombre", "Carnes y embutidos");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Frutas y verduras");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Panadería y dulces");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Huevos, lácteos y café");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Aceite, pasta y legumbres");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Conservas y comida preparada");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Jugos y bebidas");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Cosmética y cuidado personal");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Agregar valores
        valCategorias.put("Nombre", "Hogar y limpieza");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("categoria_producto", null, valCategorias);

        //Tabla catalogo
        //Crear content values
        ContentValues valCatalogo = new ContentValues();

        //Agregar valores

        //Proveedor1
        valCatalogo.put("Proveedor_FK", "1");
        valCatalogo.put("Producto_FK", "1");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "1");
        valCatalogo.put("Producto_FK", "2");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "1");
        valCatalogo.put("Producto_FK", "3");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "1");
        valCatalogo.put("Producto_FK", "4");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);


        valCatalogo.put("Proveedor_FK", "1");
        valCatalogo.put("Producto_FK", "5");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor2
        valCatalogo.put("Proveedor_FK", "2");
        valCatalogo.put("Producto_FK", "6");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "2");
        valCatalogo.put("Producto_FK", "7");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "2");
        valCatalogo.put("Producto_FK", "8");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "2");
        valCatalogo.put("Producto_FK", "9");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);


        valCatalogo.put("Proveedor_FK", "2");
        valCatalogo.put("Producto_FK", "10");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor3
        valCatalogo.put("Proveedor_FK", "3");
        valCatalogo.put("Producto_FK", "11");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "3");
        valCatalogo.put("Producto_FK", "12");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "3");
        valCatalogo.put("Producto_FK", "13");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "3");
        valCatalogo.put("Producto_FK", "14");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);


        valCatalogo.put("Proveedor_FK", "3");
        valCatalogo.put("Producto_FK", "15");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor4
        valCatalogo.put("Proveedor_FK", "4");
        valCatalogo.put("Producto_FK", "16");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "4");
        valCatalogo.put("Producto_FK", "17");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "4");
        valCatalogo.put("Producto_FK", "18");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "4");
        valCatalogo.put("Producto_FK", "19");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "4");
        valCatalogo.put("Producto_FK", "20");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor5
        valCatalogo.put("Proveedor_FK", "5");
        valCatalogo.put("Producto_FK", "21");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "5");
        valCatalogo.put("Producto_FK", "22");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "5");
        valCatalogo.put("Producto_FK", "23");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "5");
        valCatalogo.put("Producto_FK", "24");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "5");
        valCatalogo.put("Producto_FK", "25");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor6
        valCatalogo.put("Proveedor_FK", "6");
        valCatalogo.put("Producto_FK", "26");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "6");
        valCatalogo.put("Producto_FK", "27");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "6");
        valCatalogo.put("Producto_FK", "28");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "6");
        valCatalogo.put("Producto_FK", "29");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "6");
        valCatalogo.put("Producto_FK", "30");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor7
        valCatalogo.put("Proveedor_FK", "7");
        valCatalogo.put("Producto_FK", "31");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "7");
        valCatalogo.put("Producto_FK", "32");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "7");
        valCatalogo.put("Producto_FK", "33");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "7");
        valCatalogo.put("Producto_FK", "34");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "7");
        valCatalogo.put("Producto_FK", "35");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor8
        valCatalogo.put("Proveedor_FK", "8");
        valCatalogo.put("Producto_FK", "36");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "8");
        valCatalogo.put("Producto_FK", "37");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "8");
        valCatalogo.put("Producto_FK", "38");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "8");
        valCatalogo.put("Producto_FK", "39");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "8");
        valCatalogo.put("Producto_FK", "40");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Proveedor9
        valCatalogo.put("Proveedor_FK", "9");
        valCatalogo.put("Producto_FK", "41");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "9");
        valCatalogo.put("Producto_FK", "42");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "9");
        valCatalogo.put("Producto_FK", "43");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "9");
        valCatalogo.put("Producto_FK", "44");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        valCatalogo.put("Proveedor_FK", "9");
        valCatalogo.put("Producto_FK", "45");
        valCatalogo.put("Precio", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("catalogo", null, valCatalogo);

        //Tabla productos
        //Crear content values
        ContentValues valProductos = new ContentValues();

        //Agregar valores
        valProductos.put("Nombre", "Jamon Fud");
        valProductos.put("Descripcion", "Virginia Pavo y Cerdo 290gr");
        valProductos.put("Categoria_FK", "1");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jamon Kir");
        valProductos.put("Descripcion", "Pavo 250gr");
        valProductos.put("Categoria_FK", "1");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jamon Fud");
        valProductos.put("Descripcion", "Pechuga de Pavo 250gr");
        valProductos.put("Categoria_FK", "1");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Salchicha Fud");
        valProductos.put("Descripcion", "Viena de Pavo 256gr");
        valProductos.put("Categoria_FK", "1");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jamon Fud");
        valProductos.put("Descripcion", "Virginia de Pavo 290gr");
        valProductos.put("Categoria_FK", "1");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Piña");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "2");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Chayote");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "2");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Zanahoria");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "2");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Naranja");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "2");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Limon");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "2");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Donitas Bimbo");
        valProductos.put("Descripcion", "Espolvoreadas 6 piezas 105gr");
        valProductos.put("Categoria_FK", "3");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Nito Bimbo");
        valProductos.put("Descripcion", "1 Pieza 62gr");
        valProductos.put("Categoria_FK", "3");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Snickers");
        valProductos.put("Descripcion", "1 Pieza 50gr");
        valProductos.put("Categoria_FK", "3");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Milky Way");
        valProductos.put("Descripcion", "1 Pieza 50gr");
        valProductos.put("Categoria_FK", "3");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Mantecadas Bimbo");
        valProductos.put("Descripcion", "Vainilla 4 piezas 125gr");
        valProductos.put("Categoria_FK", "3");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Huevo");
        valProductos.put("Descripcion", "1kg");
        valProductos.put("Categoria_FK", "4");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Leche Sello Rojo");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "4");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Leche 19 Hnos");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "4");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Nescafe");
        valProductos.put("Descripcion", "Frasco 60gr");
        valProductos.put("Categoria_FK", "4");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Coffee Mate");
        valProductos.put("Descripcion", "Frasco 400gr");
        valProductos.put("Categoria_FK", "4");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Aceite Cristal");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Aceite 1-2-3");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Pasta La moderna");
        valProductos.put("Descripcion", "Fideo 2");
        valProductos.put("Categoria_FK", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Frijol");
        valProductos.put("Descripcion", "Costal 50kg Peruano Bola");
        valProductos.put("Categoria_FK", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Frijol");
        valProductos.put("Descripcion", "Costal 50kg Texano");
        valProductos.put("Categoria_FK", "5");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "La Costeña");
        valProductos.put("Descripcion", "Chicharos Lata 220gr");
        valProductos.put("Categoria_FK", "6");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "La Costeña");
        valProductos.put("Descripcion", "Verdura Lata 410gr");
        valProductos.put("Categoria_FK", "6");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "La Costeña");
        valProductos.put("Descripcion", "Piña en Almibar Lata 800gr");
        valProductos.put("Categoria_FK", "6");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "La Costeña");
        valProductos.put("Descripcion", "Elote Dorado Lata 220gr");
        valProductos.put("Categoria_FK", "6");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "La Costeña");
        valProductos.put("Descripcion", "Durazno en Almibar Lata 820gr");
        valProductos.put("Categoria_FK", "6");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jumex");
        valProductos.put("Descripcion", "Frasco Durazno 450ml");
        valProductos.put("Categoria_FK", "7");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jumex");
        valProductos.put("Descripcion", "Carton 1lt Piña");
        valProductos.put("Categoria_FK", "7");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Del Valle");
        valProductos.put("Descripcion", "Frasco Manzana 413ml");
        valProductos.put("Categoria_FK", "7");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Del Valle");
        valProductos.put("Descripcion", "Frasco Mango 413ml");
        valProductos.put("Categoria_FK", "7");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Coca Cola");
        valProductos.put("Descripcion", "Lata 355ml");
        valProductos.put("Categoria_FK", "7");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Jabon Dove");
        valProductos.put("Descripcion", "Barra 135gr");
        valProductos.put("Categoria_FK", "8");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Vitacilina");
        valProductos.put("Descripcion", "Tubo 28gr");
        valProductos.put("Categoria_FK", "8");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Crema Nivea");
        valProductos.put("Descripcion", "Frasco 400ml");
        valProductos.put("Categoria_FK", "8");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Pasta Colgate");
        valProductos.put("Descripcion", "Triple Accion Tubo 150ml");
        valProductos.put("Categoria_FK", "8");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Pasta Colgate");
        valProductos.put("Descripcion", "Total 12 Tubo 150ml");
        valProductos.put("Categoria_FK", "8");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Fabuloso");
        valProductos.put("Descripcion", "Lavanda 1lt");
        valProductos.put("Categoria_FK", "9");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Fabuloso");
        valProductos.put("Descripcion", "Citricos 1lt");
        valProductos.put("Categoria_FK", "9");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Suavitel");
        valProductos.put("Descripcion", "Clasico 850ml");
        valProductos.put("Categoria_FK", "9");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Pinol");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "9");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);

        //Agregar valores
        valProductos.put("Nombre", "Clorox");
        valProductos.put("Descripcion", "1lt");
        valProductos.put("Categoria_FK", "9");

        //Insertar registro en: categoria_producto
        sqLiteDatabase.insert("productos", null, valProductos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //Eliminar si existe para hacer el upgrade
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS catalogo");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS categoria_producto");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS clientes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos_detalles");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS productos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS proveedores");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(sqLiteDatabase);

    }
}
