package com.example.yanglaocommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etPhone = findViewById(R.id.et_phone);
        EditText etPwd = findViewById(R.id.et_pwd);
        EditText etPwd2 = findViewById(R.id.et_pwd2);
        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(v -> {
            String phone = etPhone.getText().toString();
            String pwd = etPwd.getText().toString();
            String pwd2 = etPwd2.getText().toString();

            if (phone.isEmpty() || pwd.isEmpty() || pwd2.isEmpty()) {
                Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pwd.equals(pwd2)) {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }

            // 注册成功
            Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
            finish(); // 回到登录页
        });
    }
}