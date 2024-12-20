package org.example.java_lab_4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML
    private TextField txt_discount;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_tariff;

    @FXML
    private TextField txt_finalPrice;

    public void updateCheapestTariff(){
        String tariff = DatabaseHandler.getCheapestTariff().getTariff();
        Double discount = DatabaseHandler.getCheapestTariff().getDiscount(), price = DatabaseHandler.getCheapestTariff().getPrice();
        Double finalPrice = price - discount*price/100;
        txt_tariff.setText(tariff);
        txt_price.setText(price.toString());
        txt_discount.setText(discount.toString());
        txt_finalPrice.setText(finalPrice.toString());
    }
    public void CheapTariff() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("CheapestTariff.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(root.load());
        newStage.setTitle("Tariffs");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        Controller controller = root.getController();
        controller.updateCheapestTariff();
        newStage.showAndWait();
    }

    public void AllTariff() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("AllTariffs.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(root.load());
        newStage.setTitle("Tariffs");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }

}