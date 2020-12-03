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
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;


public class ModificarArtistaFragment extends Fragment implements View.OnClickListener {
    private EditText tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, tx9, tx10, tx11, tx12;
    Button b, c;
    private Artistas a;

    public ModificarArtistaFragment() {

    }


    public static ModificarArtistaFragment newInstance(String param1, String param2) {
        ModificarArtistaFragment fragment = new ModificarArtistaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            a = (Artistas) getArguments().getSerializable("a");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_artista, container, false);
        tx1 = v.findViewById(R.id.tx_moddniPasaporte);
        tx1.setEnabled(false);
        tx2 = v.findViewById(R.id.tx_modNombreArtista);
        tx3 = v.findViewById(R.id.tx_modDireccion);
        tx4 = v.findViewById(R.id.tx_modPoblacion);
        tx5 = v.findViewById(R.id.tx_modProvincia);
        tx6 = v.findViewById(R.id.tx_modPais);
        tx7 = v.findViewById(R.id.tx_modMovilTrabajo);
        tx8 = v.findViewById(R.id.tx_modMovilPersonal);
        tx9 = v.findViewById(R.id.tx_modTelfFijo);
        tx10 = v.findViewById(R.id.tx_modEmail);
        tx11 = v.findViewById(R.id.tx_modWebBlog);
        tx12 = v.findViewById(R.id.tx_modfechaNac);
        rellenarCampos();
        b = v.findViewById(R.id.bt_modArt);
        c = v.findViewById(R.id.bt_volverArtMod);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        return v;
    }

    private void rellenarCampos() {
        tx1.setText(a.getDniPasaporte());
        tx1.setEnabled(false);
        tx2.setText(a.getNombre());
        tx3.setText(a.getDireccion());
        tx4.setText(a.getPoblacion());
        tx5.setText(a.getProvincia());
        tx6.setText(a.getPais());
        tx7.setText(a.getMovilTrabajo());
        tx8.setText(a.getMovilPersonal());
        tx9.setText(a.getTelefonoFijo());
        tx10.setText(a.getEmail());
        tx11.setText(a.getWebBlog());
        tx12.setText(a.getFechaNacimiento());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_modArt:
                boolean comprobarCampos = comprobarCampos(tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,tx12);
                boolean comprobarFecha=compruebaFecha(tx12.getText().toString());
                if(comprobarCampos && comprobarFecha){
                    Artistas modArt = new Artistas(tx1.getText().toString(),
                            tx2.getText().toString(),
                            tx3.getText().toString(),
                            tx4.getText().toString(),
                            tx5.getText().toString(),
                            tx6.getText().toString(),
                            tx7.getText().toString(),
                            tx8.getText().toString(),
                            tx9.getText().toString(),
                            tx10.getText().toString(),
                            tx11.getText().toString(),
                            tx12.getText().toString());
                    Funciones f = new Funciones(getContext());
                    if (f.modificarArtista(modArt) != -1) {
                        Toast.makeText(getActivity(), R.string.artisMod, Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).navigate(R.id.action_modificarArtistaFragment_to_nav_artistas);
                    } else {
                        Toast.makeText(getActivity(), R.string.errorMod, Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getActivity(), R.string.compruebeCampos, Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.bt_volverArtMod:
                Navigation.findNavController(v).navigate(R.id.action_modificarArtistaFragment_to_nav_artistas);
                break;
        }
    }

    private boolean compruebaFecha(String fecha) {
        boolean correcta = false;
        try {
            String[] fechaAux = fecha.split("/");
            int dia = Integer.parseInt(fechaAux[0]);
            int mes = Integer.parseInt(fechaAux[1]);
            if (dia >= 0 && dia <= 31 && mes >= 1 && mes <= 12 && fecha.length() == 10) {
                correcta = true;
            }
        } catch (NullPointerException e) {

        } catch (NumberFormatException e) {

        }
        return correcta;

    }
    private boolean comprobarCampos(EditText tx1, EditText tx2, EditText tx3, EditText tx4, EditText tx5, EditText tx6, EditText tx7, EditText tx8,EditText tx9,EditText tx10,EditText tx11,EditText tx12) {
        if (tx1.getText().toString().equals("")
                || tx2.getText().toString().equals("")
                || tx3.getText().toString().equals("")
                || tx4.getText().toString().equals("")
                || tx5.getText().toString().equals("")
                || tx6.getText().toString().equals("")
                || tx7.getText().toString().equals("")
                || tx8.getText().toString().equals("")
                || tx9.getText().toString().equals("")
                || tx10.getText().toString().equals("")
                || tx11.getText().toString().equals("")
                || tx12.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}