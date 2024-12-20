package org.example.java_lab_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DatabaseHandler extends Config {
    static Connection dbConnection;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public static void addTariff(String tariffName, Double tariffPrice, Double tariffDiscount) {
        String insert = "INSERT INTO " + Const.TARIFF_TABLE + "(" + Const.TARIFF_NAME + "," + Const.TARIFF_PRICE + "," + Const.TARIFF_PRICE_DISCOUNT + ") " + "VALUES(?,?,?)";
        try {

            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(insert);
            statement.setString(1, tariffName);
            statement.setDouble(2, tariffPrice);
            statement.setDouble(3, tariffDiscount);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateTariff(int id, String name, double price, Double discount) {
        String query = "UPDATE " + Const.TARIFF_TABLE + " SET " + Const.TARIFF_NAME + " = ?, " + Const.TARIFF_PRICE + " = ?, "+ Const.TARIFF_PRICE_DISCOUNT + " = ? WHERE id = ?";

        try{

            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setDouble(3, discount);
            statement.setInt(4, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteTariff(int id) {
        String query = "DELETE FROM " + Const.TARIFF_TABLE +" WHERE id = ?";

        try {

            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static ObservableList<Tariffs> getDataTariff() throws SQLException, ClassNotFoundException {
        dbConnection = getDbConnection();
        ObservableList<Tariffs> list = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = dbConnection.prepareStatement("select * from tariff");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                list.add(new Tariffs(resultSet.getInt("id") ,resultSet.getString(Const.TARIFF_NAME),resultSet.getDouble(Const.TARIFF_PRICE),
                        resultSet.getDouble(Const.TARIFF_PRICE_DISCOUNT), resultSet.getDouble(Const.TARIFF_FINAL_PRICE)));
            }
        }
        catch (Exception ignored){
        }

        return list;
    }

    public static Tariffs getCheapestTariff() {
        Tariffs cheapestTariff = null;

        String query = "SELECT * FROM " + Const.TARIFF_TABLE + " ORDER BY " + Const.TARIFF_PRICE + " ASC LIMIT 1";

        try {
            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cheapestTariff = new Tariffs(resultSet.getInt("id") ,resultSet.getString(Const.TARIFF_NAME),resultSet.getDouble(Const.TARIFF_PRICE),
                        resultSet.getDouble(Const.TARIFF_PRICE_DISCOUNT), resultSet.getDouble(Const.TARIFF_FINAL_PRICE));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cheapestTariff;
    }
}
