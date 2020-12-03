package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorExponen;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorExposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;


public class ParticiparFragment extends Fragment {
   private Artistas a = new Artistas();
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Exposiciones> listaExposiciones=new ArrayList<>();
    private Button b;



    public ParticiparFragment() {
        // Required empty public constructor
    }


    public static ParticiparFragment newInstance(String param1, String param2) {
        ParticiparFragment fragment = new ParticiparFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            a=(Artistas) getArguments().getSerializable("a");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_participar, container, false);
        cargarExposiciones();
        rv=v.findViewById(R.id.rv_exponen);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter=new AdaptadorExponen(listaExposiciones,getActivity(),a);
        rv.setAdapter(adapter);
    }
    private void cargarExposiciones(){
        Funciones f=new Funciones(getContext());
        listaExposiciones=f.listarExposiciones();
    }
}