package com.example.sprinklesbakery.ui.member;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.adapter.ViewCupcakeAdapter;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.sprinklesbakery.viewmodel.CategoryViewModel;

public class ViewCupcakesActivity extends AppCompatActivity {

    private CupcakeViewModel cupcakeViewModel;
    private CategoryViewModel categoryViewModel;
    private ViewCupcakeAdapter cupcakeAdapter;
    private Spinner categorySpinner;
    private List<String> categoryList = new ArrayList<>();
    private ArrayAdapter<String> categoryAdapter;
    private List<Cupcake> allCupcakes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cupcakes);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCupcakes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cupcakeAdapter = new ViewCupcakeAdapter();
        recyclerView.setAdapter(cupcakeAdapter);

        categorySpinner = findViewById(R.id.spinnerCategory);
        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        cupcakeViewModel.getAllCupcakes().observe(this, new Observer<List<Cupcake>>() {
            @Override
            public void onChanged(List<Cupcake> cupcakes) {
                allCupcakes = cupcakes;
                cupcakeAdapter.setCupcakeList(cupcakes);
            }
        });

        categoryViewModel.getAllCategories().observe(this, categories -> {
            categoryList.clear();
            categoryList.add("All");
            for (int i = 0; i < categories.size(); i++) {
                categoryList.add(categories.get(i).getCategoryName());
            }
            categoryAdapter.notifyDataSetChanged();
        });

        categoryAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categoryList.get(position);
                if (selectedCategory.equals("All")) {
                    cupcakeAdapter.setCupcakeList(allCupcakes);
                } else {
                    filterCupcakesByCategory(selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cupcakeAdapter.setCupcakeList(allCupcakes);
            }
        });
    }

    private void filterCupcakesByCategory(String category) {
        List<Cupcake> filteredList = new ArrayList<>();
        for (Cupcake cupcake : allCupcakes) {
            if (cupcake.getCategory().equals(category)) {
                filteredList.add(cupcake);
            }
        }
        cupcakeAdapter.setCupcakeList(filteredList);
    }
}

