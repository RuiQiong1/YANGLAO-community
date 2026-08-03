package com.example.yanglaocommunity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private EditText etPhone, etPwd;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 1. 绑定控件
        etPhone = findViewById(R.id.et_login_phone);
        etPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.tv_go_register);

        // 2. 登录逻辑（核心：登录成功→保存信息→跳首页）
        btnLogin.setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();

            if (phone.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "手机号/密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            // 模拟登录校验（毕业设计演示用，可替换为数据库逻辑）
            if (phone.equals("13800138000") && pwd.equals("123456")) {
                // 3. 登录成功：保存用户信息+标记登录状态
                UserInfo user = new UserInfo("张大爷", "138****8000", "YL20260302");
                SharedPreferencesUtil.setUser(this, user);
                SharedPreferencesUtil.setLogin(this, true);

                Toast.makeText(this, "登录成功，欢迎使用！", Toast.LENGTH_SHORT).show();
                // 4. 跳转到首页，关闭登录页（禁止返回）
                startActivity(new Intent(this, MainActivity.class));
                finish(); // 关键：关闭启动页，返回键直接退出APP
            } else {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. 跳转注册页（可选，演示用）
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }


}