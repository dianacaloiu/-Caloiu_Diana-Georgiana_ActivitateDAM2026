package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ActivitateLista extends AppCompatActivity {
    public static final String Lista = "lista";
    private ArrayList<Medicament> lista = new ArrayList<>();
    private MedicamentAdapter adapter = null;

    private final ActivityResultLauncher<Intent> launcherEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle b = result.getData().getExtras();
                    if (b == null) return;

                    Medicament nou = (Medicament) b.getParcelable(ActivitateMedicament.KEY_MEDICAMENT);
                    int poz = b.getInt(ActivitateMedicament.KEY_POZITIE, -1);

                    if (nou != null && poz >= 0 && poz < lista.size()) {
                        Medicament existent = lista.get(poz);
                        existent.setDenumire(nou.getDenumire());
                        existent.setPret(nou.getPret());
                        existent.setCuReteta(nou.isCuReteta());
                        existent.setTipUtilizare(nou.getTipUtilizare());
                        existent.setDisponibil(nou.isDisponibil());

                        adapter.notifyDataSetChanged();
                        trimiteLista();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_lista);

        ListView lv = findViewById(R.id.lvMedicamente);
        TextView tvGol = findViewById(R.id.tvGol);
        lv.setEmptyView(tvGol);

        ArrayList<Medicament> primita = getIntent().getParcelableArrayListExtra(Lista);
        if (primita != null) {
            lista = primita;
        }

        adapter = new MedicamentAdapter(lista, this, R.layout.item_medicament);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, ActivitateMedicament.class);
            intent.putExtra(ActivitateMedicament.KEY_MEDICAMENT, lista.get(position));
            intent.putExtra(ActivitateMedicament.KEY_POZITIE, position);
            launcherEdit.launch(intent);
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);
            adapter.notifyDataSetChanged();
            trimiteLista();
            return true;
        });
    }


    private void trimiteLista() {
        Intent rezultat = new Intent();
        rezultat.putParcelableArrayListExtra(Lista, lista);
        setResult(RESULT_OK, rezultat);
    }
}