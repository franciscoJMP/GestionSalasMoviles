package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorExposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;

public class ExposicionesFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Exposiciones> listaExposiciones=new ArrayList<>();
    private Button b;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exposiciones, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        cargarExposiciones();
        rv=root.findViewById(R.id.rv);
        b=root.findViewById(R.id.bt_addExposicion);
        b.setOnClickListener(this);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter=new AdaptadorExposiciones(listaExposiciones,getActivity());
        rv.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addExposicion:
                Navigation.findNavController(v).navigate(R.id.action_nav_expo_to_crearExposicion);
                break;
        }
    }
    private void cargarExposiciones(){
        Funciones f=new Funciones(getContext());
        listaExposiciones=f.listarExposiciones();
    }


}