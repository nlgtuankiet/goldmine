package com.yata.goldmine2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int mineCount = 0;

    TextView title;
    SharedPreferences sharedPreferences;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    private void save() {
        Log.d("phong", "save data");
        sharedPreferences.edit()
                .putInt("save_mine_count_2", mineCount)
                .commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE);

        title = findViewById(R.id.mine_count_text_view);

        // activity lifecycle
        int oldMineCount = sharedPreferences.getInt("save_mine_count_2", -1);
        if (oldMineCount != -1) {
            mineCount = oldMineCount;
            invalidate();
        }

        invalidate();

        Button mineButton = findViewById(R.id.mine_button);
        mineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mineCount = mineCount + 1;
                invalidate();
                Log.d("phong", "mine button clicked " + mineCount + " times");
                Log.d("phong", "MainActivity onCreate " + this.hashCode());
            }
        });
        // callback
    }

    public void invalidate() {
        title.setText("Mined " + mineCount + " times");
    }


}
