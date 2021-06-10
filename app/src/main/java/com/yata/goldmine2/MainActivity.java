package com.yata.goldmine2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int mineCount = 0;

    TextView title;
    TextView result;
    SharedPreferences sharedPreferences;

    int goldCount = 0;

    @Override
    protected void onResume() {
        super.onResume();
        save();
    }

    private void save() {
        Log.d("phong", "save data");
        sharedPreferences
                .edit()
                .putInt("save_mine_count_2", mineCount)
                .putInt("gold_count", goldCount)
                .commit();
        Log.d("phong", "saved");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = Utility.getMainSharePreferences(this);
        title = findViewById(R.id.mine_count_text_view);
        result = findViewById(R.id.result);

        // activity lifecycle
        int oldMineCount = sharedPreferences.getInt("save_mine_count_2", -1);
        if (oldMineCount != -1) {
            mineCount = oldMineCount;
            goldCount = sharedPreferences.getInt("gold_count", 0);
            invalidate();
        }

        invalidate();

        Button mineButton = findViewById(R.id.mine_button);
        mineButton.setOnClickListener(v -> {
            mineCount = mineCount + 1;
            Random random = new Random();
            int number = Math.abs(random.nextInt()) % 100; // 0 99
            if (number < 33) {
                goldCount++;
            }
            invalidate();
        });
    }

    public void invalidate() {
        if (mineCount > 9) {
            Intent intent = ResultActivity.starterIntent(this, goldCount);
            startActivity(intent);
            finish();
        }
        title.setText("Mined " + mineCount + " times");
        result.setText("Gold count: " + goldCount);
    }

}
