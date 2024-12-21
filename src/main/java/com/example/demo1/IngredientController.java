package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField unitField;

    @FXML
    void onAddIngredientClick(ActionEvent event) {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String unit = unitField.getText();

        String insertSQL = "INSERT INTO ingredients (name, quantity, unit) VALUES (?, ?, ?)";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setString(3, unit);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Ingredient added: " + name + ", Quantity: " + quantity + ", Unit: " + unit);
            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
            navigateToPage(event, "hello-view.fxml");

    }

    private void navigateToPage(ActionEvent actionEvent, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}