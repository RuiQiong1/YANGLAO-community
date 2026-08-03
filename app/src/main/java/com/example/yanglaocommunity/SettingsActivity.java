package com.example.yanglaocommunity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private TextView tvName, tvPhone, tvMemberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // 1. 直接读取用户信息（无需判断登录）
        initUserInfo();
        // 2. 初始化底部导航
        initBottomNav();
        // 3. 初始化子女远程协助
        initRemoteHelp();
    }

    /**
     * 直接显示用户信息（核心：登录后必存在）
     */
    private void initUserInfo() {
        tvName = findViewById(R.id.tv_setting_name);
        tvPhone = findViewById(R.id.tv_setting_phone);
        tvMemberId = findViewById(R.id.tv_setting_id);

        // 读取本地存储的用户信息
        UserInfo user = SharedPreferencesUtil.getUser(this);
        tvName.setText(user.getName());
        tvPhone.setText("联系电话：" + user.getPhone());
        tvMemberId.setText("会员ID：" + user.getMemberId());
    }

    /**
     * 初始化底部导航
     */
    private void initBottomNav() {
        findViewById(R.id.nav_home).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        findViewById(R.id.nav_order).setOnClickListener(v -> {
            startActivity(new Intent(this, OrderActivity.class));
            finish();
        });
        findViewById(R.id.nav_settings).setOnClickListener(v -> {
            setNavSelected(R.id.nav_settings);
        });
        setNavSelected(R.id.nav_settings);
    }

    /**
     * 子女远程协助（核心亮点）
     */
    private void initRemoteHelp() {
        findViewById(R.id.ll_remote_help).setOnClickListener(v -> {
            int helpCode = (int) (Math.random() * 900000 + 100000);
            new AlertDialog.Builder(this)
                    .setTitle("子女远程协助")
                    .setMessage("您的临时协助码：\n" + helpCode + "\n\n有效期5分钟")
                    .setPositiveButton("我知道了", (dialog, which) -> {
                        Toast.makeText(this, "协助码已生成", Toast.LENGTH_SHORT).show();
                    })
                    .setCancelable(false)
                    .show();
        });
    }

    /**
     * 退出登录：返回启动页（重新登录）
     */
    public void onLogoutClick(View v) {
        SharedPreferencesUtil.logout(this);
        startActivity(new Intent(this, StartActivity.class));
        finishAffinity(); // 关闭所有页面，回到登录页
    }

    private void setNavSelected(int navId) {
        resetNavUnselected();
        View navView = findViewById(navId);
        if (navView != null) {
            navView.setBackgroundResource(R.drawable.bg_nav_selected);
            TextView navText = navView.findViewById(R.id.tv_nav_text);
            if (navText != null) navText.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void resetNavUnselected() {
        View navHome = findViewById(R.id.nav_home);
        navHome.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navHome.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));

        View navOrder = findViewById(R.id.nav_order);
        navOrder.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navOrder.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));

        View navSettings = findViewById(R.id.nav_settings);
        navSettings.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navSettings.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));
    }
}