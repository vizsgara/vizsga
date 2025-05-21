import java.time.Duration;
import java.time.LocalTime;

public class Bejegyzes {
    private String id;
    private int terminal;
    private LocalTime login;
    private LocalTime logoff;

    public Bejegyzes(String sor) {
        String[] parts = sor.split(";");
        this.id = parts[0];
        this.terminal = Integer.parseInt(parts[1]);
        this.login = LocalTime.parse(parts[2]);
        this.logoff = LocalTime.parse(parts[3]);
    }

    public String getId() {
        return id;
    }

    public LocalTime getLogin() {
        return login;
    }

    public LocalTime getLogoff() {
        return logoff;
    }

    public Duration getIdotartam() {
        long secLogin = login.toSecondOfDay();
        long secLogoff = logoff.toSecondOfDay();
        long kulonbseg = secLogoff - secLogin;

        if (kulonbseg < 0) {
            kulonbseg += 24 * 3600; // éjfél utánra nyúlt
        }

        // Ha valamiért 24 óránál hosszabb, levágjuk 24 órára (biztonsági okból)
        kulonbseg = Math.min(kulonbseg, 24 * 3600);

        return Duration.ofSeconds(kulonbseg);
    }


    @Override
    public String toString() {
        return id + ";" + terminal + ";" + login + ";" + logoff;
    }
}
