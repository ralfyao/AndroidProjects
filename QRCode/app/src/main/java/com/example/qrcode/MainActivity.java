package com.example.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.Result;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private Bitmap bitmap;
    private ImageView iv_qrcode;
    private Button btn_scan, btn_encode;
    private EditText et_encode;
    private TextView QRResult;
    ZXingScannerView scannerView;
    BarcodeEncoder barcodeEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn_scan = findViewById(R.id.btn_scan);
        btn_encode = findViewById(R.id.btn_encode);
        iv_qrcode = findViewById(R.id.iv_qrcode);
        et_encode = findViewById(R.id.et_encode);
        QRResult = findViewById(R.id.txv_result);
        scannerView = findViewById(R.id.scannerView);
    }

    @Override
    public void handleResult(Result result) {

    }
}