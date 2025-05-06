package com.example.telefonbuch;

public class TelefonEntry {
    private String lastName;

    private String firstName;
    private String number;
    public TelefonEntry(String lastName, String firstName, String number) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean match(String str) {
        String strLower = str.toLowerCase();
        return this.getFirstName().toLowerCase().contains(strLower) ||
                this.getLastName().toLowerCase().contains(strLower) ||
                this.getNumber().toLowerCase().contains(strLower);
    }
}
