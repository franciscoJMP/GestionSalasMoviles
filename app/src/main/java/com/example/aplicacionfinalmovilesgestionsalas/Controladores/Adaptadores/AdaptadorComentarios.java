package com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;

public class AdaptadorComentarios extends RecyclerView.Adapter<AdaptadorComentarios.AdaptadorViewHolder> {
    private ArrayList<Comentarios> listaComentarios = new ArrayList<>();
    private Context context;

    public AdaptadorComentarios(ArrayList<Comentarios> listaComentarios, Context context) {
        this.listaComentarios = listaComentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentarios, parent, false);
        AdaptadorViewHolder avh = new AdaptadorViewHolder(itemView);
        return avh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorComentarios.AdaptadorViewHolder holder, int position) {
        Comentarios c = listaComentarios.get(position);
        holder.tx_idExposicion.setText(String.valueOf(c.getIdExposicion()));
        holder.tx_nombreTrabajo.setText(c.getNombreTrabajo());
        holder.tx_verComentarios.setText(c.getComentario());
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
               ;
                builder.setMessage(context.getString(R.string.eliminarComentario))
                        .setTitle(R.string.delcoment);
                builder.setPositiveButton(R.string.bt_aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Funciones f=new Funciones(context);

                        if(f.borrarComentario(c)!=-1){
                            Toast.makeText(context,R.string.borrarExito,Toast.LENGTH_LONG).show();
                            listaComentarios.remove(position);
                            notifyDataSetChanged();

                        } else{
                            Toast.makeText(context,R.string.borrarError,Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton(R.string.bt_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Funciones f = new Funciones(context);
                b.putSerializable("c",c);
                Exposiciones e = f.obtenerExposiciondeUnComentario(c.getIdExposicion());
                b.putSerializable("e",e);
                ArrayList<Trabajos> listaTrabajosExposicion = f.obtenerTrabajosExposiciones(e.getIdExposicion());
                b.putSerializable("listaTrabajos",listaTrabajosExposicion);
                Navigation.findNavController(v).navigate(R.id.action_comentariosFragment_to_modificarComentarios,b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_idExposicion, tx_nombreTrabajo, tx_verComentarios;
        private Button b,modificar;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_idExposicion = itemView.findViewById(R.id.tx_comentariosNomExposicion);
            tx_nombreTrabajo = itemView.findViewById(R.id.tx_nombreTrabajoComentarios);
            tx_verComentarios = itemView.findViewById(R.id.tx_mostrarComentario);
            b = itemView.findViewById(R.id.bt_borrarComentarios);
            modificar=itemView.findViewById(R.id.bt_modificarComentarios);
        }
    }
}
