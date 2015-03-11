package com.example.mustafaguven.ormlitelib;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mustafaguven.ormlitelib.data.DatabaseHelper;
import com.example.mustafaguven.ormlitelib.model.SystemLog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        RuntimeExceptionDao<SystemLog, Integer> systemLogDao = dbHelper.getSystemLogDao();
        for (int i = 0; i < 3; i++) {
            systemLogDao.create(new SystemLog("name " + i, "description " + i, System.currentTimeMillis()));
        }

        List<SystemLog> logs = systemLogDao.queryForAll();
        for (int i = 0; i < logs.size(); i++) {
            Log.e("", String.format("%s --> %s", logs.get(i).name, logs.get(i).description ));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
