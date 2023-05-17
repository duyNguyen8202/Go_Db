package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.RegisterAdapter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistInfoActivity extends AppCompatActivity {
    private EditText editTextFullName, editTextEmail, editTextPhoneNumber, editTextAddress;
    private DatePicker birthdayDatePicker;
    private RadioGroup genderRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_info);
        editTextFullName = findViewById(R.id.editTextTextPersonName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhoneNumber = findViewById(R.id.editTextTextPersonName3);
        editTextAddress = findViewById(R.id.editTextTextPersonName4);
        birthdayDatePicker = findViewById(R.id.birthday_date_picker);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        Button buttonFinish = findViewById(R.id.button);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getIntent().getStringExtra("username");
                String password = getIntent().getStringExtra("password");
                String fullName = editTextFullName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();

                java.sql.Date sqlDate = null; // Declare sqlDate variable

                // Lấy ngày sinh từ DatePicker
                int day = birthdayDatePicker.getDayOfMonth();
                int month = birthdayDatePicker.getMonth();
                int year = birthdayDatePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                Date dateOfBirth = new Date(calendar.getTimeInMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(dateOfBirth);
                // Lấy giới tính từ RadioButton được chọn
                boolean gender = getSelectedGender();

                // Gửi thông tin đăng ký lên server
                RegisterAdapter registerAdapter = new RegisterAdapter();
                registerAdapter.registerCustomer(username, fullName, email, phoneNumber, null, address, gender, formattedDate, password, new RegisterAdapter.RegisterCallback() {
                    @Override
                    public void onRegisterSuccess(String message) {
                        // Xử lý khi đăng ký thành công
                        Toast.makeText(RegistInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistInfoActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onRegisterFailure(String error) {
                        // Xử lý khi đăng ký thất bại
                        Toast.makeText(RegistInfoActivity.this, "Registration failed: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(RegistInfoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean getSelectedGender() {
        RadioButton selectedGenderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String genderText = selectedGenderRadioButton.getText().toString();

        // Kiểm tra giá trị của genderText và trả về giới tính tương ứng
        if (genderText.equals("Nam")) {
            return true; // Giới tính là Nam
        } else if (genderText.equals("Nữ")) {
            return false; // Giới tính là Nữ
        } else {
            // Giới tính không được chọn hoặc không hợp lệ
            return false; // Hoặc bạn có thể trả về giá trị mặc định tuỳ theo yêu cầu của bạn
        }
    }

}