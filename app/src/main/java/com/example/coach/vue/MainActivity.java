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
    private RadioButton rdHomme, rdFemme;
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
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);

        controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();
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

                if (rdHomme.isChecked()) {
                    sexe = 1;
                }
                if ((poids == 0) || (taille == 0) || (age == 0)) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    affichResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Méthode qui valorise les éléments graphiques.
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 0 pour femme, 1 pour homme
     */
    private void affichResult(int poids, int taille, int age, int sexe) {
        Controle.creerProfil(poids,taille,age,sexe,this);
        Float img = controle.getImg();
        String message = controle.getMessage();

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

    /**
     * Méthode pour récupérer les données de la dernière session
     */
    private void recupProfil() {
        if (controle.getTaille() != null) {
            txtPoids.setText("" + controle.getPoids());
            txtTaille.setText("" + controle.getTaille());
            txtAge.setText("" + controle.getAge());
            switch (controle.getSexe().toString()) {
                case "0":
                    rdFemme.setChecked(true);
                    rdHomme.setChecked(false);
                    break;
                case "1":
                    rdHomme.setChecked(true);
                    rdFemme.setChecked(false);
                    break;
            }
            btnCalc.performClick();
        }
    }
}