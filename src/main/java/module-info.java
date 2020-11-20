module org.buluton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.buluton to javafx.fxml;
    exports org.buluton;
}