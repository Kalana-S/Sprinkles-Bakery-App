package com.example.sprinklesbakery.ui.member;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.data.model.Order;
import com.example.sprinklesbakery.utils.SessionManager;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import com.example.sprinklesbakery.viewmodel.OrderViewModel;
import java.util.ArrayList;
import java.util.List;

public class OrderCupcakeActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private CupcakeViewModel cupcakeViewModel;
    private OrderViewModel orderViewModel;
    private TextView tvMemberId, tvMemberName, tvMemberEmail, tvPrice, tvAvailableQuantity, tvTotalPrice;
    private Spinner spCategory, spCupcake;
    private EditText etQuantity;
    private Button btnOrder;
    private List<String> categories = new ArrayList<>();
    private List<Cupcake> cupcakes = new ArrayList<>();
    private Cupcake selectedCupcake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cupcake);

        sessionManager = new SessionManager(this);
        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        tvMemberId = findViewById(R.id.tvMemberId);
        tvMemberName = findViewById(R.id.tvMemberName);
        tvMemberEmail = findViewById(R.id.tvMemberEmail);
        tvPrice = findViewById(R.id.tvPrice);
        tvAvailableQuantity = findViewById(R.id.tvAvailableQuantity);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        spCategory = findViewById(R.id.spCategory);
        spCupcake = findViewById(R.id.spCupcake);
        etQuantity = findViewById(R.id.etQuantity);
        btnOrder = findViewById(R.id.btnOrder);

        tvMemberId.setText("ID: " + sessionManager.getMemberId());
        tvMemberName.setText("Name: " + sessionManager.getMemberName());
        tvMemberEmail.setText("Email: " + sessionManager.getMemberEmail());

        cupcakeViewModel.getAllCupcakes().observe(this, cupcakeList -> {
            cupcakes = cupcakeList;
            categories.clear();
            for (Cupcake cupcake : cupcakes) {
                if (!categories.contains(cupcake.getCategory())) {
                    categories.add(cupcake.getCategory());
                }
            }
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                    OrderCupcakeActivity.this,
                    R.layout.spinner_item,
                    categories
            );
            categoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spCategory.setAdapter(categoryAdapter);

        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories.get(position);
                loadCupcakesForCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spCupcake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCupcake = (Cupcake) spCupcake.getSelectedItem();
                updateCupcakeDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        etQuantity.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTotalPrice();
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        btnOrder.setOnClickListener(v -> placeOrder());
    }

    private void loadCupcakesForCategory(String category) {
        List<Cupcake> filteredCupcakes = new ArrayList<>();
        for (Cupcake cupcake : cupcakes) {
            if (cupcake.getCategory().equals(category)) {
                filteredCupcakes.add(cupcake);
            }
        }
        ArrayAdapter<Cupcake> cupcakeAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                filteredCupcakes
        );
        cupcakeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spCupcake.setAdapter(cupcakeAdapter);
    }

    private void updateCupcakeDetails() {
        if (selectedCupcake != null) {
            tvPrice.setText("Price: $" + selectedCupcake.getPrice());
            tvAvailableQuantity.setText("Available: " + selectedCupcake.getQuantity());
            updateTotalPrice();
        }
    }

    private void updateTotalPrice() {
        if (selectedCupcake != null) {
            String quantityText = etQuantity.getText().toString();
            if (!quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                if (quantity > selectedCupcake.getQuantity()) {
                    etQuantity.setError("Not enough stock available!");
                    tvTotalPrice.setText("Total: $0.00");
                    return;
                }
                double total = quantity * selectedCupcake.getPrice();
                tvTotalPrice.setText("Total: $" + total);
            } else {
                tvTotalPrice.setText("Total: $0.00");
            }
        }
    }

    private void placeOrder() {
        if (selectedCupcake == null) {
            Toast.makeText(this, "Please select a cupcake", Toast.LENGTH_SHORT).show();
            return;
        }

        String quantityText = etQuantity.getText().toString();
        if (quantityText.isEmpty()) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        if (quantity > selectedCupcake.getQuantity()) {
            Toast.makeText(this, "Not enough stock available!", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalPrice = quantity * selectedCupcake.getPrice();

        Order order = new Order(
                selectedCupcake.getCategory(),
                selectedCupcake.getCupcakeName(),
                selectedCupcake.getPrice(),
                quantity,
                sessionManager.getMemberId(),
                sessionManager.getMemberName(),
                sessionManager.getMemberEmail(),
                totalPrice
        );

        orderViewModel.insert(order);
        selectedCupcake.setQuantity(selectedCupcake.getQuantity() - quantity);
        cupcakeViewModel.update(selectedCupcake);

        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
