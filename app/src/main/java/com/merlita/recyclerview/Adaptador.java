package com.merlita.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MiContenedor>
    implements View.OnClickListener {
    Context context;
    ArrayList<DatosPersonales> lista;
    View.OnClickListener escuchador;

    @NonNull
    @Override
    public MiContenedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflador.inflate(R.layout.text_row_item, parent, false);
        return new MiContenedor(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedor holder, int position) {
        holder.tvNombre.setText(lista.get(position).getNombre());
        holder.tvEdad.setText(lista.get(position).getEdad()+"");
        holder.imagen.setImageResource(R.mipmap.aa);
    }

    public Adaptador(View.OnClickListener escuchador,
                     Context context, ArrayList<DatosPersonales> lista) {
        this.escuchador = escuchador;
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View view) {
        if(escuchador!=null)
            escuchador.onClick(view);
    }

    public static class MiContenedor extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener
    {
        TextView tvNombre, tvEdad;
        ImageView imagen;

        public MiContenedor(@NonNull View itemView) {
            super(itemView);

            tvNombre = (TextView) itemView.findViewById(R.id.textView);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            imagen = itemView.findViewById(R.id.imageView);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                        ContextMenu.ContextMenuInfo contextMenuInfo)
        {
            contextMenu.add(getAdapterPosition(), 121, 0, "EDITAR");
            contextMenu.add(getAdapterPosition(), 122, 1, "BORRAR");

        }
    }


    public Adaptador(@NonNull Context context) {
        super();
    }



}
