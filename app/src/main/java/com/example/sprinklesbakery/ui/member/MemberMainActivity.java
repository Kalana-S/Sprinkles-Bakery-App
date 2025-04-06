package com.example.sprinklesbakery.ui.member;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.ui.main.MainActivity;

public class MemberMainActivity extends AppCompatActivity {

    private Button btnOrderCupcakes, btnViewCupcakes, btnViewOrders, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member_main);

        btnOrderCupcakes = findViewById(R.id.btnOrderCupcakes);
        btnViewCupcakes = findViewById(R.id.btnViewCupcakes);
        btnViewOrders = findViewById(R.id.btnViewOrders);
        btnLogout = findViewById(R.id.btnLogout);

        btnOrderCupcakes.setOnClickListener(v -> {
            Intent intent = new Intent(MemberMainActivity.this, OrderCupcakeActivity.class);
            startActivity(intent);
        });

        btnViewCupcakes.setOnClickListener(v -> {
            Intent intent = new Intent(MemberMainActivity.this, ViewCupcakesActivity.class);
            startActivity(intent);
        });

        btnViewOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MemberMainActivity.this, ViewOrdersActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MemberMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}