package com.example.medical_appointment_david_gonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Formulario extends AppCompatActivity {
    public static final String OPTION = "com.example.medical_appointment_david_gonzalez.option";

    private ListView lvItems;
    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;
    private String itemSelected;
    private EditText etModality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        initialize();
        createAdapter();
    }

    public void initialize() {
        lvItems = findViewById(R.id.lvItems);
        etModality = findViewById(R.id.etModalidad);

        listItems = new ArrayList<>();
        listItems.add("Presencial");
        listItems.add("Telefonica");
        listItems.add("Urgencia");
        listItems.add("Partes de baja/alta");

        registerForContextMenu(lvItems);
    }

    public void createAdapter() {
        adapter = new ArrayAdapter<String>(this, R.layout.list_view, R.id.tvItem, listItems);
        lvItems.setAdapter(adapter);

        Intent thirdActivity = new Intent(this, BaseDatos.class);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                itemSelected = parent.getItemAtPosition(i).toString();
                thirdActivity.putExtra(itemSelected, OPTION);
                startActivity(thirdActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, 1, Menu.NONE, "Añadir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                if (etModality.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Introduce la modalidad", Toast.LENGTH_SHORT).show();
                } else {
                    this.listItems.add(etModality.getText().toString());
                    this.adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        menu.setHeaderTitle("Opciones: ");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 1:
                this.listItems.remove(info.position);
                this.adapter.notifyDataSetChanged();

                Toast.makeText(this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}