package com.merlita.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    RecyclerView vistaRecycler;
    ArrayList<DatosPersonales> lista = new ArrayList<DatosPersonales>();
    TextView tv;
    Adaptador adaptador;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inicializarLista();
        tv = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        vistaRecycler = findViewById(R.id.recyclerView);
        adaptador = new Adaptador(this, this, lista);

        vistaRecycler.setLayoutManager(new LinearLayoutManager(this));
        vistaRecycler.setAdapter(adaptador);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(this, Colores.class);
                lanzador.launch(i);
            }
        });
    }

    private void inicializarLista() {
        lista.add(new DatosPersonales("Pepito", 3));
        lista.add(new DatosPersonales("Tepito", 2));
        lista.add(new DatosPersonales("Depito", 4));
        lista.add(new DatosPersonales("Jepito", 6));
    }


    @Override
    public void onClick(View view) {
        tv.setText(lista.get(
                vistaRecycler.getChildAdapterPosition(view)).getNombre());
    }



    ActivityResultLauncher<Intent> lanzador = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult resultado)
                {
                    //Esto es un intent:
                    Intent i = resultado.getData();
                    assert i != null;
                    tv.setText(i.getStringExtra("NOMBRE"));
                }
            }
    );


}