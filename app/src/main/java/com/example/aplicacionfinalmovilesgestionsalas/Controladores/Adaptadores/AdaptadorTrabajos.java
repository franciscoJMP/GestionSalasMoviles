package com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class AdaptadorTrabajos extends RecyclerView.Adapter<AdaptadorTrabajos.AdaptadorViewHolder> {
    private ArrayList<Trabajos> listaTrabajos = new ArrayList<>();
    private Context context;

    public AdaptadorTrabajos(ArrayList<Trabajos> listaTrabajos, Context context) {
        this.listaTrabajos = listaTrabajos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trabajos, parent, false);
        AdaptadorViewHolder avh = new AdaptadorViewHolder(itemView);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
        InputStream is;
        BufferedInputStream bis;
        Trabajos t = listaTrabajos.get(position);
        holder.tx_nombre.setText(t.getNombreTrabajo());
        holder.tx_des.setText(t.getDescripcion());
        holder.tx_size.setText(t.getTamanio());
        holder.tx_pes.setText(t.getPeso());
        Funciones f = new Funciones(context);
        Artistas a = f.buscarArtista(t.getDniPasaporte().toString());
        holder.tx_art.setText(a.getNombre());
        File path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        String[] saux = path.getAbsolutePath().split("/");

        String pathAux = "/" + saux[1] + "/" + saux[2] + "/" + saux[3] + "/" + saux[4] + "/" + saux[5] + "/" + saux[6] + "/" + saux[7] + "/";
        String s = pathAux + t.getFoto();
        Bitmap bm = BitmapFactory.decodeFile(s);
        holder.iv.setImageBitmap(bm);
        holder.infoArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Artistas a = f.obtenerArtistasTrabajosById(t.getDniPasaporte());
                if (a != null) {
                    Bundle b = new Bundle();
                    b.putSerializable("a", a);
                    Navigation.findNavController(v).navigate(R.id.action_nav_trabajos_to_nav_artistas2, b);
                } else {
                    Toast.makeText(context, R.string.noArtistas, Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String mensaje = context.getString(R.string.seguroTrab);
                builder.setMessage(mensaje + t.getNombreTrabajo() + "?")
                        .setTitle(R.string.deltrab);
                builder.setPositiveButton(R.string.bt_aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Funciones f = new Funciones(context);
                        f.borrarComentariosIdTrabajos(t);
                        if (f.borrarTrabajo(t)!= -1) {
                            Toast.makeText(context, R.string.borrarExito, Toast.LENGTH_LONG).show();
                            listaTrabajos.remove(position);
                            notifyDataSetChanged();

                        } else {
                            Toast.makeText(context, R.string.borrarError, Toast.LENGTH_LONG).show();
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


    }

    @Override
    public int getItemCount() {
        return listaTrabajos.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_nombre, tx_des, tx_size, tx_pes, tx_art;
        private ImageView iv;
        private Button mod, infoArtista, borrar;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_nombre = itemView.findViewById(R.id.tx_nombreTrabajo);
            tx_des = itemView.findViewById(R.id.tx_desTrabajo);
            tx_size = itemView.findViewById(R.id.tx_sizeTrab);
            tx_pes = itemView.findViewById(R.id.tx_pesoTrab);
            tx_art = itemView.findViewById(R.id.tx_artistaTrab);
            iv = itemView.findViewById(R.id.im_trabajo);
            borrar = itemView.findViewById(R.id.bt_borrarTrabajo);

            infoArtista = itemView.findViewById(R.id.bt_infoArtista);

        }
    }
}
