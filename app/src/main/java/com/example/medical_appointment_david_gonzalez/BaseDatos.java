package com.example.medical_appointment_david_gonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import java.security.PublicKey;

public class BaseDatos extends AppCompatActivity {
    private TextView tvDay, tvHour, tvModality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);
        initialize();
        createDataBase();
    }

    public void initialize() {
        tvDay = findViewById(R.id.tvMuestraDia);
        tvHour = findViewById(R.id.tvMuestraHora);
        tvModality = findViewById(R.id.tvMuestraModalidad);
    }

    public void createDataBase(){
        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        SharedPreferences pref = getSharedPreferences("cita", Context.MODE_PRIVATE);

        Intent intent = getIntent();

        String day = pref.getString("day", "defValue");
        String hour = pref.getString("hour", "defValue");
        String modality = intent.getStringExtra(Formulario.OPTION);

        tvDay.setText(day);
        tvHour.setText(hour);
        tvModality.setText(modality);

        ContentValues values = new ContentValues();

        values.put("day", day);
        values.put("hour", hour);
        values.put("modality", modality);
        database.insert("formularios", null, values);
        database.close();

    }
}