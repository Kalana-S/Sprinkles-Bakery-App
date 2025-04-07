package com.example.sprinklesbakery.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Category;
import com.example.sprinklesbakery.viewmodel.CategoryViewModel;

public class CreateCategoryActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private EditText etCategoryName;
    private Button btnSaveCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        categoryViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(CategoryViewModel.class);

        etCategoryName = findViewById(R.id.etCategoryName);
        btnSaveCategory = findViewById(R.id.btnSaveCategory);

        btnSaveCategory.setOnClickListener(view -> saveCategory());
    }

    private void saveCategory() {
        String categoryName = etCategoryName.getText().toString().trim();

        if (categoryName.isEmpty()) {
            Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show();
            return;
        }

        categoryViewModel.insert(new Category(categoryName));

        Toast.makeText(this, "Category saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
