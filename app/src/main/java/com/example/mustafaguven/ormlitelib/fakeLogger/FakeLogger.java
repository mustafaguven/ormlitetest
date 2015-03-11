package com.example.mustafaguven.ormlitelib.fakeLogger;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by MustafaGuven on 9.3.2015.
 */
public class FakeLogger {
    public static void e(String x){
        Log.e("", !TextUtils.isEmpty(x) ? x : "");
    }
}
