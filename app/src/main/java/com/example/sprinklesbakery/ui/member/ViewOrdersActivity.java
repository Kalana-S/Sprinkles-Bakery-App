package com.example.sprinklesbakery.ui.member;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.data.model.Order;
import com.example.sprinklesbakery.utils.SessionManager;
import com.example.sprinklesbakery.viewmodel.OrderViewModel;
import com.example.sprinklesbakery.adapter.OrderAdapter;
import java.util.List;

public class ViewOrdersActivity extends AppCompatActivity implements OrderAdapter.OnOrderDeleteListener{

    private TextView tvMemberInfo;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        tvMemberInfo = findViewById(R.id.tvMemberInfo);
        recyclerView = findViewById(R.id.recyclerViewOrders);

        sessionManager = new SessionManager(this);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        String memberName = sessionManager.getMemberName();
        String memberEmail = sessionManager.getMemberEmail();
        tvMemberInfo.setText("Logged in as: " + memberName + " (" + memberEmail + ")");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(this);
        recyclerView.setAdapter(orderAdapter);

        int memberId = sessionManager.getMemberId();
        orderViewModel.getOrdersByMemberId(memberId).observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                orderAdapter.setOrderList(orders);
            }
        });
    }

    @Override
    public void onDeleteOrder(Order order) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Order")
                .setMessage("Are you sure you want to delete this order?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    orderViewModel.delete(order);
                    Toast.makeText(this, "Order deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

}

