package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;


public class MainActivity extends AppCompatActivity {
    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Méthode pour initialisé les propriétés avec les valeurs récupérés côté graphique
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmyley);
        btnCalc = (Button) findViewById(R.id.btnCalc);

        controle = Controle.getInstance();
        ecouteCalcul();
    }

    /**
     * Méthode pour l'événement clique sur le boutton Calculer
     */
    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0, taille = 0, age = 0, sexe = 0;


                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}

                if (rdHomme.isChecked())
                    sexe = 1;

                if ((poids == 0) || (taille == 0) || (age == 0))
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                else
                    affichResult(poids, taille, age, sexe);
            }
        });
    }

    private void affichResult(int poids, int taille, int age, int sexe) {
        Controle.creerProfil(poids,taille,age,sexe);
        Float img = Controle.getImg();
        String message = Controle.getMessage();

        switch (message) {
            case "trop faible":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(Color.RED);
                break;
            case "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setTextColor(Color.GREEN);
                break;
            case "trop élevé":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
                break;
        }
        lblIMG.setText(String.format("%.01f",img) + " " + message);
    }
}