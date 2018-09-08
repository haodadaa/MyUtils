package com.hao.myutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hao.myutils.util.AssetUtil;

public class MainActivity extends AppCompatActivity {

    private TextView mTvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mTvMain = findViewById(R.id.tv_main);
    }

    private void initData() {
        String assetString = AssetUtil.getAsset2String(this, "vecShader.glsl");
        if (assetString != null) {
            mTvMain.setText(assetString);
        }
    }
}
