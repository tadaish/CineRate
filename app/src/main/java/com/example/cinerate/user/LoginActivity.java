package com.example.cinerate.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerate.R;
import com.example.cinerate.daos.UserDAO;
import com.example.cinerate.models.User;
import com.example.cinerate.utils.PasswordUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        userDAO = new UserDAO(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (validateLogin(username, password)) {
                    User user = userDAO.getUserByUsername(username);
                    if (user != null && PasswordUtils.verifyPassword(password, user.getPassword())) {
                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Lưu ID người dùng vào SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("LoggedInUserId", user.getId());
                        editor.putString("LoggedInUserName", user.getUsername());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Chuyển đến HomePageActivity
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish(); // Kết thúc LoginActivity
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}

