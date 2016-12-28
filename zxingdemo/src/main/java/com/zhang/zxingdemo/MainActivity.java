package com.zhang.zxingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.CaptureActivity;
import com.google.zxing.encode.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;
    private EditText etInput;
    private ImageView ivQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.show);
        etInput = (EditText) findViewById(R.id.input);
        ivQR = (ImageView) findViewById(R.id.iv);

    }

    public void scan(View view){
        Toast.makeText(this, "可以开始扫描", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent,0);
    }

    public void gene(View view){
        String input=etInput.getText().toString();
        if (TextUtils.isEmpty(input)) {

        }
        else {
            try {

                Bitmap qrCode = EncodingUtils.createQRCode(input,400);
                ivQR.setImageBitmap(qrCode);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            String result=data.getStringExtra("result");
            tvShow.setText(result);
        }
    }

}
