package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.R;

public class Preferencias extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias_usuario);

    }
}
