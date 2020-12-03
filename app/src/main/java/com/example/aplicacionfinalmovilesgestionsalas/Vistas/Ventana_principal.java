package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.R;


public class Ventana_principal extends Fragment implements View.OnClickListener {
    EditText e1,e2;
    public Ventana_principal() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ventana_principal, container, false);
        Button button = (Button)view.findViewById(R.id.bt_comprobarDatos);
        e1= view.findViewById(R.id.tx_user);
        e2 = view.findViewById(R.id.tx_password);
        button.setOnClickListener(this);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_comprobarDatos:
                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
                String user=prefs.getString("user","admin");
                String pass=prefs.getString("password","admin");

                String userComprobar=e1.getText().toString();
                String passComprobar=e2.getText().toString();
                if(user.equals(userComprobar) && pass.equals(passComprobar)){
                    Navigation.findNavController(v).navigate(R.id.action_ventana_principal_to_nav_expo);
                } else {
                    Toast.makeText(getActivity(),R.string.incorrecto,Toast.LENGTH_LONG).show();
                }



                break;
        }
    }


}