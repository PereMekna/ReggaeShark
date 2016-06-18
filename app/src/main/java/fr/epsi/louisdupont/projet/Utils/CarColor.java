package fr.epsi.louisdupont.projet.Utils;

/**
 * Created by Loulou on 14/06/2016.
 */
public enum CarColor {
    Blue ("blue"),
    Red ("red"),
    Green ("green");

    private String name = "";

    //Constructeur
    CarColor(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
