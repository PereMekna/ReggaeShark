package fr.epsi.louisdupont.projet.Bean;

/**
 * Created by Loulou on 10/06/2016.
 */
public class User {
    private String name, firstName;

    public User() {
    }

    public User(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
