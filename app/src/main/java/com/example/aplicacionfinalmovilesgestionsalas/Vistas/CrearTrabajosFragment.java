package com.example.aplicacionfinalmovilesgestionsalas.Vistas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.Funciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;
import com.example.aplicacionfinalmovilesgestionsalas.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class CrearTrabajosFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Funciones f;
    private Spinner sp;
    private List<String> listaDniPasaporte;
    private ArrayAdapter<String> comboAdapterSql;
    private ArrayList<Artistas> listaArtistas;
    private String dniArtista;
    private Button fotoCamara, fotoGaleria, guardar, back,add;
    private EditText nombreFoto, tx1, tx2, tx3, tx4;
    private ImageView iv;
    private Uri uriFoto;
    private OutputStream os;
    private BufferedOutputStream bos;
    private File path;
    private File fich_salida;
    private String nombreFichero;
    private final int ACT_GALERIA = 1;
    private final int ACT_CAMARA = 2;
    private boolean guardada = false, comprobar = false;
    private Bitmap bm;


    public CrearTrabajosFragment() {
    }


    public static CrearTrabajosFragment newInstance(String param1, String param2) {
        CrearTrabajosFragment fragment = new CrearTrabajosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_trabajos, container, false);
        f = new Funciones(getContext());
        sp = v.findViewById(R.id.sp_artistas);
        sp.setOnItemSelectedListener(this);
        listaDniPasaporte = new ArrayList<>();
        listaArtistas = f.listarArtistas();
        for (Iterator it = listaArtistas.iterator(); it.hasNext(); ) {
            Artistas a = (Artistas) it.next();
            listaDniPasaporte.add(a.getDniPasaporte());
        }
        comboAdapterSql = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaDniPasaporte);
        sp.setAdapter(comboAdapterSql);
        fotoGaleria = v.findViewById(R.id.bt_addImagenGalery);
        fotoCamara = v.findViewById(R.id.bt_addImagenCamara);
        guardar = v.findViewById(R.id.bt_guardarFoto);
        nombreFoto = v.findViewById(R.id.tx_nombreFoto);
        iv = v.findViewById(R.id.iv_imagenTrabajo);
        iv.setMaxWidth(300);
        iv.setMaxHeight(300);
        fotoGaleria.setOnClickListener(this);
        fotoCamara.setOnClickListener(this);
        guardar.setOnClickListener(this);
        tx1 = v.findViewById(R.id.tx_addnombreTrabajo);
        tx2 = v.findViewById(R.id.tx_addDescripcionTrab);
        tx3 = v.findViewById(R.id.tx_addTamanio);
        tx4 = v.findViewById(R.id.tx_addPeso);
        add=v.findViewById(R.id.bt_crearTrab);
        back = v.findViewById(R.id.bt_volverTrabAdd);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_artistas:
                dniArtista = listaDniPasaporte.get(position);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_addImagenGalery:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACT_GALERIA);
                break;
            case R.id.bt_addImagenCamara:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, ACT_CAMARA);
                break;
            case R.id.bt_guardarFoto:
                try {
                    nombreFichero = nombreFoto.getText().toString();
                    if (nombreFichero.equals("")) {
                        Toast.makeText(getContext(), R.string.nombreImagen, Toast.LENGTH_LONG).show();
                    } else {
                        if (iv.getDrawable() == null) {

                        } else {
                            path = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                            fich_salida = new File(path + nombreFichero + ".jpg");
                            try {
                                FileOutputStream os = new FileOutputStream(fich_salida);
                                BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
                                Bitmap bitmap = drawable.getBitmap();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
                                os.flush();
                                os.close();
                                guardada = true;
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(getContext(), R.string.imagenNoCompatible, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bt_crearTrab:

                comprobar = comprobarCampos(tx1, tx2, tx3, tx4);
                if (guardada && comprobar) {
                    Trabajos t = new Trabajos(
                            tx1.getText().toString(),
                            tx2.getText().toString(),
                            tx3.getText().toString(),
                            tx4.getText().toString(),
                            dniArtista, "Pictures" + nombreFoto.getText().toString() + ".jpg");
                    f = new Funciones(getContext());
                    if (f.insertarTrabajo(t) != -1) {
                        Toast.makeText(getContext(), R.string.trabajoadd, Toast.LENGTH_LONG).show();
                        tx1.setText("");
                        tx2.setText("");
                        tx3.setText("");
                        tx4.setText("");
                        nombreFoto.setText("");

                    } else {
                        Toast.makeText(getContext(), R.string.erroCrearTrab, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.guardeImagen, Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.bt_volverTrabAdd:
                Navigation.findNavController(v).navigate(R.id.action_crearTrabajosFragment_to_nav_trabajos);

                break;


        }
    }

    private boolean comprobarCampos(EditText tx1, EditText tx2, EditText tx3, EditText tx4) {
        if (tx1.getText().toString().equals("") || tx2.getText().toString().equals("") || tx3.getText().toString().equals("") || tx4.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACT_GALERIA && resultCode == RESULT_OK) {
            uriFoto = data.getData();
            Toast.makeText(getContext(), data.getData().toString(), Toast.LENGTH_SHORT).show();
            iv.setImageURI(uriFoto);
        }
        if (requestCode == ACT_CAMARA && resultCode == RESULT_OK) {
            bm = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(bm);

        }
    }
}