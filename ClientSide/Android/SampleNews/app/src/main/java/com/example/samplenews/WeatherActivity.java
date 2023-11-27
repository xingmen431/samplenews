package com.example.samplenews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather); // 设置活动的布局文件为activity_weather.xml

        // 初始化一个 TextView 控件，用于显示 "今日天气"
        TextView textView = findViewById(R.id.text_view);

        // 设置 TextView 的文本内容为 "今日天气"
        textView.setText("今日天气");
    }
}