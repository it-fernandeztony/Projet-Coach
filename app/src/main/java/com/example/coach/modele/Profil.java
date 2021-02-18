package com.example.coach.modele;

public class Profil {

    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;

    private Integer poids,taille,age,sexe;
    private Float img;
    private String message;

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        calculIMG();
        resultIMG();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Méthode pour calculer l'IMG
     */
    private void calculIMG() {
        Float tailleFloat = (float)taille/100;
        img = (float)1.2 * ((float)poids / (tailleFloat * tailleFloat)) + ((float)0.23 * (float)age) - ((float)10.83 * (float)sexe) - (float)5.4;

    }

    /**
     * Méthode pour affecter une valeur au message en fonction de l'IMG
     */
    private void resultIMG() {
        if (((img < minFemme) && (sexe == 0)) || ((img < minHomme) && (sexe == 1)))
            message = "trop faible";
        else
            if (((img > maxFemme)&&(sexe == 0)) || ((img > maxHomme) && (sexe == 1)))
                message = "trop élevé";
            else
                message = "normal";
    }
}
