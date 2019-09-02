package com.example.musta.catchkennymultiple;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    String userName;
    EditText nameText;
    TextView rekor;
    SharedPreferences sharedPreferences;
    Integer storedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        nameText = findViewById(R.id.nameText);
        rekor = findViewById(R.id.rekor);

        sharedPreferences = this.getSharedPreferences("com.example.musta.catchkennymultiple", Context.MODE_PRIVATE);

        storedScore = sharedPreferences.getInt("storedScore",0);

        rekor.setText("En Yüksek:" + storedScore);

        userName = "";
    }

    public void oyunBasla(View view) {

        userName = nameText.getText().toString();

        Intent intent = new Intent(WelcomePage.this,GameScreen.class);

        intent.putExtra("userNameInput",userName);

        startActivity(intent);

    }

    public void skorSifirla (View view){

         sharedPreferences.edit().remove("storedScore").apply();
         rekor.setText("Rekor Sıfırlandı");


    }
}
