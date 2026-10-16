package com.example.seminar02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NouaActivitate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_noua_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (y, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            y.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button buton = findViewById(R.id.button);
        buton.setOnClickListener(v -> {
            Intent it = new Intent(NouaActivitate.this, ActivitateRezultat.class);
            Bundle bundle=new Bundle();
            bundle.putString("mesaj", "Mesaj din Activitate NOUA.");
            bundle.putInt("numar1", 5);
            bundle.putInt("numar2", 7);
            it.putExtras(bundle);



            startActivityForResult(it,83);
        });
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==83){
            if(resultCode==RESULT_OK){
                String mesajIntors=data.getStringExtra("mesaj");
                int suma=data.getIntExtra("suma", 0);
                Toast.makeText(this, mesajIntors+" "+suma, Toast.LENGTH_SHORT).show();
            }
        }
    }
}