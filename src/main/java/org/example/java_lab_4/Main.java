package org.example.java_lab_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(root.load());
        stage.setTitle("Tariffs");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}