package org.example.bevasarlolista;

public class Termek {
    private String nev;
    private String azonosito;
    private String kiszereles;
    private int ar;

    public Termek(String sor) {
        String[] parts = sor.split(";");
        this.nev = parts[0];
        this.azonosito = parts[1];
        this.kiszereles = parts[2];
        this.ar = Integer.parseInt(parts[3]);
    }

    public String getNev() { return nev; }
    public String getAzonosito() { return azonosito; }
    public String getKiszereles() { return kiszereles; }
    public int getAr() { return ar; }

    @Override
    public String toString() {
        return nev + " – " + kiszereles + " – " + ar + " Ft";
    }
}
