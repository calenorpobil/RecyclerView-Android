package com.merlita.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AltaActivity extends AppCompatActivity {
    EditText etNombre, etEdad;
    Button bt;
    Intent upIntent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etNombre = findViewById(R.id.etNombre);
        etNombre.setHint("Nombre del nuevo alumno");
        etEdad = findViewById(R.id.etEdad);
        etEdad.setHint("Edad del nuevo alumno");
        bt = findViewById(R.id.btVolver);



    }

    public void clickVolver(View v){
        Intent i = new Intent();
        String nombre = String.valueOf(etNombre.getText());
        String edad = String.valueOf(etEdad.getText());

        if (nombre.equals("") || edad.equals("")) {
            setResult(RESULT_CANCELED);
        }else{
            i.putExtra("NOMBRE", nombre);
            i.putExtra("EDAD", edad);
            setResult(RESULT_OK, i);
        }


        finish();





    }
}
