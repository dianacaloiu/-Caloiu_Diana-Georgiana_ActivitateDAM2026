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
    public static final String KEY_POZITIE = "pozitie";

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
        RadioButton rbExtern = findViewById(R.id.rbExtern);
        Spinner spForma = findViewById(R.id.spForma);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        CompoundButton swDisponibil = findViewById(R.id.swDisponibil);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        Bundle extras = getIntent().getExtras();
        final Medicament primit = extras != null ? (Medicament) extras.getParcelable(KEY_MEDICAMENT) : null;
        final int pozitie = extras != null ? extras.getInt(KEY_POZITIE, -1) : -1;
        final boolean modificare = primit != null && pozitie >= 0;

        if (modificare) {
            etDenumire.setText(primit.getDenumire());
            etPret.setText(String.valueOf(primit.getPret()));
            cbReteta.setChecked(primit.isCuReteta());
            swDisponibil.setChecked(primit.isDisponibil());

            String tip = primit.getTipUtilizare();
            if (tip.startsWith("Uz extern")) {
                rbExtern.setChecked(true);
            } else {
                rbIntern.setChecked(true);
            }
            for (int i = 0; i < spForma.getCount(); i++) {
                if (tip.contains(spForma.getItemAtPosition(i).toString())) {
                    spForma.setSelection(i);
                    break;
                }
            }
            btnSalveaza.setText("Salvează modificările");
        }

        btnSalveaza.setOnClickListener(v -> {
            String denumire = etDenumire.getText().toString().trim();
            String pretText = etPret.getText().toString().trim();

            if (denumire.isEmpty() || pretText.isEmpty()) {
                Toast.makeText(this, "Completeaza denumire si pret", Toast.LENGTH_SHORT).show();
                return;
            }

            String utilizare = (rbIntern.isChecked() ? "Uz intern" : "Uz extern")
                    + " - " + spForma.getSelectedItem().toString();

            Medicament m = new Medicament(cbReteta.isChecked(), denumire,
                    Double.parseDouble(pretText), utilizare, swDisponibil.isChecked());

            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_MEDICAMENT, m);
            bundle.putFloat(KEY_RATING, ratingBar.getRating());
            if (modificare) {
                bundle.putInt(KEY_POZITIE, pozitie);
            }

            Intent rezultat = new Intent();
            rezultat.putExtras(bundle);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}