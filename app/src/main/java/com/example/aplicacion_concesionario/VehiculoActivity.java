package com.example.aplicacion_concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class VehiculoActivity extends AppCompatActivity {

    EditText jetplaca,jetmarca,jetmodelo,jetcosto,jetcolor;
    Button jbtguardar,jbtconsultar,jbtanular, jbtcancelar,jbtregresar;
    long resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);

        getSupportActionBar().hide();
        jetplaca=findViewById(R.id.etplaca);
        jetmarca=findViewById(R.id.etmarca);
        jetmodelo=findViewById(R.id.etmodelo);
        jetcolor=findViewById(R.id.etcolor);
        jetcosto=findViewById(R.id.etcosto);
        jbtguardar=findViewById(R.id.btguardar);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtanular=findViewById(R.id.btanular);
        jbtcancelar=findViewById(R.id.btcancelar);
        jbtregresar=findViewById(R.id.btregresar);

    }

    public void Guardar(View view) {
        String placa,marca,modelo,color,costo;
        ;
        placa = jetplaca.getText().toString();
        marca = jetmarca.getText().toString();
        modelo = jetmodelo.getText().toString();
        color = jetcolor.getText().toString();
        costo = jetcosto.getText().toString();
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty()
                || color.isEmpty() || costo.isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();

        } else {
            Conexion_Sqlite admin = new Conexion_Sqlite(this, "concesionario.db", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("placa",placa);
            registro.put("modelo",modelo);
            registro.put("marca",marca);
            registro.put("color",color);
            registro.put("costo",costo);
            resp=db.insert("tblVehiculo",null,registro);

            if (resp > 0){
                Limpiar_campos();
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }
    private void Limpiar_campos(){

        jetplaca.setText("");
        jetmarca.setText("");
        jetmodelo.setText("");
        jetcolor.setText("");
        jetcosto.setText("");
        jetplaca.requestFocus();
    }
}
