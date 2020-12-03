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
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.R;


public class CrearExposicion extends Fragment implements View.OnClickListener {
    private Funciones f;
    private EditText tx1, tx2, tx3, tx4, tx5;
    Button b, c;
    private long resultado;
    private boolean comprobar = false;

    public CrearExposicion() {
        // Required empty public constructor
    }


    public static CrearExposicion newInstance(String param1, String param2) {
        CrearExposicion fragment = new CrearExposicion();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_exposicion, container, false);

        tx1 = v.findViewById(R.id.tx_addIdExposicionComent);
        tx2 = v.findViewById(R.id.tx_addNombreExpo);
        tx3 = v.findViewById(R.id.tx_addDescExposicion);
        tx4 = v.findViewById(R.id.tx_fecInicioExpo);
        tx5 = v.findViewById(R.id.tx_fecFinExpo);

        b = v.findViewById(R.id.bt_crearArt);
        c = v.findViewById(R.id.bt_volverArtAdd);
        b.setOnClickListener(this);
        c.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_crearArt:
                comprobar = comprobarCampos(tx1, tx2, tx3, tx4, tx5);
                try {
                    Exposiciones e = new Exposiciones(Integer.parseInt(tx1.getText().toString()), tx2.getText().toString(), tx3.getText().toString(), tx4.getText().toString(), tx5.getText().toString());
                    f = new Funciones(getContext());

                    if (comprobar && compruebaFecha(tx4.getText().toString()) && compruebaFecha(tx5.getText().toString())) {
                        resultado = f.insertarExposicion(e);
                        if (resultado != -1) {
                            tx1.setText("");
                            tx2.setText("");
                            tx3.setText("");
                            tx4.setText("");
                            tx5.setText("");
                            Toast.makeText(getActivity(), R.string.exitoExpo, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), R.string.errorAddExpo, Toast.LENGTH_LONG).show();
                        }
                    } else{
                        Toast.makeText(getActivity(), R.string.compruebeCampos3, Toast.LENGTH_LONG).show();
                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), R.string.compruebeCampos2, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bt_volverArtAdd:
                Navigation.findNavController(v).navigate(R.id.action_crearExposicion_to_nav_expo);
                break;

        }
    }

    private boolean comprobarCampos(EditText tx1, EditText tx2, EditText tx3, EditText tx4, EditText tx5) {
        if (tx1.getText().toString().equals("") || tx2.getText().toString().equals("") || tx3.getText().toString().equals("") || tx4.getText().toString().equals("") || tx5.getText().toString().equals("")) {
            return false;
        } else {
            return true;
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

        } catch (NumberFormatException e){

        } catch (Exception e){

        }
        return correcta;

    }
}