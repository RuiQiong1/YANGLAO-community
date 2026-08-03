package com.example.yanglaocommunity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTime, tvWeather, tvGreeting;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initBottomNav();
        startUpdateTime();
        initWeather();
        initServiceClick();
    }

    private void initViews() {
        tvTime = findViewById(R.id.tv_time);
        tvWeather = findViewById(R.id.tv_weather);
        tvGreeting = findViewById(R.id.tv_greeting);

        findViewById(R.id.nav_home).setOnClickListener(this);
        findViewById(R.id.nav_order).setOnClickListener(this);
        findViewById(R.id.nav_setting).setOnClickListener(this);
    }

    private void initBottomNav() {
        setNavSelected(R.id.nav_home);
    }

    private void startUpdateTime() {
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
                tvTime.setText(sdf.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(timeRunnable);
    }

    private void initWeather() {
        tvWeather.setText("26℃ 晴朗");
        updateGreeting();
    }

    private void updateGreeting() {
        int hour = Integer.parseInt(new SimpleDateFormat("HH", Locale.CHINA).format(new Date()));
        String greeting;
        if (hour >= 6 && hour < 12) greeting = "早上好，张大爷";
        else if (hour >= 12 && hour < 18) greeting = "中午好，张大爷";
        else if (hour >= 18 && hour < 24) greeting = "晚上好，张大爷";
        else greeting = "深夜了，注意休息";
        tvGreeting.setText(greeting);
    }

    // 简化：移除所有未登录判断，直接跳转服务页
    private void initServiceClick() {
        findViewById(R.id.ll_housekeeping).setOnClickListener(v -> {
            startActivity(new Intent(this, HousekeepingActivity.class));
        });
        findViewById(R.id.ll_medical).setOnClickListener(v -> {
            Toast.makeText(this, "上门问诊服务开发中", Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.ll_sos).setOnClickListener(v -> {
            Toast.makeText(this, "紧急求助已触发，正在联系家人", Toast.LENGTH_LONG).show();
        });
        findViewById(R.id.ll_buy).setOnClickListener(v -> {
            startActivity(new Intent(this, ShoppingActivity.class));
        });
    }

    @Override
    public void onClick(View v) {
        resetNavUnselected();
        if (v.getId() == R.id.nav_home) {
            setNavSelected(R.id.nav_home);
        } else if (v.getId() == R.id.nav_order) {
            setNavSelected(R.id.nav_order);
            startActivity(new Intent(this, OrderActivity.class));
        } else if (v.getId() == R.id.nav_setting) {
            setNavSelected(R.id.nav_setting);
            startActivity(new Intent(this, SettingsActivity.class));
        }
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

        View navSetting = findViewById(R.id.nav_setting);
        navSetting.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navSetting.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && timeRunnable != null) handler.removeCallbacks(timeRunnable);
    }
}
//package com.example.yanglaocommunity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // 底部导航
//        findViewById(R.id.nav_home).setOnClickListener(v -> {});
//        findViewById(R.id.nav_order).setOnClickListener(v -> {
//            startActivity(new Intent(this, OrderActivity.class));
//            finish();
//        });
//        findViewById(R.id.nav_settings).setOnClickListener(v -> {
//            startActivity(new Intent(this, SettingsActivity.class));
//            finish();
//        });
//    }
//}