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
import com.example.aplicacionfinalmovilesgestionsalas.R;


public class CrearArtista extends Fragment implements View.OnClickListener {
    private Funciones f;
    private EditText tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, tx9, tx10, tx11, tx12;
    Button b, c;
    private long resultado;
    private boolean comprobar;


    public CrearArtista() {
        // Required empty public constructor
    }


    public static CrearArtista newInstance(String param1, String param2) {
        CrearArtista fragment = new CrearArtista();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_artista, container, false);
        tx1 = v.findViewById(R.id.tx_adddniPasaporte);
        tx2 = v.findViewById(R.id.tx_addNombreArtista);
        tx3 = v.findViewById(R.id.tx_addDireccion);
        tx4 = v.findViewById(R.id.tx_addPoblacion);
        tx5 = v.findViewById(R.id.tx_addProvincia);
        tx6 = v.findViewById(R.id.tx_addPais);
        tx7 = v.findViewById(R.id.tx_addMovilTrabajo);
        tx8 = v.findViewById(R.id.tx_addMovilPersonal);
        tx9 = v.findViewById(R.id.tx_addTelfFijo);
        tx10 = v.findViewById(R.id.tx_addEmail);
        tx11 = v.findViewById(R.id.tx_addWebBlog);
        tx12 = v.findViewById(R.id.tx_addfechaNac);
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
                comprobar=comprobarCampos(tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,tx12);
                if(comprobar){
                    if (compruebaFecha(tx12.getText().toString())) {
                        Artistas a = new Artistas(tx1.getText().toString(),
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
                        f = new Funciones(getContext());
                        resultado = f.insertarArtista(a);
                        if (resultado != -1) {
                            tx1.setText("");
                            tx2.setText("");
                            tx3.setText("");
                            tx4.setText("");
                            tx5.setText("");
                            tx6.setText("");
                            tx7.setText("");
                            tx8.setText("");
                            tx9.setText("");
                            tx10.setText("");
                            tx11.setText("");
                            tx12.setText("");
                            Toast.makeText(getActivity(), R.string.artistAdd, Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getActivity(), R.string.errArtist, Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), R.string.errorsCampos, Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getActivity(), R.string.rellenCampos, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bt_volverArtAdd:
                Navigation.findNavController(v).navigate(R.id.action_crearArtista_to_nav_artistas);
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

        } catch (NumberFormatException e){

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