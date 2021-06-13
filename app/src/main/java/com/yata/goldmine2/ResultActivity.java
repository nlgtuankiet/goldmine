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
        int goldCount = intent.getIntExtra(GOLD_COUNT_EXTRA, -1);
        result.setText("Score: " + goldCount);
        restart.setOnClickListener(v -> {
            Intent intent1 = new Intent(ResultActivity.this, MainActivity.class);
            Utility.getMainSharePreferences(this)
                    .edit()
                    .putInt("save_mine_count_2", 0)
                    .putInt("gold_count", 0)
                    .commit();
            // them hinh anh
            // them comment
            startActivity(intent1);
            finish();
        });
    }

    public static Intent starterIntent(Context context, int goldCount) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(GOLD_COUNT_EXTRA, goldCount);
        return intent;
    }

    private static final String GOLD_COUNT_EXTRA = "gold_count";
}
