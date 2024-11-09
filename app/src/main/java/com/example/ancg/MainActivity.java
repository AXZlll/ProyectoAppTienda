package com.example.ancg;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    EditText ingresarRopa, editarRopa;
    Button btnAgregar, btnEliminar, btnEditar;
    ListView listarRopas;
    ArrayList<String> ropas;
    ArrayAdapter<String> adapter;
    String seleccionarRopas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        database = new Database(this);
        ingresarRopas = findViewById(R.id.editTextClothes);
        editarRopas = findViewById(R.id.editTextEditClothes);
        btnAgregar = findViewById(R.id.buttonAdd);
        btnEliminar = findViewById(R.id.buttonDelete);
        btnEditar = findViewById(R.id.buttonEdit);
        listarRopas = findViewById(R.id.listViewClothes);

        ropas = database.getAllClothes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listarRopas.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ropas = ingresarRopas.getText().toString();
                if (!ropas.isEmpty()) {
                    database.InsertClothes(ropas);
                    actualizarListadoRopas();
                    ingresarRopas.setText("");
                }
            }
        });

    }

    public void actualizarListadoRopas() {
        ropas.clear();
        ropas.addAll(database.getAllClothes());
        adapter.notifyDataSetChanged();
    }
}