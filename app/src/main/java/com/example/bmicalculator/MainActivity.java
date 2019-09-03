package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtPoids;
    EditText txtTaille;
    EditText txtAge;
    TextView afficheIbm;
    RadioButton homme;
    int max = 30;
    int min = 15;
    String textBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPoids = findViewById(R.id.poidsTxt);
        txtTaille = findViewById(R.id.tailleTxt);
        txtAge = findViewById(R.id.ageTxt);
        afficheIbm = findViewById(R.id.bmiTxt);
        homme = findViewById(R.id.hommeBtn);
    }

    public void calcul(View view) {
        if(txtPoids.getText().toString().matches("") || txtTaille.getText().toString().matches("")
                || txtAge.getText().toString().matches("")){
            Toast.makeText(this, "Veuillez remplir tout les champs.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        int s = 0;
        if(homme.isChecked()){
            max = 25;
            min = 10;
            s = 1;
        }


        int poids = Integer.parseInt(txtPoids.getText().toString());
        double taille = Double.parseDouble(txtTaille.getText().toString()) / 100;
        int age = Integer.parseInt(txtAge.getText().toString());

        double bmi = Math.round((1.2 * poids /(taille * taille)) + (0.23 * age) - (10.83 * s) - 5.4);


        if(bmi < min)
            textBmi = "Trop maigre";
        else if (bmi > max)
            textBmi = "Surpoids";
        else
            textBmi = "Poids normal";

        afficheIbm.setText(textBmi + "\n Votre IMC:" + bmi);

    }
}
