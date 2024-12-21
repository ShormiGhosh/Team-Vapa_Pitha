package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onAskChatbotClick(ActionEvent actionEvent) {
        navigateToPage(actionEvent, "ask-chatbot-view.fxml");
    }

    public void onInputIngredientsClick(ActionEvent actionEvent) {
        navigateToPage(actionEvent, "available_ingredient_input.fxml");
    }

    public void onAddShoppingIngredientsClick(ActionEvent actionEvent) {
        navigateToPage(actionEvent, "recipe2.fxml");
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