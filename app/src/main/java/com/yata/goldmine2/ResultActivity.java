package com.yata.goldmine2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView result;
    Button restart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        result = findViewById(R.id.result);
        restart = findViewById(R.id.restart);
        Intent intent = getIntent();
        int goldCount = intent.getIntExtra("gold_count", -1);
        result.setText("Score: " + goldCount);
        restart.setOnClickListener(v -> {
            Intent intent1 = new Intent(ResultActivity.this, MainActivity.class);
            getSharedPreferences("my_preference", Context.MODE_PRIVATE)
                    .edit()
                    .putInt("save_mine_count_2", 0)
                    .putInt("gold_count", 0)
                    .commit();
            startActivity(intent1);
            finish();
        });
    }
}
