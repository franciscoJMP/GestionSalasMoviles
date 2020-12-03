package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;

import com.example.aplicacionfinalmovilesgestionsalas.R;




public class FragmentEmail extends Fragment implements View.OnClickListener {
    private Artistas a;
    private EditText tx1,tx2,tx3;
    private Button b;


    public FragmentEmail() {
        // Required empty public constructor
    }


    public static FragmentEmail newInstance(String param1, String param2) {
        FragmentEmail fragment = new FragmentEmail();

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
        View v = inflater.inflate(R.layout.fragment_email, container, false);
        tx1 = v.findViewById(R.id.tx_emailArtista2);
        tx2=v.findViewById(R.id.tx_mensaje1);
        tx3=v.findViewById(R.id.tx_mensaje2);
        b = v.findViewById(R.id.bt_mandarEmail);
        tx1.setText(a.getEmail());
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_mandarEmail:
                String correo=tx1.getText().toString();
                String [] arrayEmail=correo.split(",");
                String titulo=tx2.getText().toString();
                String mensaje=tx3.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,arrayEmail);
                intent.putExtra(Intent.EXTRA_SUBJECT,titulo);
                intent.putExtra(Intent.EXTRA_TEXT,mensaje);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, getContext().getString(R.string.eligeClientEmail)));
                break;
        }
    }
}