package com.example.cinerate.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerate.R;
import com.example.cinerate.daos.UserDAO;
import com.example.cinerate.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.register_username);
        passwordEditText = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_button);

        userDAO = new UserDAO(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (validateInput(username, password)) {
                    // Kiểm tra xem username có tồn tại không
                    if (userDAO.getUserByUsername(username) == null) {
                        User newUser = new User(username, password,  "user");
                        userDAO.addUser(newUser);

                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                        // Chuyển về trang đăng nhập
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Username đã có", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Hãy điền vào tất cả các ô", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}
