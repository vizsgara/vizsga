package org.example.bevasarlolista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class BevasarloApp extends Application {

    private List<Termek> aruk = new ArrayList<>();
    private ListView<Termek> arukView = new ListView<>();
    private ListView<Termek> listaView = new ListView<>();
    private List<Termek> bevasarloLista = new ArrayList<>();
    private PieChart pieChart = new PieChart();
    private RadioButton dbRadio = new RadioButton("Darabszám");
    private RadioButton ertekRadio = new RadioButton("Érték");

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // Menü
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Fájl");
        MenuItem betoltes = new MenuItem("Áruk betöltése");
        menu.getItems().add(betoltes);
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        // Két ListView és gombok
        HBox kozep = new HBox(10);
        VBox bal = new VBox(5, new Label("Áruk:"), arukView);
        VBox jobb = new VBox(5, new Label("Bevásárlólista:"), listaView);

        VBox gombok = new VBox(10);
        Button hozzaadBtn = new Button(">");
        Button torolBtn = new Button("Törlés");
        gombok.getChildren().addAll(hozzaadBtn, torolBtn);

        arukView.setPrefWidth(300);
        listaView.setPrefWidth(300);
        arukView.setPrefHeight(300);
        listaView.setPrefHeight(300);

        kozep.getChildren().addAll(bal, gombok, jobb);
        root.getChildren().add(kozep);

        // Diagram és vezérlés
        HBox diagramPanel = new HBox(10);
        VBox vez = new VBox(10);
        Button megjelenitBtn = new Button("Diagram megjelenítése");
        ToggleGroup tg = new ToggleGroup();
        dbRadio.setToggleGroup(tg);
        ertekRadio.setToggleGroup(tg);
        dbRadio.setSelected(true);
        vez.getChildren().addAll(megjelenitBtn, dbRadio, ertekRadio);
        diagramPanel.getChildren().addAll(vez, pieChart);
        root.getChildren().add(diagramPanel);

        // Fájl betöltés
        betoltes.setOnAction(e -> {
            try {
                aruk.clear();
                List<String> sorok = Files.readAllLines(Path.of("bevasarloLista.txt"));
                sorok.remove(0); // fejléc
                for (String sor : sorok) {
                    aruk.add(new Termek(sor));
                }
                arukView.getItems().setAll(aruk);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Hozzáadás gomb
        hozzaadBtn.setOnAction(e -> {
            Termek kivalasztott = arukView.getSelectionModel().getSelectedItem();
            if (kivalasztott != null) {
                bevasarloLista.add(kivalasztott);
                listaView.getItems().setAll(bevasarloLista);
            }
        });

        // Törlés gomb
        torolBtn.setOnAction(e -> {
            Termek kivalasztott = listaView.getSelectionModel().getSelectedItem();
            if (kivalasztott != null) {
                bevasarloLista.remove(kivalasztott);
                listaView.getItems().setAll(bevasarloLista);
            }
        });

        // Diagram
        megjelenitBtn.setOnAction(e -> {
            Map<String, Integer> stat = new HashMap<>();
            for (Termek t : bevasarloLista) {
                stat.putIfAbsent(t.getNev(), 0);
                if (dbRadio.isSelected()) {
                    stat.put(t.getNev(), stat.get(t.getNev()) + 1);
                } else {
                    stat.put(t.getNev(), stat.get(t.getNev()) + t.getAr());
                }
            }
            pieChart.getData().clear();
            for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
        });

        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Bevásárlólista alkalmazás");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
