package com.example.sprinklesbakery.ui.auth;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.data.model.Member;
import com.example.sprinklesbakery.viewmodel.MemberViewModel;

public class MemberRegistrationActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    private Button btnRegister, btnBack;
    private MemberViewModel memberViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_registration);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);

        btnRegister.setOnClickListener(v -> registerMember());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberRegistrationActivity.this, MemberLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerMember() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Member newMember = new Member(name, email, password);
        memberViewModel.registerMember(newMember);

        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        finish();
    }

}