package com.example.sprinklesbakery.ui.member;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprinklesbakery.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.lifecycle.ViewModelProvider;
import com.example.sprinklesbakery.ui.auth.MemberLoginActivity;
import com.example.sprinklesbakery.utils.SessionManager;
import com.example.sprinklesbakery.viewmodel.UpdateMemberViewModel;

public class UpdateMemberActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private UpdateMemberViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_member);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        sessionManager = new SessionManager(this);
        viewModel = new ViewModelProvider(this).get(UpdateMemberViewModel.class);

        viewModel.getMemberLiveData().observe(this, member -> {
            if (member != null) {
                etName.setText(member.getName());
                etEmail.setText(member.getEmail());
                etPassword.setText(member.getPassword());
            }
        });

        viewModel.loadMemberData(sessionManager.getMemberId());

        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.updateMember(sessionManager.getMemberId(), name, email, password);

                sessionManager.clearSession();

                Toast.makeText(this, "Details updated. Please login again.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateMemberActivity.this, MemberLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
