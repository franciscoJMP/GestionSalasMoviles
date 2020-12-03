package com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exponen;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;

public class AdaptadorExponen extends RecyclerView.Adapter<AdaptadorExponen.AdaptadorViewHolder> {
    private ArrayList<Exposiciones> listaExposiciones=new ArrayList<>();
    private Artistas a;
    private Context context;

    public AdaptadorExponen(ArrayList<Exposiciones> listaExposiciones, Context context,Artistas a) {
        this.listaExposiciones = listaExposiciones;
        this.a=a;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorExponen.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exponen,parent,false);
        AdaptadorViewHolder avh=new AdaptadorViewHolder(itemView);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorExponen.AdaptadorViewHolder holder, int position) {
        Exposiciones e = listaExposiciones.get(position);
        holder.tx_idExposicion.setText(String.valueOf(e.getIdExposicion()));
        holder.tx_nombreExposicion.setText(e.getNombreExp());
        holder.tx_descripcion.setText(e.getDescripcion());
        holder.tx_fecIn.setText(e.getFechaInicio());
        holder.tx_fecFin.setText(e.getFechaFin());
        holder.participar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exponen exp=new Exponen(e.getIdExposicion(),a.getDniPasaporte());
                Funciones f= new Funciones(context);
                if(f.insertarExponen(exp)!=-1){
                    Toast.makeText(context,R.string.participicaionSave,Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(context,R.string.yaestaparticipando,Toast.LENGTH_LONG).show();
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
        private Button participar;
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_idExposicion=itemView.findViewById(R.id.tx_idExposicion);
            tx_nombreExposicion=itemView.findViewById(R.id.tx_nombreExpo);
            tx_descripcion=itemView.findViewById(R.id.tx_descripcion);
            tx_fecIn=itemView.findViewById(R.id.tx_fechaIn);
            tx_fecFin=itemView.findViewById(R.id.tx_fechaFin);
            participar=itemView.findViewById(R.id.bt_borrarComentarios);

        }


    }
}
