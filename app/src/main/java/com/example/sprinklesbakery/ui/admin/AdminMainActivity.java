package com.example.sprinklesbakery.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.ui.main.MainActivity;

public class AdminMainActivity extends AppCompatActivity {
    private Button btnCreateCategory, btnCreateCupcake, btnManageCategories, btnManageCupcakes, btnManageOrders, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_main);

        btnCreateCategory = findViewById(R.id.btnCreateCategories);
        btnCreateCupcake = findViewById(R.id.btnCreateCupcakes);
        btnManageCategories = findViewById(R.id.btnManageCategories);
        btnManageCupcakes = findViewById(R.id.btnManageCupcakes);
        btnManageOrders = findViewById(R.id.btnManageOrders);
        btnLogout = findViewById(R.id.btnLogout);

        btnCreateCategory.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, CreateCategoryActivity.class);
            startActivity(intent);
        });

        btnCreateCupcake.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, CreateCupcakeActivity.class);
            startActivity(intent);
        });

        btnManageCategories.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, ManageCategoriesActivity.class);
            startActivity(intent);
        });

        btnManageCupcakes.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, ManageCupcakesActivity.class);
            startActivity(intent);
        });

        btnManageOrders.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, ManageOrdersActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

}