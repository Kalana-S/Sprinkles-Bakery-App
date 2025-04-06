package com.example.sprinklesbakery.ui.admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Category;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.viewmodel.CategoryViewModel;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import java.util.ArrayList;
import java.util.List;

public class CreateCupcakeActivity extends AppCompatActivity {
    private CupcakeViewModel cupcakeViewModel;
    private CategoryViewModel categoryViewModel;
    private EditText etCupcakeName, etPrice, etQuantity;
    private Spinner spinnerCategory;
    private Button btnSaveCupcake;
    private List<String> categoryList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cupcake);

        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        etCupcakeName = findViewById(R.id.etCupcakeName);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSaveCupcake = findViewById(R.id.btnSaveCupcake);
        spinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categoryList
        );
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item); // custom dropdown
        spinnerCategory.setAdapter(spinnerAdapter);


        categoryViewModel.getAllCategories().observe(this, categories -> {
            categoryList.clear();
            for (Category category : categories) {
                categoryList.add(category.getCategoryName());
            }
            spinnerAdapter.notifyDataSetChanged();
        });

        btnSaveCupcake.setOnClickListener(v -> saveCupcake());
    }

    private void saveCupcake() {
        String name = etCupcakeName.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String priceText = etPrice.getText().toString().trim();
        String quantityText = etQuantity.getText().toString().trim();

        if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceText);
        int quantity = Integer.parseInt(quantityText);

        Cupcake newCupcake = new Cupcake(name, category, price, quantity);
        cupcakeViewModel.insert(newCupcake);
        Toast.makeText(this, "Cupcake saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
