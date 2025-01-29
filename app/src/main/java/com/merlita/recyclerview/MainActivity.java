package com.merlita.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    RecyclerView vistaRecycler;
    ArrayList<DatosPersonales> lista = new ArrayList<DatosPersonales>();
    TextView tv;
    Adaptador adaptador;
    Button btAlta;
    EditText et;
    int posicionEdicion;
    DatosPersonales auxiliar;
    private final ArrayList<DatosPersonales> datosVacio =
            new ArrayList<>();

    Intent resultado = null;

    private void toast(miExcepcion e) {
        Toast.makeText(this, e.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if(lista.isEmpty()){
            try {
                auxiliar = new DatosPersonales(
                        "Sin ítems",
                        -1);
            } catch (miExcepcion e) {
                toast(e);
            }
            //El mensaje de inicio:
            datosVacio.add(auxiliar);
        }
        try {
            inicializarLista();
        } catch (miExcepcion e) {
            toast(e);
        }
        tv = findViewById(R.id.textView);
        btAlta = findViewById(R.id.btAlta);
        //et = findViewById(R.id.editTextText);
        vistaRecycler = findViewById(R.id.recyclerView);
        adaptador = new Adaptador(this, this, lista);

        vistaRecycler.setLayoutManager(new LinearLayoutManager(this));
        vistaRecycler.setAdapter(adaptador);

        btAlta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, AltaActivity.class);
                lanzadorAlta.launch(i);
                //setResult(RESULT_OK, i);
                //finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        /*if(item.getItemId() == R.id.item_alta){
            posicionEdicion=-999;
            lanzadorEdit.launch(new Intent(
                    this, EditActivity.class));
        }*/
        return true;
    }

    //MENU CONTEXTUAL
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        switch(item.getItemId())
        {
            case 121:
                //MENU --> EDITAR
                Intent i = new Intent(this, EditActivity.class);
                posicionEdicion = item.getGroupId();
                String nombre = lista.get(posicionEdicion).getNombre();
                int edad = lista.get(posicionEdicion).getEdad();
                i.putExtra("NOMBRE", nombre);
                i.putExtra("EDAD", edad);
                lanzadorEdit.launch(i);
                return true;
            case 122:
                //MENU --> BORRAR
                lista.remove(item.getGroupId());
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void inicializarLista() throws miExcepcion {
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


    ActivityResultLauncher<Intent> lanzadorEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult resultado)
                {
                    //RECOGER EDIT ACTIVITY
                    if(resultado.getResultCode()==RESULT_OK){
                        Intent i = resultado.getData();
                        assert i != null;
                        String nombre = i.getStringExtra("NOMBRE");
                        int edad = Integer.parseInt(Objects.requireNonNull(i.getStringExtra("EDAD")));

                        try {
                            if(posicionEdicion==-999){
                                lista.add(0, new DatosPersonales(nombre, edad));
                            }else{
                                lista.set(posicionEdicion, new DatosPersonales(nombre, edad));
                            }
                        } catch (miExcepcion e) {
                            toast(e);
                        }
                        adaptador.notifyDataSetChanged();
                    }
                }
            }
    );


    ActivityResultLauncher<Intent>
            lanzadorAlta = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                //RECOGER ALTA ACTIVITY
                public void onActivityResult(ActivityResult resultado) {
                    if(resultado.getResultCode()==RESULT_OK){
                        //CON DATOS
                        Intent datos = resultado.getData();
                        assert datos != null;
                        String nombre = datos.getStringExtra("NOMBRE");
                        int edad = Integer.parseInt(Objects.requireNonNull(datos.getStringExtra("EDAD")));
                        try {
                            lista.add(new DatosPersonales(nombre, edad));
                        } catch (miExcepcion e) {
                            toast(e);
                        }
                        adaptador.notifyDataSetChanged();
                    } else{
                        //SIN DATOS



                    }
                }
            });


}