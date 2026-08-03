package com.example.yanglaocommunity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HousekeepingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping);

        // 直接读取用户信息显示
        TextView tvUsername = findViewById(R.id.tv_username);
        UserInfo user = SharedPreferencesUtil.getUser(this);
        tvUsername.setText("您好，" + user.getName());

        // 返回按钮
        findViewById(R.id.iv_back_housekeeping).setOnClickListener(v -> finish());

        // 预约按钮（简化购买逻辑）
        findViewById(R.id.btn_book_housekeeping).setOnClickListener(v -> {
            Toast.makeText(this, "预约成功！订单已生成", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, OrderActivity.class));
            finish();
        });

        // 底部导航
        findViewById(R.id.nav_home).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        findViewById(R.id.nav_order).setOnClickListener(v -> {
            startActivity(new Intent(this, OrderActivity.class));
            finish();
        });
        findViewById(R.id.nav_setting).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });
    }
}
//package com.example.yanglaocommunity;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class HousekeepingActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_housekeeping);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}