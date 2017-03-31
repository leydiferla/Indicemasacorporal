package com.example.leydi.indicemasacorporal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Pormi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pormi);
    }

    public void Salir(View v) {
        finish();
    }
}