package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;


public class ModificarExposicionFragment extends Fragment implements View.OnClickListener {
    Exposiciones e = new Exposiciones();
    private EditText tx1, tx2, tx3, tx4, tx5;
    Button b, c;
    private long resultado;
    private Funciones f;
    private boolean comprobar;

    public ModificarExposicionFragment() {
        // Required empty public constructor
    }


    public static ModificarExposicionFragment newInstance(String param1, String param2) {
        ModificarExposicionFragment fragment = new ModificarExposicionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            e = (Exposiciones) getArguments().getSerializable("e");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_exposicion, container, false);
        tx1 = v.findViewById(R.id.tx_modIdExposicion);
        tx2 = v.findViewById(R.id.tx_modNombreExpo);
        tx3 = v.findViewById(R.id.tx_modDescExposicion);
        tx4 = v.findViewById(R.id.tx_modfecInicioExpo);
        tx5 = v.findViewById(R.id.tx_modfecFinExpo);
        b = v.findViewById(R.id.bt_modificarExpo);
        c = v.findViewById(R.id.bt_volverExpoMod);
        rellenarCampos();
        b.setOnClickListener(this);
        c.setOnClickListener(this);


        return v;
    }

    private void rellenarCampos() {
        tx1.setText(String.valueOf(e.getIdExposicion()));
        tx1.setEnabled(false);
        tx2.setText(e.getNombreExp());
        tx3.setText(e.getDescripcion());
        tx4.setText(e.getFechaInicio());
        tx5.setText(e.getFechaFin());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_modificarExpo:
                comprobar = comprobarCampos(tx1, tx2, tx3, tx4,tx5);
                if (comprobar) {
                    Exposiciones ex = new Exposiciones(Integer.parseInt(tx1.getText().toString()), tx2.getText().toString(), tx3.getText().toString(), tx4.getText().toString(), tx5.getText().toString());
                    f = new Funciones(getContext());
                    resultado = f.modificarExposicion(ex);
                    if (resultado != -1) {
                        Toast.makeText(getActivity(), R.string.mensajeModExpo, Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).navigate(R.id.action_modificarExposicionFragment_to_nav_expo);
                    }
                } else{
                    Toast.makeText(getActivity(), R.string.rellenCampos, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bt_volverExpoMod:
                Navigation.findNavController(v).navigate(R.id.action_modificarExposicionFragment_to_nav_expo);
                break;
        }
    }

    private boolean comprobarCampos(EditText tx1, EditText tx2, EditText tx3, EditText tx4,EditText tx5) {
        if (tx1.getText().toString().equals("") || tx2.getText().toString().equals("") || tx3.getText().toString().equals("") || tx4.getText().toString().equals("") || tx5.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}