package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.LoginAdapter;
import com.example.ltdd_finalproject.models.Customer;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //Init Component
    EditText passwordEdit,usernameEdit;
    Button loginButton;
    TextView regisTextview,forgotTextview;
    private LoginAdapter loginAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        loginAdapter = new LoginAdapter();
        setEvent();
    }
    protected void setEvent() {
        //Event for login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    usernameEdit.setError("Vui lòng nhập tên đăng nhập");
                    usernameEdit.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEdit.setError("Vui lòng nhập mật khẩu");
                    passwordEdit.requestFocus();
                    return;
                }

                loginAdapter.login(username, password, new LoginAdapter.LoginCallback()
                {
                    @Override
                    public void onLoginSuccess(String message, String accountType, String user) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        // TODO: Handle successful login (e.g., navigate to the main activity)
                        if ("Staff".equals(accountType)) {
                            Intent intent = new Intent(LoginActivity.this, StaffActivity.class);
                            startActivity(intent);
                            finish(); // Tùy chọn: Để kết thúc LoginActivity sau khi chuyển đến StaffActivity
                        }
                        else if ("Customer".equals(accountType)) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                            finish(); // Tùy chọn: Để kết thúc LoginActivity sau khi chuyển đến StaffActivity
                        }
                    }

                    @Override
                    public void onLoginFailure(String error) {
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
//                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
            }
        });

        //forgot
        forgotTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the TextView is clicked
                Toast.makeText(getApplicationContext(), "Forgot under development", Toast.LENGTH_SHORT).show();
            }
        });

        //Register
        regisTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the TextView is clicked
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void anhXa(){

        loginButton = findViewById(R.id.loginButton);
        passwordEdit = findViewById(R.id.passwordEdit);
        usernameEdit = findViewById(R.id.usernameEdit);
        regisTextview = findViewById(R.id.regisTextview);
        forgotTextview = findViewById(R.id.forgotTextview);

    }
}