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
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;

public class AdaptadorExposiciones extends RecyclerView.Adapter<AdaptadorExposiciones.AdaptadorViewHolder> {
    private ArrayList<Exposiciones> listaExposiciones=new ArrayList<>();
    private Context context;

    public AdaptadorExposiciones(ArrayList<Exposiciones> listaExposiciones, Context context) {
        this.listaExposiciones = listaExposiciones;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exposiciones,parent,false);
        AdaptadorViewHolder avh=new AdaptadorViewHolder(itemView);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {

            Exposiciones e = listaExposiciones.get(position);
            holder.tx_idExposicion.setText(String.valueOf(e.getIdExposicion()));
            holder.tx_nombreExposicion.setText(e.getNombreExp());
            holder.tx_descripcion.setText(e.getDescripcion());
            holder.tx_fecIn.setText(e.getFechaInicio());
            holder.tx_fecFin.setText(e.getFechaFin());
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    String mensaje=context.getString(R.string.confirmacionDelExpo);
                    builder.setMessage(mensaje+e.getNombreExp()+"?")
                            .setTitle(R.string.EliminarExpo);
                    builder.setPositiveButton(R.string.bt_aceptar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Funciones f=new Funciones(context);
                            f.borrarExponenExposiciones(e.getIdExposicion());
                            f.borrarComentarioIdExposicion(e);
                            if(f.borrarExposicion(e)!=-1){
                                Toast.makeText(context,R.string.borrarExito,Toast.LENGTH_LONG).show();
                                listaExposiciones.remove(position);
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
                    b.putSerializable("e",e);
                    Navigation.findNavController(v).navigate(R.id.action_nav_expo_to_modificarExposicionFragment,b);
                }
            });
            holder.verArtistas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Funciones f= new Funciones(context);
                    ArrayList<String> dniPasaportes=f.obtenerArtistasExponen(e);
                    ArrayList<Artistas> listaArtistas=f.obtenerArtistasById(dniPasaportes);
                    if(listaArtistas.size()>0){
                        Bundle b = new Bundle();
                        b.putSerializable("listaArtistas",listaArtistas);
                        Navigation.findNavController(v).navigate(R.id.action_nav_expo_to_nav_artistas,b);
                    }else{
                        Toast.makeText(context,"Esta exposicon no tiene artistas",Toast.LENGTH_LONG).show();
                    }

                }
            });
            holder.verComentarios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putSerializable("e",e);
                    Funciones f= new Funciones(context);
                    ArrayList<Trabajos> listaTrabajosExposicion = f.obtenerTrabajosExposiciones(e.getIdExposicion());
                    if(listaTrabajosExposicion.size()>0){
                        b.putSerializable("listaTrabajos",listaTrabajosExposicion);
                        Navigation.findNavController(v).navigate(R.id.action_nav_expo_to_comentariosFragment,b);
                    } else{
                        Toast.makeText(context,"Esta exposicion no tiene trabajos",Toast.LENGTH_LONG).show();
                    }



                }
            });
    }
    @Override
    public int getItemCount() {
        return listaExposiciones.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        private TextView tx_idExposicion,tx_nombreExposicion,tx_descripcion,tx_fecIn,tx_fecFin;
        private Button borrar,modificar,verArtistas,verComentarios;
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_idExposicion=itemView.findViewById(R.id.tx_idExposicion);
            tx_nombreExposicion=itemView.findViewById(R.id.tx_nombreExpo);
            tx_descripcion=itemView.findViewById(R.id.tx_descripcion);
            tx_fecIn=itemView.findViewById(R.id.tx_fechaIn);
            tx_fecFin=itemView.findViewById(R.id.tx_fechaFin);
            borrar=itemView.findViewById(R.id.bt_borrarComentarios);
            modificar=itemView.findViewById(R.id.bt_modificarExpo);
            verArtistas=itemView.findViewById(R.id.bt_verArtistas);
            verComentarios=itemView.findViewById(R.id.bt_verComentarios);
        }


    }
}
