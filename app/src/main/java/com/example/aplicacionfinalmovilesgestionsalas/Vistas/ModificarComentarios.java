package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;

public class ModificarComentarios extends Fragment implements View.OnClickListener {
    private EditText tx1;
    private Button bt_Modificar, volver;
    private Comentarios c;
    private Exposiciones e;
    private Funciones f;
    private ArrayList<Trabajos> listaTrabajos = new ArrayList<>();


    public ModificarComentarios() {

    }


    public static ModificarComentarios newInstance(String param1, String param2) {
        ModificarComentarios fragment = new ModificarComentarios();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            c = (Comentarios) getArguments().getSerializable("c");
            e = (Exposiciones) getArguments().getSerializable("e");
            listaTrabajos = (ArrayList) getArguments().getSerializable("listaTrabajos");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_comentarios, container, false);
        tx1 = v.findViewById(R.id.tx_modComent);
        bt_Modificar = v.findViewById(R.id.bt_modComentario);
        volver = v.findViewById(R.id.bt_volverComentMod);
        tx1.setText(c.getComentario());
        bt_Modificar.setOnClickListener(this);
        volver.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_modComentario:
                boolean comprobar = comprobarCampos(tx1);
                if (comprobar) {
                    c.setComentario(tx1.getText().toString());
                    f = new Funciones(getContext());
                    if (f.modificarComentarios(c) != -1) {
                        Bundle b = new Bundle();
                        b.putSerializable("e", e);
                        b.putSerializable("listaTrabajos", listaTrabajos);
                        Toast.makeText(getContext(), R.string.comentMod, Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.action_modificarComentarios_to_comentariosFragment, b);
                    } else {
                        Toast.makeText(getContext(), R.string.errorMod, Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getContext(), R.string.rellenCampos, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.bt_volverComentMod:
                Bundle b = new Bundle();
                b.putSerializable("e", e);
                b.putSerializable("listaTrabajos", listaTrabajos);
                Navigation.findNavController(v).navigate(R.id.action_modificarComentarios_to_comentariosFragment,b);
                break;
        }
    }

    private boolean comprobarCampos(EditText tx1) {
        if (tx1.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}