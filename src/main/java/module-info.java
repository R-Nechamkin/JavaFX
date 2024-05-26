module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.dataStructureAnimation to javafx.fxml;
    exports org.example.dataStructureAnimation;
}

