package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorComentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;


public class ComentariosFragment extends Fragment implements View.OnClickListener {
    private Exposiciones e;
    private ArrayList<Trabajos> listaTrabajos = new ArrayList<>();
    private ArrayList<Comentarios> listaComentarios=new ArrayList<>();
    private Button b;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Funciones f;


    public ComentariosFragment() {

    }


    public static ComentariosFragment newInstance(String param1, String param2) {
        ComentariosFragment fragment = new ComentariosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            e=(Exposiciones) getArguments().getSerializable("e");
            listaTrabajos=(ArrayList) getArguments().getSerializable("listaTrabajos");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comentarios, container, false);
        b=v.findViewById(R.id.bt_addComentarios);
        cargarComentarios();
        rv=v.findViewById(R.id.rv_comentarios);
        b.setOnClickListener(this);
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter=new AdaptadorComentarios(listaComentarios,getActivity());
        rv.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addComentarios:
                Bundle b = new Bundle();
                b.putSerializable("e",e);
                b.putSerializable("listaTrabajos",listaTrabajos);
                Navigation.findNavController(v).navigate(R.id.action_comentariosFragment_to_crearComentariosFragment,b);
            break;
        }
    }
    private void cargarComentarios() {
        f=new Funciones(getContext());
        listaComentarios=f.listarComentarios(e.getIdExposicion());
    }
}