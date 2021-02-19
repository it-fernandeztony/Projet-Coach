package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";

    private Controle() {
        super();
    }

    public static final Controle getInstance(Context contexte) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            recupSerialize(contexte);
        }
        return instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public static void creerProfil(int poids, int taille, int age, int sexe, Context context) {
        profil = new Profil(poids,taille,age,sexe);
        Serializer.serialize(nomFic,profil,context);
    }

    /**
     * Méthode qui retourne l'IMG récupéré dans profil
     * @return Float
     */
    public static Float getImg() {
        return profil.getImg();
    }

    /**
     * Méthode qui retourne le message récupéré dans profil
     * @return String
     */
    public static String getMessage() {
        return profil.getMessage();
    }

    /**
     * Méthode qui valorise la propriété profil avec les information récupéré dans le fichier de
     * sauvegarde
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil = (Profil)Serializer.deSerialize(nomFic,contexte);
    }

    /**
     * Méthode qui retourne le poids récupéré dans profil
     * @return Integer ou null
     */
    public static Integer getPoids() {
        if (profil != null) {
            return profil.getPoids();
        }
        else {
            return null;
        }
    }

    /**
     * Méthode qui retourne la taille récupéré dans profil
     * @return Integer ou null
     */
    public static Integer getTaille() {
        if (profil != null) {
            return profil.getTaille();
        }
        else {
            return null;
        }
    }

    /**
     * Méthode qui retourne l'âge récupéré dans profil
     * @return Integer ou null
     */
    public static Integer getAge() {
        if (profil != null) {
            return profil.getAge();
        }
        else {
            return null;
        }
    }

    /**
     * Méthode qui retourne le sexe récupéré dans profil
     * @return Integer ou null
     */
    public static Integer getSexe() {
        if (profil != null) {
            return profil.getSexe();
        }
        else {
            return null;
        }
    }
}
