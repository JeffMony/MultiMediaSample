package com.jeffmony.multimediademo.surface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeffmony.multimediademo.R;

/**
 * 使用 SurfaceView 绘制一张图片
 *
 * @author Richie on 2018.10.22
 */
public class SurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
    }

}
