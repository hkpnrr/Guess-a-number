package com.example.sayitahminoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TahminActivity extends AppCompatActivity {
    private TextView textViewKalanHak, textViewYardim;
    private Button buttonTahmin;
    private EditText editTextGirdi;

    private int randomNumber,live=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahmin);

        textViewKalanHak = findViewById(R.id.textViewKalanHak);
        textViewYardim = findViewById(R.id.textViewYardim);
        buttonTahmin = findViewById(R.id.buttonTahmin);
        editTextGirdi = findViewById(R.id.editTextGirdi);

        Random rnd = new Random();

        randomNumber = rnd.nextInt(101);
        Log.e("Random Number: ",String.valueOf(randomNumber));



        buttonTahmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(live>1 && Integer.parseInt(String.valueOf(editTextGirdi.getText()))!=randomNumber){

                    live--;
                    textViewKalanHak.setText("LIVE: "+live);
                    if(Integer.parseInt(editTextGirdi.getText().toString())>randomNumber){
                        textViewYardim.setText("GUESS LOWER");
                    }
                    else
                        textViewYardim.setText("GUESS HIGHER");

                }
                else if(live!=0 && Integer.parseInt(String.valueOf(editTextGirdi.getText()))==randomNumber){

                    Intent kazanmaIntent = new Intent(TahminActivity.this,SonucActivity.class);
                    kazanmaIntent.putExtra("sonuc",true);
                    startActivity(kazanmaIntent);

                }
                else{
                    Intent kaybetmeIntent = new Intent(TahminActivity.this,SonucActivity.class);
                    kaybetmeIntent.putExtra("sonuc",false);
                    startActivity(kaybetmeIntent);
                }
                if(live == 1)
                    textViewKalanHak.setText("ONE LIVE LEFT "+getIntent().getStringExtra("isim"));

            }
        });
    }
}