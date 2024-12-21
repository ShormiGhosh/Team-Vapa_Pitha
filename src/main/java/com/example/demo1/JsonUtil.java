package com.example.demo1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new Gson();
    private static final String FILE_PATH = "src/main/resources/com/example/demo1/ingredients.json";

    public static List<Ingredient> readIngredientsFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list if the file does not exist
        }
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type ingredientListType = new TypeToken<List<Ingredient>>() {}.getType();
            return gson.fromJson(reader, ingredientListType);
        }
    }

    public static void writeIngredientsToFile(List<Ingredient> ingredients) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(ingredients, writer);
        }
    }
}