package com.example.sprinklesbakery.ui.main;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.ui.auth.AdminLoginActivity;
import com.example.sprinklesbakery.ui.auth.MemberLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnAdmin = findViewById(R.id.btnAdmin);
        Button btnMember = findViewById(R.id.btnMember);
        Button btnExit = findViewById(R.id.btnExit);

        btnAdmin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));
        });

        btnMember.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MemberLoginActivity.class));
        });

        btnExit.setOnClickListener(v -> finishAffinity());
    }

}