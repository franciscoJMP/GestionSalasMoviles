package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.R;


public class LlamarFragment extends Fragment implements View.OnClickListener {
    private Artistas a;
    private RadioGroup rg;

    private Button b;


    public LlamarFragment() {
        // Required empty public constructor
    }



    public static LlamarFragment newInstance(String param1, String param2) {
        LlamarFragment fragment = new LlamarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            a=(Artistas) getArguments().getSerializable("a");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_llamar, container, false);
        rg=view.findViewById(R.id.rg_llamar);
        b=view.findViewById(R.id.bt_llamarArtista);
        b.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_llamarArtista:
                String numero="";
                int checked=rg.getCheckedRadioButtonId();
                switch (checked){
                    case R.id.rb_fijo:
                        numero=a.getTelefonoFijo();
                        break;
                    case R.id.rb_personal:
                        numero=a.getMovilPersonal();
                        break;
                    case R.id.rb_trabajo:
                        numero=a.getMovilTrabajo();
                        break;
                }
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+numero));
                startActivity(intent);

                break;
        }
    }
}