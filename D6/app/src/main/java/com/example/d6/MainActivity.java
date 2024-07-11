package com.example.d6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView txv_selected;//顯示文字
    private Spinner spinner;//下拉式選單
    private Boolean first_state=true;//判斷是否第一次選(因為首次進入會直接跳顯示)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = findViewById(R.id.spinner);
        txv_selected = findViewById(R.id.txv_selected);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.day_array, android.R.layout.simple_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Selected", "Yes");
                String str = adapterView.getSelectedItem().toString();
                if (!first_state && !str.equals("請選擇")){
                    txv_selected.setText(str);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                }
                else{
                    first_state = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //較少call到
                Log.d("Selected","No");
            }
        });
    }
}