module org.example.naptar {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.naptar to javafx.fxml;
    exports org.example.naptar;
}