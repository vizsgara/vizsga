import java.time.LocalDate;
import java.time.LocalTime;

public class Vasarlas {
    private LocalDate datum;
    private LocalTime idopont;
    private int ertek;
    private String aruhaz;

    public Vasarlas(String sor) {
        String[] adatok = sor.split(";");
        this.datum = LocalDate.parse(adatok[0]);
        this.idopont = LocalTime.parse(adatok[1]);
        this.ertek = Integer.parseInt(adatok[2]);
        this.aruhaz = adatok[3];
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getErtek() {
        return ertek;
    }

    public String getAruhaz() {
        return aruhaz;
    }

    @Override
    public String toString() {
        return datum + ";" + idopont + ";" + ertek + ";" + aruhaz;
    }
}
