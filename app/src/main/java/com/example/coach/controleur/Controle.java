package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private Controle() {
        super();
    }

    public static final Controle getInstance() {
        if (Controle.instance == null)
            Controle.instance = new Controle();

        return instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public static void creerProfil(int poids, int taille, int age, int sexe) {
        profil = new Profil(poids,taille,age,sexe);
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
}
