package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private final ArrayList<Medicament> lista = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle == null) return;

                    Medicament m = (Medicament) bundle.getParcelable(ActivitateMedicament.KEY_MEDICAMENT);
                    if (m != null) {
                        lista.add(m);
                        actualizeazaText();
                        Toast.makeText(this, "Adăugat: " + m.getDenumire(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    // Rezultatul de la ActivitateLista (lista după modificări și ștergeri)
    private final ActivityResultLauncher<Intent> launcherLista = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Medicament> actualizata =
                            result.getData().getParcelableArrayListExtra(ActivitateLista.Lista);
                    if (actualizata != null) {
                        lista.clear();
                        lista.addAll(actualizata);
                        actualizeazaText();
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
        Button btnLista = findViewById(R.id.btnLista);

        btnAdauga.setOnClickListener(v ->
                launcher.launch(new Intent(this, ActivitateMedicament.class)));
        btnAdaugaAI.setOnClickListener(v ->
                launcher.launch(new Intent(this, ActivitateMedicamentAi.class)));

        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivitateLista.class);
            intent.putParcelableArrayListExtra(ActivitateLista.Lista, lista);
            launcherLista.launch(intent);
        });
    }

    private void actualizeazaText() {
        tvRezultat.setText("Medicamente în listă: " + lista.size());
    }
}