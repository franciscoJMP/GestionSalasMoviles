package com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class AdaptadorArtistas extends RecyclerView.Adapter<AdaptadorArtistas.AdaptadorViewHolder>{
    private ArrayList<Artistas> listaArtistas=new ArrayList<>();
    private Context context;

    public AdaptadorArtistas(ArrayList<Artistas> listaArtistas, Context context) {
        this.listaArtistas = listaArtistas;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorArtistas.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artistas,parent,false);
        AdaptadorViewHolder avh=new AdaptadorViewHolder(itemView);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
            Artistas a = listaArtistas.get(position);
            holder.tx_dniPasaporte.setText(a.getDniPasaporte());
            holder.tx_nombre.setText(a.getNombre());
            holder.tx_direccion.setText(a.getDireccion());
            holder.tx_poblacion.setText(a.getPoblacion());
            holder.tx_provincia.setText(a.getProvincia());
            holder.tx_pais.setText(a.getPais());
            holder.tx_movilTrabajo.setText(a.getMovilTrabajo());
            holder.tx_movilPersonal.setText(a.getMovilPersonal());
            holder.tx_telefonoFijo.setText(a.getTelefonoFijo());
            holder.tx_email.setText(a.getEmail());
            holder.tx_webBlog.setText(a.getWebBlog());
            holder.tx_fechaNacimiento.setText(a.getFechaNacimiento());
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    String mensaje=context.getString(R.string.seguroEliminarArtist);
                    builder.setMessage(mensaje+a.getNombre()+"?")
                            .setTitle(R.string.delartis);
                    builder.setPositiveButton(R.string.bt_aceptar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Funciones f=new Funciones(context);

                            ArrayList<Trabajos> trabajosArtista = f.obtenerTrabajosList(a);
                            for(Iterator it = trabajosArtista.iterator(); it.hasNext();){
                                Trabajos t = (Trabajos) it.next();
                                f.borrarComentariosIdTrabajos(t);
                                f.borrarTrabajo(t);
                            }

                            f.borrarTrabajosArtista(a.getDniPasaporte());
                            f.borrarExponenArtistas(a.getDniPasaporte());
                            if(f.borrarArtista(a)!=-1){
                                Toast.makeText(context,R.string.borrarExito,Toast.LENGTH_LONG).show();
                                listaArtistas.remove(position);
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
                    b.putSerializable("a",a);
                    Navigation.findNavController(v).navigate(R.id.action_nav_artistas_to_modificarArtistaFragment,b);
                }
            });
            holder.participar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putSerializable("a",a);
                    Navigation.findNavController(v).navigate(R.id.action_nav_artistas_to_participarFragment,b);

                }
            });
            holder.conEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putSerializable("a",a);
                    Navigation.findNavController(v).navigate(R.id.action_nav_artistas_to_fragmentEmail,b);
                }
            });
            holder.llamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putSerializable("a",a);
                    Navigation.findNavController(v).navigate(R.id.action_nav_artistas_to_llamarFragment,b);
                }
            });
            holder.felicitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formato2=new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date hoy = new Date();

                        String fecha1;
                        String[] auxFecha1=a.getFechaNacimiento().split("\\/");
                        fecha1=auxFecha1[2]+"-"+auxFecha1[1]+"-"+auxFecha1[0];

                        String fecha2=formato.format(hoy);
                        String[] auxFecha2 =fecha2.split("\\/");
                        fecha2=auxFecha2[2]+"-"+auxFecha2[1]+"-"+auxFecha2[0];

                        Date fechaDate=formato2.parse(fecha1);
                        Date fechaActu=formato2.parse(fecha2);

                        if(fechaDate.equals(fechaActu)){
                            String correo=a.getEmail();
                            String [] arrayEmail=correo.split(",");
                            String titulo=context.getString(R.string.felicitacion1)+a.getNombre();
                            String mensaje=context.getString(R.string.felicitacion2)+a.getNombre();

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL,arrayEmail);
                            intent.putExtra(Intent.EXTRA_SUBJECT,titulo);
                            intent.putExtra(Intent.EXTRA_TEXT,mensaje);
                            intent.setType("message/rfc822");
                            context.startActivity(Intent.createChooser(intent,context.getString(R.string.eligeClientEmail)));
                        }else{
                           Toast.makeText(context,R.string.noescumple,Toast.LENGTH_LONG).show();
                        }

                    } catch (ParseException e) {

                        Toast.makeText(context,R.string.errorFelicitarArtis,Toast.LENGTH_LONG).show();
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return listaArtistas.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        private TextView tx_dniPasaporte,tx_nombre,tx_direccion,tx_poblacion,tx_provincia,tx_pais,tx_movilTrabajo,tx_movilPersonal,tx_telefonoFijo,tx_email,tx_webBlog,tx_fechaNacimiento;
        private Button borrar,modificar,participar,conEmail,llamar,felicitar;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_dniPasaporte=itemView.findViewById(R.id.tx_dniPasaporte);
            tx_nombre=itemView.findViewById(R.id.tx_nombreArtista);
            tx_direccion=itemView.findViewById(R.id.tx_direccion);
            tx_poblacion=itemView.findViewById(R.id.tx_poblacion);
            tx_provincia=itemView.findViewById(R.id.tx_provincia);
            tx_pais=itemView.findViewById(R.id.tx_pais);
            tx_movilTrabajo=itemView.findViewById(R.id.tx_movTrabajo);
            tx_movilPersonal=itemView.findViewById(R.id.tx_movPersonal);
            tx_telefonoFijo=itemView.findViewById(R.id.tx_tefFijo);
            tx_email=itemView.findViewById(R.id.tx_email);
            tx_webBlog=itemView.findViewById(R.id.tx_webBlog);
            tx_fechaNacimiento=itemView.findViewById(R.id.tx_fechaNac);
            borrar=itemView.findViewById(R.id.bt_borrarArtista);
            modificar = itemView.findViewById(R.id.bt_modificarArtista);
            participar = itemView.findViewById(R.id.bt_participarExpo);
            conEmail=itemView.findViewById(R.id.bt_contacEmail);
            llamar=itemView.findViewById(R.id.bt_contacTelef);
            felicitar=itemView.findViewById(R.id.bt_felicitarArtista);
        }
    }
}