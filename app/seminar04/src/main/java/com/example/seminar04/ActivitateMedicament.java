package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitateMedicament extends AppCompatActivity {

    public static final String KEY_MEDICAMENT = "medicament";
    public static final String KEY_RATING = "rating";
    protected int layoutId() {
        return R.layout.activity_activitate_medicament;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etPret = findViewById(R.id.etPret);
        CheckBox cbReteta = findViewById(R.id.cbReteta);
        RadioButton rbIntern = findViewById(R.id.rbIntern);
        Spinner spForma = findViewById(R.id.spForma);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        CompoundButton swDisponibil = findViewById(R.id.swDisponibil);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(v -> {
            String denumire = etDenumire.getText().toString().trim();
            String pretText = etPret.getText().toString().trim();

            if (denumire.isEmpty() || pretText.isEmpty()) {
                Toast.makeText(this, "Completeaza denumire si pret", Toast.LENGTH_SHORT).show();
                return;
            }

            String utilizare = (rbIntern.isChecked() ? "Uz intern" : "Uz extern") + spForma.getSelectedItem().toString();

            Medicament m = new Medicament(cbReteta.isChecked(), denumire, Double.parseDouble(pretText), utilizare, swDisponibil.isChecked());

            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_MEDICAMENT, m);
            bundle.putFloat(KEY_RATING, ratingBar.getRating());

            Intent rezultat = new Intent();
            rezultat.putExtras(bundle);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}