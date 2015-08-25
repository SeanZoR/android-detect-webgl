package com.github.seanzor.webgl.detect.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.seanzor.webgl.detect.OnReceiveDetectJsResult;
import com.github.seanzor.webgl.detect.WebGLDetector;
import com.github.seanzor.webgl.detect.WebGLSupportLevel;
import com.github.seanzor.webgl.detect.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BindableSupportLevel mBindableSupportLevel = new BindableSupportLevel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This is the binding to the layout
        // read more @ https://developer.android.com/tools/data-binding/guide.html#binding_data
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set the initial result
        binding.setResult(mBindableSupportLevel);

        // Trigger the check
        WebGLDetector.detect(this, new OnReceiveDetectJsResult() {
            @Override
            public void onReceiveDetectJsResult(WebGLSupportLevel supportLevel) {
                mBindableSupportLevel.setSupportLevel(supportLevel);
            }
        });
    }
}