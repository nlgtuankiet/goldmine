package com.yata.goldmine2;

import android.content.Context;
import android.content.SharedPreferences;

public class Utility {
    public static SharedPreferences getMainSharePreferences(Context context) {
        return context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }
}
