package com.example.yanglaocommunity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShoppingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // 返回按钮
        findViewById(R.id.iv_back_shopping).setOnClickListener(v -> finish());

        // 加入购物车/付款
        Button btnAdd = findViewById(R.id.btn_add_cart);
        btnAdd.setOnClickListener(v -> {
            Toast.makeText(this, "已加入购物车，可前往订单页结算", Toast.LENGTH_LONG).show();
            // 跳转到订单页
            startActivity(new Intent(this, OrderActivity.class));
            finish();
        });

        // 底部导航
        findViewById(R.id.nav_home).setOnClickListener(v -> { startActivity(new Intent(this, MainActivity.class)); finish(); });
        findViewById(R.id.nav_order).setOnClickListener(v -> { startActivity(new Intent(this, OrderActivity.class)); finish(); });
        findViewById(R.id.nav_setting).setOnClickListener(v -> { startActivity(new Intent(this, SettingsActivity.class)); finish(); });
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
//public class ShoppingActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_shopping);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}