package com.example.d1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_sex;
    private TextView txv_single_show, txn_check_show;
    private CheckBox cb_water, cb_drinks;
    private Button btn_check_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rg_sex = findViewById(R.id.rg_sex);
        txv_single_show = findViewById(R.id.txv_single_show);
        txn_check_show = findViewById(R.id.txv_check_show);
        cb_water = findViewById(R.id.cb_water);
        cb_drinks = findViewById(R.id._drink);
        btn_check_send = findViewById(R.id.btn_check_send);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (radioGroup.getCheckedRadioButtonId() == R.id.btn_male){
                            txv_single_show.setText("男");
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.btn_female){
                            txv_single_show.setText("女");
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.btn_third){
                            txv_single_show.setText("第三性");
                        }
                    }
                });
        btn_check_send.setOnClickListener(view->{
            if(cb_drinks.isChecked() || cb_water.isChecked()){
                if(cb_water.isChecked()){
                    String originalText = txn_check_show.getText().toString();
                    txn_check_show.setText("");
                    if(originalText.equals("未選")){
                        txn_check_show.setText("喜歡喝水");
                    } else {
                        txn_check_show.setText(originalText+"、喜歡喝水");
                    }
                }
                if(cb_drinks.isChecked()){
                    String originalText = txn_check_show.getText().toString();
                    txn_check_show.setText("");
                    if(originalText.equals("未選")){
                        txn_check_show.setText("喜歡喝飲料");
                    } else {
                        txn_check_show.setText(originalText+"、喜歡喝飲料");
                    }
                }
            }
            else {
                txn_check_show.setText("未選");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}