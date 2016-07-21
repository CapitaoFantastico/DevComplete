package br.com.devcomplete.entity;

/**
 * Created by ricks on 20/07/2016.
 */
public class Person {

    private String name;

    private String email;

    private String bio;

    private char gender;

    private int codOccupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getCodOccupation() {
        return codOccupation;
    }

    public void setCodOccupation(int codOccupation) {
        this.codOccupation = codOccupation;
    }
}
