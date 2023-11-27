package com.example.samplenews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置该活动的布局文件为activity_coupon.xml
        setContentView(R.layout.activity_coupon);

        // 初始化一个 TextView 控件，它用于显示优惠券信息
        TextView textView = findViewById(R.id.text_view);

        // 设置 TextView 的文本内容为 "优惠券"
        textView.setText("优惠券");
    }
}