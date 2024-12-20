package org.example.java_lab_4;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;

public class Controller_Table {
    @FXML
    private TextField txt_discount;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_tariff;

    @FXML
    private TextField txt_finalPrice;

    @FXML
    private TableView<Tariffs> tariffTable;

    @FXML
    private TableColumn<Tariffs, String> col_tariff;

    @FXML
    private TableColumn<Tariffs, Double> col_price;

    @FXML
    private TableColumn<Tariffs, Integer> col_id;

    @FXML
    private TableColumn<Tariffs, Double> col_finalPrice;

    @FXML
    private TableColumn<Tariffs, Double> col_discount;

    int index = -1;
    ObservableList<Tariffs> listM;

    public void initialize() throws SQLException, ClassNotFoundException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_tariff.setCellValueFactory(new PropertyValueFactory<>("tariff"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        col_finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));

        listM = DatabaseHandler.getDataTariff();
        tariffTable.setItems(listM);

        TextFormatter<String> priceFormatter = Filter.numericFilter(10000);
        txt_price.setTextFormatter(priceFormatter);
        TextFormatter<String> discountFormatter = Filter.numericFilter(100);
        txt_discount.setTextFormatter(discountFormatter);

        Filter.StringFilter(txt_tariff,24);
    }

    private void update() throws SQLException, ClassNotFoundException {
        txt_id.clear();
        txt_tariff.clear();
        txt_price.clear();
        txt_discount.clear();
        txt_finalPrice.clear();
        initialize();
    }

    private boolean checkUp(){
        if (txt_discount.getText().isEmpty()){
            txt_discount.setText("0");
        }
        if (txt_price.getText().isEmpty()){
            txt_price.setPromptText("Введите стоимость");
            return false;
        }
        if (txt_tariff.getText().isEmpty()){
            txt_tariff.setPromptText("Введите название тарифа");
            return false;
        }
        return true;
    }

    public void addData() throws SQLException, ClassNotFoundException {
        if(checkUp()) {
            DatabaseHandler.addTariff(txt_tariff.getText(), Double.parseDouble(txt_price.getText()), Double.parseDouble(txt_discount.getText()));
        }
        update();
    }

    public void changeData() throws SQLException, ClassNotFoundException {
        if(checkUp()){
            DatabaseHandler.updateTariff(Integer.parseInt(txt_id.getText()), txt_tariff.getText(), Double.parseDouble(txt_price.getText()), Double.parseDouble(txt_discount.getText()));
        }
        update();
    }

    public void deleteData() throws SQLException, ClassNotFoundException {
        if(!txt_id.getText().isEmpty()) {
            DatabaseHandler.deleteTariff(Integer.parseInt(txt_id.getText()));
        }
        update();
    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tariffTable.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_tariff.setText(col_tariff.getCellData(index));
        txt_price.setText(col_price.getCellData(index).toString());
        txt_discount.setText(col_discount.getCellData(index).toString());
        txt_finalPrice.setText(col_finalPrice.getCellData(index).toString());
    }
}
