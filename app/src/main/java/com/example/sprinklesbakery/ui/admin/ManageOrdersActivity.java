package com.example.sprinklesbakery.ui.admin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.adapter.AdminOrderAdapter;
import com.example.sprinklesbakery.data.model.Order;
import com.example.sprinklesbakery.viewmodel.OrderViewModel;
import java.util.ArrayList;

public class ManageOrdersActivity extends AppCompatActivity {

    private OrderViewModel orderViewModel;
    private AdminOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdminOrderAdapter(new ArrayList<>(), this::deleteOrder);
        recyclerView.setAdapter(adapter);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(this, orders -> adapter.updateOrders(orders));
    }

    private void deleteOrder(Order order) {
        orderViewModel.delete(order);
        Toast.makeText(this, "Order deleted", Toast.LENGTH_SHORT).show();
    }

}

