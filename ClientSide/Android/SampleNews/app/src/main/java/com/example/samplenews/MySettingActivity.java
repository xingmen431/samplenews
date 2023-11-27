package com.example.samplenews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置活动的布局文件为activity_my_setting.xml
        setContentView(R.layout.activity_my_setting);

        // 初始化一个 TextView 控件，它用于显示 "我的设定"
        TextView textView = findViewById(R.id.text_view);

        // 设置 TextView 的文本内容为 "我的设定"
        textView.setText("我的设定");
    }
}