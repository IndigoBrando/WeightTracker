package com.example.weighttracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText foodNameInput, foodWeightInput;
    private Button addFoodButton, clearAllButton;
    private ListView foodListView;
    private TextView totalWeightText;

    private ArrayList<String> foodList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private double totalWeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        foodNameInput = findViewById(R.id.foodNameInput);
        foodWeightInput = findViewById(R.id.foodWeightInput);
        addFoodButton = findViewById(R.id.addFoodButton);
        clearAllButton = findViewById(R.id.clearAllButton);
        foodListView = findViewById(R.id.foodListView);
        totalWeightText = findViewById(R.id.totalWeightText);

        // Setup adapter for ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodList);
        foodListView.setAdapter(adapter);

        // Add food button click listener
        addFoodButton.setOnClickListener(v -> addFood());

        // Clear all button click listener
        clearAllButton.setOnClickListener(v -> clearAllFood());
    }

    private void addFood() {
        String foodName = foodNameInput.getText().toString().trim();
        String foodWeightStr = foodWeightInput.getText().toString().trim();

        if (foodName.isEmpty() || foodWeightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both food name and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double foodWeight = Double.parseDouble(foodWeightStr);

            // Add to list and update total
            String foodEntry = foodName + " - " + foodWeight + " g";
            foodList.add(foodEntry);
            adapter.notifyDataSetChanged();

            totalWeight += foodWeight;
            updateTotalWeight();

            // Clear input fields
            foodNameInput.setText("");
            foodWeightInput.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid number for weight", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAllFood() {
        foodList.clear();
        adapter.notifyDataSetChanged();
        totalWeight = 0;
        updateTotalWeight();
    }

    private void updateTotalWeight() {
        totalWeightText.setText("Total Weight: " + totalWeight + " g");
    }
}
