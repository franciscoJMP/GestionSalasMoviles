package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.util.ArrayList;
import java.util.Iterator;


public class CrearComentariosFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Exposiciones e;
    private ArrayList<Trabajos> listaTrabajos = new ArrayList<>();
    private EditText tx1, tx2;
    private Button b,volver;
    private Spinner sp;
    private ArrayAdapter<String> comboAdapterSql;
    private ArrayList<String> listaNombreTrabajos = new ArrayList<>();
    private String trabajos;
    private Funciones f;


    public CrearComentariosFragment() {
        // Required empty public constructor
    }


    public static CrearComentariosFragment newInstance(String param1, String param2) {
        CrearComentariosFragment fragment = new CrearComentariosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            e = (Exposiciones) getArguments().getSerializable("e");
            listaTrabajos = (ArrayList) getArguments().getSerializable("listaTrabajos");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_comentarios, container, false);
        sp = v.findViewById(R.id.sp_trabajos);
        sp.setOnItemSelectedListener(this);
        for (Iterator it = listaTrabajos.iterator(); it.hasNext(); ) {
            Trabajos t = (Trabajos) it.next();
            listaNombreTrabajos.add(t.getNombreTrabajo());
        }
        comboAdapterSql = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaNombreTrabajos);
        sp.setAdapter(comboAdapterSql);
        tx1 = v.findViewById(R.id.tx_addIdExposicionComent);
        tx2 = v.findViewById(R.id.tx_modComent);
        b = v.findViewById(R.id.bt_modComentario);
        volver=v.findViewById(R.id.bt_volverComentAdd);
        tx1.setEnabled(false);
        tx1.setText(String.valueOf(e.getIdExposicion()));
        b.setOnClickListener(this);
        volver.setOnClickListener(this);

        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_trabajos:
                trabajos = listaNombreTrabajos.get(position);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_modComentario:
                boolean comprobarCampos = comprobarCampos(tx1, tx2);
                if (comprobarCampos) {
                    Comentarios c = new Comentarios(Integer.parseInt(tx1.getText().toString()), trabajos, tx2.getText().toString());
                    f = new Funciones(getContext());
                    if (f.insertarComentario(c) != -1) {
                        Toast.makeText(getContext(), R.string.comentAdd, Toast.LENGTH_LONG).show();
                        tx2.setText("");
                    } else {
                        Toast.makeText(getContext(), R.string.errComent, Toast.LENGTH_LONG).show();
                    }

                } else{
                    Toast.makeText(getContext(), R.string.rellenCampos, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bt_volverComentAdd:
                Bundle b = new Bundle();
                b.putSerializable("e",e);
                b.putSerializable("listaTrabajos",listaTrabajos);
                Navigation.findNavController(v).navigate(R.id.action_crearComentariosFragment_to_comentariosFragment,b);
                break;
        }
    }

    private boolean comprobarCampos(EditText tx1, EditText tx2) {
        if (tx1.getText().toString().equals("") || tx2.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}