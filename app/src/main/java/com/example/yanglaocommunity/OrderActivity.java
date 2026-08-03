package com.example.yanglaocommunity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 订单页面
 * 功能：底部导航切换、订单筛选（全部/已完成/未完成）、订单按钮点击反馈
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    // 1. 筛选栏控件
    private TextView tvOrderAll;
    private TextView tvOrderCompleted;
    private TextView tvOrderUnfinished;

    // 2. 订单列表容器
    private LinearLayout llOrderList;

    // 3. 订单子项（为了精准筛选，需单独声明每个订单的状态控件）
    private TextView tvStatus1; // 未完成
    private TextView tvStatus2; // 已完成
    private TextView tvStatus3; // 未完成

    private LinearLayout llOrderItem1;
    private LinearLayout llOrderItem2;
    private LinearLayout llOrderItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // 初始化所有控件
        initViews();

        // 初始化底部导航选中状态（默认选中订单）
        setNavSelected(R.id.nav_order);

        // 默认显示全部订单
        showAllOrders();
    }

    /**
     * 初始化所有控件ID和点击事件
     */
    private void initViews() {
        // 绑定筛选栏
        tvOrderAll = findViewById(R.id.tv_order_all);
        tvOrderCompleted = findViewById(R.id.tv_order_completed);
        tvOrderUnfinished = findViewById(R.id.tv_order_unfinished);

        // 绑定订单列表容器
        llOrderList = findViewById(R.id.ll_order_list);

        // 【关键修复】单独绑定每个订单项及其状态标签
        // 解决同个布局中多个相同ID导致findViewById只获取第一个的问题
        llOrderItem1 = llOrderList.getChildAt(0).findViewById(R.id.ll_order_item);
        tvStatus1 = llOrderItem1.findViewById(R.id.tv_order_status);

        llOrderItem2 = llOrderList.getChildAt(1).findViewById(R.id.ll_order_item);
        tvStatus2 = llOrderItem2.findViewById(R.id.tv_order_status);

        llOrderItem3 = llOrderList.getChildAt(2).findViewById(R.id.ll_order_item);
        tvStatus3 = llOrderItem3.findViewById(R.id.tv_order_status);

        // 绑定订单按钮事件
        llOrderItem1.findViewById(R.id.btn_cancel_order1).setOnClickListener(v ->
                Toast.makeText(this, "订单1：取消订单中...", Toast.LENGTH_SHORT).show());
        llOrderItem2.findViewById(R.id.btn_evaluate_order2).setOnClickListener(v ->
                Toast.makeText(this, "订单2：跳转到评价页", Toast.LENGTH_SHORT).show());
        llOrderItem3.findViewById(R.id.btn_cancel_order3).setOnClickListener(v ->
                Toast.makeText(this, "订单3：取消订单中...", Toast.LENGTH_SHORT).show());

        // 绑定筛选栏点击事件
        tvOrderAll.setOnClickListener(this);
        tvOrderCompleted.setOnClickListener(this);
        tvOrderUnfinished.setOnClickListener(this);

        // 绑定底部导航点击事件
        findViewById(R.id.nav_home).setOnClickListener(this);
        findViewById(R.id.nav_order).setOnClickListener(this);
        findViewById(R.id.nav_setting).setOnClickListener(this);
    }

    /**
     * 点击事件总处理
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        // --- 底部导航逻辑 ---
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainActivity.class));
            finish(); // 关闭当前页面，避免返回键回到订单页
        } else if (id == R.id.nav_order) {
            // 点击自身，重置选中态即可
            setNavSelected(R.id.nav_order);
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }

        // --- 筛选栏逻辑 ---
        if (id == R.id.tv_order_all) {
            setFilterSelected(tvOrderAll);
            showAllOrders();
        } else if (id == R.id.tv_order_completed) {
            setFilterSelected(tvOrderCompleted);
            showCompletedOrders();
        } else if (id == R.id.tv_order_unfinished) {
            setFilterSelected(tvOrderUnfinished);
            showUnfinishedOrders();
        }
    }

    // ===================== 筛选栏样式控制 =====================

    /**
     * 设置筛选栏选中样式
     */
    private void setFilterSelected(TextView selectedTv) {
        // 1. 重置所有为未选中
        resetAllFilters();
        // 2. 设置当前为选中
        selectedTv.setBackgroundTintList(getResources().getColorStateList(R.color.black));
        selectedTv.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 重置筛选栏为默认样式
     */
    private void resetAllFilters() {
        tvOrderAll.setBackgroundTintList(getResources().getColorStateList(R.color.bg_light));
        tvOrderAll.setTextColor(getResources().getColor(R.color.black));

        tvOrderCompleted.setBackgroundTintList(getResources().getColorStateList(R.color.bg_light));
        tvOrderCompleted.setTextColor(getResources().getColor(R.color.black));

        tvOrderUnfinished.setBackgroundTintList(getResources().getColorStateList(R.color.bg_light));
        tvOrderUnfinished.setTextColor(getResources().getColor(R.color.black));
    }

    // ===================== 订单筛选核心逻辑 =====================

    /**
     * 显示全部订单
     */
    private void showAllOrders() {
        llOrderItem1.setVisibility(View.VISIBLE);
        llOrderItem2.setVisibility(View.VISIBLE);
        llOrderItem3.setVisibility(View.VISIBLE);
    }

    /**
     * 只显示已完成订单
     */
    private void showCompletedOrders() {
        // 根据状态标签的文本进行判断
        llOrderItem1.setVisibility(tvStatus1.getText().equals("已完成") ? View.VISIBLE : View.GONE);
        llOrderItem2.setVisibility(tvStatus2.getText().equals("已完成") ? View.VISIBLE : View.GONE);
        llOrderItem3.setVisibility(tvStatus3.getText().equals("已完成") ? View.VISIBLE : View.GONE);
    }

    /**
     * 只显示未完成订单
     */
    private void showUnfinishedOrders() {
        llOrderItem1.setVisibility(tvStatus1.getText().equals("未完成") ? View.VISIBLE : View.GONE);
        llOrderItem2.setVisibility(tvStatus2.getText().equals("未完成") ? View.VISIBLE : View.GONE);
        llOrderItem3.setVisibility(tvStatus3.getText().equals("未完成") ? View.VISIBLE : View.GONE);
    }

    // ===================== 底部导航样式控制 =====================

    /**
     * 设置底部导航选中态
     */
    private void setNavSelected(int navId) {
        resetNavUnselected();
        View navView = findViewById(navId);
        navView.setBackgroundResource(R.drawable.bg_nav_selected);
        // 找到导航内的文字并改色
        ((TextView) navView.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 重置底部导航为未选中态
     */
    private void resetNavUnselected() {
        // 首页
        View navHome = findViewById(R.id.nav_home);
        navHome.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navHome.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));

        // 订单
        View navOrder = findViewById(R.id.nav_order);
        navOrder.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navOrder.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));

        // 设置
        View navSetting = findViewById(R.id.nav_setting);
        navSetting.setBackgroundResource(R.drawable.bg_nav_unselected);
        ((TextView) navSetting.findViewById(R.id.tv_nav_text)).setTextColor(getResources().getColor(R.color.black));
    }
}
//package com.example.yanglaocommunity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.yanglaocommunity.MainActivity;
//import com.example.yanglaocommunity.R;
//
//public class OrderActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);
//
//        findViewById(R.id.nav_home).setOnClickListener(v -> {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        });
//        findViewById(R.id.nav_order).setOnClickListener(v -> {});
//        findViewById(R.id.nav_settings).setOnClickListener(v -> {
//            startActivity(new Intent(this, SettingsActivity.class));
//            finish();
//        });
//    }
//}
