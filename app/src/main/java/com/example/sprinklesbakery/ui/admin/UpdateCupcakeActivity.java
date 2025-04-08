package com.example.sprinklesbakery.ui.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.data.model.Category;
import com.example.sprinklesbakery.viewmodel.CategoryViewModel;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import java.util.ArrayList;
import java.util.List;

public class UpdateCupcakeActivity extends AppCompatActivity {

    private EditText etCupcakeName, etPrice, etQuantity;
    private Spinner spinnerCategory;
    private ImageView imageCupcake;
    private Button btnUpdateCupcake, btnChooseImage;
    private Uri imageUri = null;
    private CupcakeViewModel cupcakeViewModel;
    private CategoryViewModel categoryViewModel;
    private List<String> categoryList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAdapter;
    private Cupcake cupcakeToUpdate;
    private static final int PICK_IMAGE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cupcake);

        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        etCupcakeName = findViewById(R.id.etCupcakeName);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        imageCupcake = findViewById(R.id.imageCupcake);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnUpdateCupcake = findViewById(R.id.btnSaveCupcake);

        cupcakeToUpdate = getIntent().getParcelableExtra("cupcake");

        if (cupcakeToUpdate == null) {
            Toast.makeText(this, "Invalid cupcake data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etCupcakeName.setText(cupcakeToUpdate.getCupcakeName());
        etPrice.setText(String.valueOf(cupcakeToUpdate.getPrice()));
        etQuantity.setText(String.valueOf(cupcakeToUpdate.getQuantity()));
        imageUri = Uri.parse(cupcakeToUpdate.getImageUri());
        imageCupcake.setImageURI(imageUri);

        spinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categoryList
        );
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);

        categoryViewModel.getAllCategories().observe(this, categories -> {
            categoryList.clear();
            for (Category cat : categories) {
                categoryList.add(cat.getCategoryName());
            }
            spinnerAdapter.notifyDataSetChanged();

            int selectedIndex = categoryList.indexOf(cupcakeToUpdate.getCategory());
            if (selectedIndex != -1) {
                spinnerCategory.setSelection(selectedIndex);
            }
        });

        btnChooseImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });
        btnUpdateCupcake.setOnClickListener(v -> updateCupcake());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageCupcake.setImageURI(imageUri);
        }
    }

    private void updateCupcake() {
        String name = etCupcakeName.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String priceText = etPrice.getText().toString().trim();
        String quantityText = etQuantity.getText().toString().trim();

        if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceText);
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price or quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        cupcakeToUpdate.setCupcakeName(name);
        cupcakeToUpdate.setCategory(category);
        cupcakeToUpdate.setPrice(price);
        cupcakeToUpdate.setQuantity(quantity);
        if (imageUri != null) {
            cupcakeToUpdate.setImageUri(imageUri.toString());
        }

        cupcakeViewModel.update(cupcakeToUpdate);
        Toast.makeText(this, "Cupcake updated", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }

}
