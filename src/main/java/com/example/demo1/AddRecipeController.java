package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddRecipeController {

    public ListView recipeListView;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField tasteField;
    @FXML
    private TextField reviewsField;
    @FXML
    private TextField cuisineTypeField;
    @FXML
    private TextField preparationTimeField;

    @FXML
    void onAddRecipeClick(ActionEvent event) {
        String newRecipe = "id: " + idField.getText() + "\n" +
                "name: " + nameField.getText() + "\n" +
                "taste: " + tasteField.getText() + "\n" +
                "reviews: " + reviewsField.getText() + "\n" +
                "cuisine type: " + cuisineTypeField.getText() + "\n" +
                "preparation time: " + preparationTimeField.getText() + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/com/example/demo1/recipe.txt", true))) {
            writer.write(newRecipe);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update the ListView in RecipeController
        RecipeController recipeController = new RecipeController();
        recipeController.initialize();
    }
}