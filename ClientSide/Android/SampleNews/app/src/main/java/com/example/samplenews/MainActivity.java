package com.example.samplenews;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewNews;

    private RecyclerView recyclerViewNews;
    private NewsAdapter newsAdapter;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 使用Firebase Cloud Messaging获取设备的令牌
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // 获取新的FCM注册令牌
                        String token = task.getResult();

                        // 记录和显示令牌
                        Log.d(TAG, token);
                    }
                });

        // 新闻API的URL
        String url = "http://192.168.11.13:3000/api/items";

        // 创建一个StringRequest以获取提供的URL的字符串响应
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 解析响应并显示新闻
                        displayNews(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 在网络请求失败时更新textViewNews
                textViewNews.setText("That didn't work!");
            }
        });

        // 将请求添加到请求队列中
        Volley.newRequestQueue(this).add(stringRequest);

        // 底部导航视图的处理
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    // TODO: 切换到首页Fragment或Activity
                    // 如果点击的是当前活动，不做任何操作
                    return true;
                } else if (id == R.id.weather) {
                    intent = new Intent(MainActivity.this, WeatherActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.coupon) {
                    intent = new Intent(MainActivity.this, CouponActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.my_set) {
                    intent = new Intent(MainActivity.this, MySettingActivity.class);
                    startActivity(intent);
                    return true;
                }
                // 其他if-else处理其他导航项
                return false;
            }
        });
    }

    // 解析JSON响应并更新RecyclerView
    private void displayNews(String response) {
        try {
            recyclerViewNews = findViewById(R.id.recyclerViewNews);

            // 将字符串转换为JSONObject
            JSONArray jsonArray = new JSONArray(response);

            newsList = new ArrayList<>();
            // 假设newsList已经通过网络请求填充数据
            // 遍历JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                // 获取每个JSON对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 从JSON对象中提取数据
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String imageUrl = jsonObject.getString("imageUrl");

                // 创建News对象并添加到列表中
                News news = new News(title, content, imageUrl);
                newsList.add(news);
            }

            // 使用NewsAdapter设置RecyclerView
            newsAdapter = new NewsAdapter(this, newsList);
            recyclerViewNews.setAdapter(newsAdapter);
            recyclerViewNews.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
            // 在发生错误的情况下，可以在textViewNews中显示错误信息
            textViewNews.setText("Error parsing JSON");
        }
    }
}