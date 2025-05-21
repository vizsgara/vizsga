import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Bejegyzes> bejegyzesek = Files.lines(Path.of("bejelentkezesek.txt"))
                .skip(1)
                .map(Bejegyzes::new)
                .collect(Collectors.toList());

        // 1. Hány felhasználó jelentkezett be összesen?
        long felhasznalokSzama = bejegyzesek.stream()
                .map(Bejegyzes::getId)
                .distinct()
                .count();
        System.out.println("1. Bejelentkezett felhasználók száma: " + felhasznalokSzama);

        // 2. Ki volt a legkorábbi és a legkésőbbi bejelentkező?
        Bejegyzes legkorabbi = Collections.min(bejegyzesek, Comparator.comparing(Bejegyzes::getLogin));
        Bejegyzes legkesobbi = Collections.max(bejegyzesek, Comparator.comparing(Bejegyzes::getLogin));
        System.out.println("2. Legkorábbi bejelentkező: " + legkorabbi.getId() + " (" + legkorabbi.getLogin() + ")");
        System.out.println("   Legkésőbbi bejelentkező: " + legkesobbi.getId() + " (" + legkesobbi.getLogin() + ")");

        // 3. Legrövidebb egyszeri bejelentkezés
        Bejegyzes legrövidebb = Collections.min(bejegyzesek, Comparator.comparing(Bejegyzes::getIdotartam));
        System.out.println("3. Legrövidebb bejelentkezés: " + legrövidebb.getId() + " (" + formatDuration(legrövidebb.getIdotartam()) + ")");

        // 4. Ki töltötte a legtöbb időt bejelentkezve?
        Map<String, Duration> idotartamok = new HashMap<>();
        for (Bejegyzes b : bejegyzesek) {
            idotartamok.merge(b.getId(), b.getIdotartam(), Duration::plus);
        }

        String legtobbIdoFelhasz = idotartamok.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        Duration legtobbIdo = idotartamok.get(legtobbIdoFelhasz);
        System.out.println("4. Legtöbb időt a rendszerben töltötte: " + legtobbIdoFelhasz + " (" + formatDuration(legtobbIdo) + ")");

        // 5. Kérj be egy időpontot, és írd ki, ki volt akkor bejelentkezve
        Scanner sc = new Scanner(System.in);
        System.out.print("5. Adj meg egy időpontot (HH:MM:SS): ");
        String input = sc.nextLine();
        LocalTime keresettIdopont = LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("   Bejelentkezve ekkor:");
        bejegyzesek.stream()
                .filter(b -> {
                    Duration d = b.getIdotartam();
                    LocalTime logoff = b.getLogoff();
                    if (logoff.isBefore(b.getLogin())) {
                        logoff = logoff.plusSeconds(24 * 3600);
                        return !keresettIdopont.isBefore(b.getLogin()) || keresettIdopont.isBefore(logoff);
                    }
                    return !keresettIdopont.isBefore(b.getLogin()) && keresettIdopont.isBefore(b.getLogoff());
                })
                .map(Bejegyzes::getId)
                .distinct()
                .forEach(id -> System.out.println("    - " + id));

        // 6. Írd ki a délutáni bejelentkezéseket egy delutan.txt fájlba (12:00:00 után kezdődnek)
        List<String> delutan = bejegyzesek.stream()
                .filter(b -> b.getLogin().isAfter(LocalTime.NOON))
                .map(Bejegyzes::toString)
                .toList();
        Files.write(Path.of("delutan.txt"), delutan);
        System.out.println("6. Délutáni bejelentkezések kiírva a delutan.txt fájlba.");

        // 7. Írd ki egy logins.txt fájlba, ki hányszor jelentkezett be
        Map<String, Long> bejelentkezesSzam = bejegyzesek.stream()
                .collect(Collectors.groupingBy(Bejegyzes::getId, Collectors.counting()));
        List<String> loginStat = bejelentkezesSzam.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + ": " + e.getValue())
                .toList();
        Files.write(Path.of("logins.txt"), loginStat);
        System.out.println("7. Bejelentkezésszámok kiírva a logins.txt fájlba.");
    }

    private static String formatDuration(Duration d) {
        long h = d.toHours();
        long m = d.toMinutesPart();
        long s = d.toSecondsPart();
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
