package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;

public class RecipeController {

    @FXML
    private ListView<String> recipeListView;
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
    public void initialize() {
        ObservableList<String> recipes = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/demo1/recipe.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recipes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeListView.setItems(recipes);
    }

    @FXML
    void onAddRecipeClick() {
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

        initialize();
    }
}