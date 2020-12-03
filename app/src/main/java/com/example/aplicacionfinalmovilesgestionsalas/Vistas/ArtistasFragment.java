package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Adaptadores.AdaptadorArtistas;
import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.MainActivity;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;


public class ArtistasFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Artistas> listaArtistas=new ArrayList<>();
    private Artistas a;
    private Button b;
    private Funciones f;
    private boolean verArtistas=false;

    public ArtistasFragment() {
    }



    public static ArtistasFragment newInstance(String param1, String param2) {
        ArtistasFragment fragment = new ArtistasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            listaArtistas=(ArrayList) getArguments().getSerializable("listaArtistas");
            if(listaArtistas==null){
                a=(Artistas) getArguments().getSerializable("a");
                if(a!=null){
                    listaArtistas=new ArrayList<>();
                    listaArtistas.add(a);
                }
            }
            verArtistas=true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_artistas, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Artistas");
        if(!verArtistas){
            cargarArtistas();

        }
        rv=v.findViewById(R.id.rv_artistas);
        b=v.findViewById(R.id.bt_addComentarios);
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter=new AdaptadorArtistas(listaArtistas,getActivity());
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addComentarios:
                Navigation.findNavController(v).navigate(R.id.action_nav_artistas_to_crearArtista);
                break;
        }
    }
    public void cargarArtistas(){
        f=new Funciones(getContext());
        listaArtistas=f.listarArtistas();
    }

}