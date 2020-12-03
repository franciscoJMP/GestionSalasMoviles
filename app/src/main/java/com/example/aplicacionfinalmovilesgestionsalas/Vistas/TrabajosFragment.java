package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;


import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorTrabajos;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;


public class TrabajosFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Trabajos> listaTrabajos = new ArrayList<>();
    private Button b;
    private Funciones f;



    public TrabajosFragment() {
    }


    public static TrabajosFragment newInstance(String param1, String param2) {
        TrabajosFragment fragment = new TrabajosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trabajos, container, false);
        cargarLista();
        rv=v.findViewById(R.id.rv_trabajos);
        b=v.findViewById(R.id.bt_addTrabajo);
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter=new AdaptadorTrabajos(listaTrabajos,getContext());
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addTrabajo:
                ArrayList<Artistas> hayArtistas = f.listarArtistas();
                if(hayArtistas.size()>0){
                    Navigation.findNavController(v).navigate(R.id.action_nav_trabajos_to_crearTrabajosFragment);
                } else{
                    Toast.makeText(getContext(),R.string.noArtistas,Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
    public void cargarLista(){
        f=new Funciones(getContext());
        listaTrabajos=f.listarTrabajos();
    }
}