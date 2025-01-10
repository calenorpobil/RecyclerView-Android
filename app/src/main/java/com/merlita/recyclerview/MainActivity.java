package com.merlita.recyclerview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView vistaRecycler;
    ArrayList<DatosPersonales> lista = new ArrayList<DatosPersonales>();
    TextView tv;
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inicializarLista();
        tv = findViewById(R.id.textView);
        vistaRecycler = findViewById(R.id.recyclerView);
        adaptador = new Adaptador(this, lista);

        vistaRecycler.setLayoutManager(new LinearLayoutManager(this));
        vistaRecycler.setAdapter(adaptador);
    }

    private void inicializarLista() {
        lista.add(new DatosPersonales("Pepito", 3));
        lista.add(new DatosPersonales("Tepito", 2));
        lista.add(new DatosPersonales("Depito", 4));
        lista.add(new DatosPersonales("Jepito", 6));
    }
}