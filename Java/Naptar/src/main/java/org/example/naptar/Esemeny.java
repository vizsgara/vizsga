package org.example.naptar;

import java.time.LocalDate;
import java.time.LocalTime;

public class Esemeny {
    private String cim;
    private LocalDate datum;
    private LocalTime ido;
    private String megjegyzes;

    public Esemeny(String sor) {
        String[] parts = sor.split(";");
        this.cim = parts[0];
        this.datum = LocalDate.parse(parts[1]);
        this.ido = LocalTime.parse(parts[2]);
        this.megjegyzes = parts[3];
    }

    public Esemeny(String cim, LocalDate datum, LocalTime ido, String megjegyzes) {
        this.cim = cim;
        this.datum = datum;
        this.ido = ido;
        this.megjegyzes = megjegyzes;
    }

    public String getCim() { return cim; }
    public LocalDate getDatum() { return datum; }
    public LocalTime getIdo() { return ido; }
    public String getMegjegyzes() { return megjegyzes; }

    @Override
    public String toString() {
        return cim + " – " + datum + " " + ido;
    }

    public String reszletek() {
        return cim + " – " + datum + " " + ido + "\n\n" + megjegyzes;
    }
}
