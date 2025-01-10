package com.merlita.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MiContenedor> {
    Context context;
    ArrayList<DatosPersonales> lista;

    @NonNull
    @Override
    public MiContenedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflador.inflate(R.layout.text_row_item, parent, false);
        return new Adaptador.MiContenedor(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedor holder, int position) {
        holder.tvNombre.setText(lista.get(position).getNombre());
        holder.tvEdad.setText(lista.get(position).getEdad()+"");
        holder.imagen.setImageResource(R.mipmap.aa);
    }

    public Adaptador(Context context, ArrayList<DatosPersonales> lista) {

        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MiContenedor extends RecyclerView.ViewHolder{
        TextView tvNombre, tvEdad;
        ImageView imagen;

        public MiContenedor(@NonNull View itemView) {
            super(itemView);

            tvNombre = (TextView) itemView.findViewById(R.id.textView);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            imagen = itemView.findViewById(R.id.imageView);

        }
    }


    public Adaptador(@NonNull Context context) {
        super();
    }



}
