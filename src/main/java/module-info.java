module org.example.java_lab_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example.java_lab_4 to javafx.fxml;
    exports org.example.java_lab_4;
}