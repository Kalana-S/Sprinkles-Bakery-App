package com.example.sprinklesbakery.ui.auth;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.viewmodel.MemberLoginViewModel;
import com.example.sprinklesbakery.ui.member.MemberMainActivity;
import com.example.sprinklesbakery.utils.SessionManager;

public class MemberLoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private MemberLoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        viewModel = new ViewModelProvider(this).get(MemberLoginViewModel.class);
        viewModel.getLoggedInMember().observe(this, member -> {
            if (member != null) {
                SessionManager sessionManager = new SessionManager(MemberLoginActivity.this);
                sessionManager.saveMemberSession(member.getId(), member.getName(), member.getEmail());

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MemberMainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.login(email, password);
            }
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, MemberRegistrationActivity.class));
        });

    }

}