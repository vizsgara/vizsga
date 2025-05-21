package org.example.naptar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class NaptarApp extends Application {
    private List<Esemeny> esemenyek = new ArrayList<>();
    private ListView<Esemeny> listView = new ListView<>();
    private TextArea reszletekArea = new TextArea();

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // Menü: események betöltése
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Fájl");
        MenuItem betoltes = new MenuItem("Események betöltése");
        menu.getItems().add(betoltes);
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        // ListView és TextArea
        HBox fopanel = new HBox(10);
        VBox bal = new VBox(10);
        VBox jobb = new VBox(10);

        listView.setPrefWidth(300);
        bal.getChildren().addAll(new Label("Események:"), listView);

        reszletekArea.setPrefSize(300, 150);
        reszletekArea.setEditable(false);
        jobb.getChildren().addAll(new Label("Részletek:"), reszletekArea);

        fopanel.getChildren().addAll(bal, jobb);
        root.getChildren().add(fopanel);

        // Törlés gomb
        Button torlesBtn = new Button("Kijelölt törlése");
        root.getChildren().add(torlesBtn);

        // Új esemény hozzáadása
        HBox ujEsemPanel = new HBox(10);
        TextField nevField = new TextField();
        nevField.setPromptText("Esemény neve");

        DatePicker datePicker = new DatePicker();

        Spinner<Integer> oraSpinner = new Spinner<>(0, 23, 12);
        Spinner<Integer> percSpinner = new Spinner<>(0, 59, 0);

        TextField megjegyzesField = new TextField();
        megjegyzesField.setPromptText("Megjegyzés");

        Button hozzaadBtn = new Button("Hozzáadás");

        ujEsemPanel.getChildren().addAll(nevField, datePicker, oraSpinner, percSpinner, megjegyzesField, hozzaadBtn);
        root.getChildren().add(new Label("Új esemény:"));
        root.getChildren().add(ujEsemPanel);

        // Események
        betoltes.setOnAction(e -> {
            try {
                esemenyek.clear();
                List<String> sorok = Files.readAllLines(Path.of("Naptar_grafikus.txt"));
                for (String sor : sorok) {
                    esemenyek.add(new Esemeny(sor));
                }
                listView.getItems().setAll(esemenyek);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, regi, uj) -> {
            if (uj != null) {
                reszletekArea.setText(uj.reszletek());
            }
        });

        torlesBtn.setOnAction(e -> {
            Esemeny kijelolt = listView.getSelectionModel().getSelectedItem();
            if (kijelolt != null) {
                esemenyek.remove(kijelolt);
                listView.getItems().setAll(esemenyek);
                reszletekArea.clear();
            }
        });

        hozzaadBtn.setOnAction(e -> {
            String nev = nevField.getText().trim();
            LocalDate datum = datePicker.getValue();
            int ora = oraSpinner.getValue();
            int perc = percSpinner.getValue();
            String megj = megjegyzesField.getText().trim();

            if (!nev.isEmpty() && datum != null && !megj.isEmpty()) {
                Esemeny uj = new Esemeny(nev, datum, LocalTime.of(ora, perc), megj);
                esemenyek.add(uj);
                listView.getItems().setAll(esemenyek);

                // mezők törlése
                nevField.clear();
                megjegyzesField.clear();
            }
        });

        Scene scene = new Scene(root, 950, 400);
        stage.setScene(scene);
        stage.setTitle("Naptár alkalmazás");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
