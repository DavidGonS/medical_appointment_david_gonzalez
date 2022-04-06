    package com.example.medical_appointment_david_gonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

    private EditText etDay, etHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        etDay = findViewById(R.id.etDia);
        etHour = findViewById(R.id.etHora);
    }

    public void checkAndSend(View view) {
        if (etDay.getText().toString().isEmpty() || etHour.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes introducir los dos campos", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences pref = getSharedPreferences("cita", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            editor.putString("day", etDay.getText().toString());
            editor.putString("hour", etHour.getText().toString());

            editor.commit();

            Intent secondActivity = new Intent(this, Formulario.class);
            startActivity(secondActivity);
        }
    }
}