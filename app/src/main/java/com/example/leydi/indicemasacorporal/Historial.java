package com.example.leydi.indicemasacorporal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.leydi.indicemasacorporal.R.id.et_1;


public class Historial extends AppCompatActivity {
    private EditText et_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        et_1=(EditText)findViewById(R.id.et_1);

    String[] archivos = fileList();

    if (existe(archivos, "datos.txt"))
            try {
        InputStreamReader archivo = new InputStreamReader(
                openFileInput("datos.txt"));
        BufferedReader br = new BufferedReader(archivo);
        String linea = br.readLine();
        String todo = "";
        while (linea != null) {
            todo = todo + linea + "\n";
            linea = br.readLine();
        }
        br.close();
        archivo.close();
        et_1.setText(todo);
    } catch (IOException e) {
    }
}

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
    public void grabar(View v)
    {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "datos.txt", Activity.MODE_APPEND));
            archivo.write(et_1.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();

    }

    public void Salir(View v) {
        finish();
    }
}





