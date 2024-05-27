module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens org.example.dataStructureAnimation to javafx.fxml;
    exports org.example.dataStructureAnimation;

    exports org.example.calculator.application;
}

