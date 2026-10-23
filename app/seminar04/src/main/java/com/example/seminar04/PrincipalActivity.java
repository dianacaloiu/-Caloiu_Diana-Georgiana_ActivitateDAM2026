package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {
    private TextView tvRezultat;
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle == null) return;

                    Medicament m = (Medicament) bundle.getSerializable(ActivitateMedicament.KEY_MEDICAMENT);
                    float rating = bundle.getFloat(ActivitateMedicament.KEY_RATING, 0);

                    if (m != null) {
                        Toast.makeText(this, "Adăugat: " + m.getDenumire(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tvRezultat = findViewById(R.id.tvRezultat);
        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaAI);
        btnAdauga.setOnClickListener(v -> launcher.launch(new Intent(this, ActivitateMedicament.class)));
        btnAdaugaAI.setOnClickListener(v -> launcher.launch(new Intent(this, ActivitateMedicamentAi.class)));
    }
}