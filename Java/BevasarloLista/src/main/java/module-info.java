module org.example.bevasarlolista {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bevasarlolista to javafx.fxml;
    exports org.example.bevasarlolista;
}