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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IngredientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField unitField;

    @FXML
    void onAddIngredientClick(ActionEvent event) {
        try {
            List<Ingredient> ingredients = JsonUtil.readIngredientsFromFile();
            Ingredient ingredient = new Ingredient();
            ingredient.setName(nameField.getText());
            ingredient.setQuantity(Integer.parseInt(quantityField.getText()));
            ingredient.setUnit(unitField.getText());
            ingredients.add(ingredient);
            JsonUtil.writeIngredientsToFile(ingredients);

            writeIngredientToDatabase(ingredient);

            navigateToPage(event, "hello-view.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeIngredientToDatabase(Ingredient ingredient) {
        String selectSQL = "SELECT quantity FROM ingredients WHERE name = ?";
        String insertSQL = "INSERT INTO ingredients (name, quantity, unit) VALUES (?, ?, ?)";
        String updateSQL = "UPDATE ingredients SET quantity = ?, unit = ? WHERE name = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
             PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
             PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {

            selectStmt.setString(1, ingredient.getName());
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                // Ingredient exists, update it
                int existingQuantity = resultSet.getInt("quantity");
                int newQuantity = ingredient.getQuantity();
                updateStmt.setInt(1, newQuantity);
                updateStmt.setString(2, ingredient.getUnit());
                updateStmt.setString(3, ingredient.getName());
                updateStmt.executeUpdate();
            } else {
                // Ingredient does not exist, insert it
                insertStmt.setString(1, ingredient.getName());
                insertStmt.setInt(2, ingredient.getQuantity());
                insertStmt.setString(3, ingredient.getUnit());
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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