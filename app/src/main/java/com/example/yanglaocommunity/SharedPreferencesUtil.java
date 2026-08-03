package com.example.yanglaocommunity;

import android.content.Context;
import android.content.SharedPreferences;

// 适配你已有UserInfo的字段：name/phone/memberId
public class SharedPreferencesUtil {

    // 存储文件名（相当于“用户信息记事本”）
    private static final String SP_NAME = "user_info";
    // 登录状态标识
    private static final String KEY_IS_LOGIN = "is_login";
    // 新增：和你UserInfo字段对应的存储key
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_MEMBER_ID = "member_id";

    /**
     * 判断用户是否登录（原有方法，保留）
     */
    public static boolean isLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_LOGIN, false);
    }

    /**
     * 保存登录状态（原有方法，保留）
     */
    public static void setLogin(Context context, boolean isLogin) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.apply();
    }

    /**
     * 新增：保存用户信息（登录成功后调用，比如登录页）
     */
    public static void setUser(Context context, UserInfo userInfo) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        // 把用户信息存入本地存储（和你UserInfo字段对应）
        editor.putString(KEY_USER_NAME, userInfo.getName());
        editor.putString(KEY_USER_PHONE, userInfo.getPhone());
        editor.putString(KEY_MEMBER_ID, userInfo.getMemberId());
        editor.apply();
    }

    /**
     * 完善核心方法：读取用户信息（参数改为Context，所有页面都能调用）
     * 解决你 tvUsername.setText("您好，" + user.getName()) 报错的问题
     */
    public static UserInfo getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        // 创建UserInfo对象，读取本地存储的信息（适配你的字段）
        UserInfo userInfo = new UserInfo();
        // 给默认值，避免空指针报错（适老化设计：默认显示“张大爷”）
        userInfo.setName(sp.getString(KEY_USER_NAME, "张大爷"));
        userInfo.setPhone(sp.getString(KEY_USER_PHONE, "138****5678"));
        userInfo.setMemberId(sp.getString(KEY_MEMBER_ID, "YLA001"));
        return userInfo; // 返回用户信息，能正常调用getName()
    }

    /**
     * 退出登录，清除所有用户信息（原有方法，补充清空逻辑）
     */
    public static void logout(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear(); // 清空所有用户信息
        editor.apply();
    }
}