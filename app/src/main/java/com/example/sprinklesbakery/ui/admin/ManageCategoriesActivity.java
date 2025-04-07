package com.example.sprinklesbakery.ui.admin;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.adapter.CategoryAdapter;
import com.example.sprinklesbakery.viewmodel.CategoryViewModel;

public class ManageCategoriesActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_categories);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CategoryAdapter(category -> {
            categoryViewModel.delete(category);
            Toast.makeText(this, "Category deleted", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
        categoryViewModel.getAllCategories().observe(this, adapter::setCategories);

    }

}

