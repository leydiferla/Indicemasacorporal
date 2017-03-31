package com.example.leydi.indicemasacorporal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity {
    EditText etPeso;
    EditText etAltura;
    TextView resultado;
    Button btnCalccular;

    Date date = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPeso=(EditText) findViewById(R.id.etPeso);
        etAltura=(EditText) findViewById(R.id.etAltura);
        resultado=(TextView) findViewById(R.id.tvResultado);
        btnCalccular=(Button) findViewById(R.id.btnCalcular);
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        etPeso.setText(prefe.getString("peso",""));
        etAltura.setText(prefe.getString("altura",""));

        btnCalccular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double peso, altura, IMC;
                peso = Double.parseDouble(etPeso.getText().toString());
                altura = Double.parseDouble(etAltura.getText().toString());

                SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_APPEND);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("peso", etPeso.getText().toString());
                editor.putString("altura", etAltura.getText().toString());
                editor.commit();

                IMC=peso/Math.pow(altura,2);

                int estado=0;
                if(IMC<=18.5){
                    estado=1;

                }
                else if (IMC >18.5 && IMC <24.99) {
                    estado = 2;
                }
                else if(IMC>25){
                    estado = 3;
                }
                switch(estado) {
                    case 1:
                        Crouton.makeText(MainActivity.this, getString(R.string.PesoBajo), Style.INFO).show();
                        break;
                    case 2:
                        Crouton.makeText(MainActivity.this, getString(R.string.pesobajonormal), Style.CONFIRM).show();
                        break;
                    case 3:
                        Crouton.makeText(MainActivity.this, getString(R.string.sobrepeso), Style.ALERT).show();
                        break;
                }
                resultado.setText(""+IMC);

                }

        });



        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "datos.txt", Activity.MODE_APPEND));
            archivo.write("Fecha: "+dateFormat.format(date)+"\n" );
            archivo.write("Hora: "+hourFormat.format(date)+"\n" );
            archivo.write("la altura es: " + etAltura.getText().toString()+"\n");
            archivo.write("el peso es: "+ etPeso.getText().toString()+"\n"+"\n");


            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();



    }

    public void grabar(View v) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "notas.txt", Activity.MODE_APPEND));
            archivo.write("Fecha: "+dateFormat.format(date)+"\n" );
            archivo.write("Hora: "+hourFormat.format(date)+"\n" );
            archivo.write("la altura es: " + etAltura.getText().toString()+"\n");
            archivo.write("el peso es: "+ etPeso.getText().toString()+"\n"+"\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();
        //finish();
    }

    public void acercade(View view) {
        Intent i = new Intent(this, Pormi.class );
        startActivity(i);
    }

    public void btnH(View view) {
        Intent i = new Intent(this, Historial.class );
        startActivity(i);
    }




}
