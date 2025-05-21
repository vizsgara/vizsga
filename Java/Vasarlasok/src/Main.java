import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Vasarlas> vasarlasok = Files.lines(Path.of("vasarlasok.txt"))
                .skip(1)
                .map(Vasarlas::new)
                .collect(Collectors.toList());

        // 2. feladat – Dátum bekérése és ellenőrzés
        Scanner sc = new Scanner(System.in);
        System.out.print("Adj meg egy dátumot (ÉÉÉÉ.HH.NN.): ");
        String datumStr = sc.nextLine();
        LocalDate keresettDatum = LocalDate.parse(datumStr, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        boolean volt = vasarlasok.stream().anyMatch(v -> v.getDatum().equals(keresettDatum));
        System.out.println(volt ? "Volt vásárlás." : "Nem volt vásárlás.");

        // 3. feladat – Legkorábbi és legkésőbbi vásárlás
        LocalDate min = vasarlasok.stream().map(Vasarlas::getDatum).min(LocalDate::compareTo).get();
        LocalDate max = vasarlasok.stream().map(Vasarlas::getDatum).max(LocalDate::compareTo).get();
        System.out.println("Legkorábbi vásárlás: " + min);
        System.out.println("Legkésőbbi vásárlás: " + max);

        // 4. feladat – Szombati vásárlások fájlba
        List<String> szombatok = vasarlasok.stream()
                .filter(v -> v.getDatum().getDayOfWeek() == DayOfWeek.SATURDAY)
                .map(Vasarlas::toString)
                .collect(Collectors.toList());
        Files.write(Path.of("szombatok.txt"), szombatok);
        System.out.println("Szombati vásárlások kiírva a szombatok.txt fájlba.");

        // 5. feladat – Vásárlással érintett napok száma
        long kulonbozoNapok = vasarlasok.stream()
                .map(Vasarlas::getDatum)
                .distinct()
                .count();
        System.out.println("Vásárlással érintett napok száma: " + kulonbozoNapok);

        // 6. feladat – Átlagos vásárlási érték
        double atlag = vasarlasok.stream().mapToInt(Vasarlas::getErtek).average().orElse(0);
        System.out.printf("Átlagos vásárlás értéke: %.2f Ft\n", atlag);

        // 7. feladat – Különböző áruházak száma
        long kulonbozoAruhazak = vasarlasok.stream()
                .map(Vasarlas::getAruhaz)
                .distinct()
                .count();
        System.out.println("Különböző áruházak száma: " + kulonbozoAruhazak);
    }
}
