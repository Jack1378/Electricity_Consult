package com.example.lenovo_pc.electricity_consult;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * name ：李飞宇
 * Date: 2017/7/4
 * desc:
 */

public class SecondActivity extends Activity implements View.OnClickListener {
    private Button bu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        initView();
    }

    private void initView() {
        bu = (Button) findViewById(R.id.bu);

        bu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu:

                break;
        }
    }
}
