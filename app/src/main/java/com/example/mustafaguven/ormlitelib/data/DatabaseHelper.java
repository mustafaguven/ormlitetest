package com.example.mustafaguven.ormlitelib.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.mustafaguven.ormlitelib.fakeLogger.FakeLogger;
import com.example.mustafaguven.ormlitelib.model.SystemLog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MustafaGuven on 9.3.2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;
    private static Context mContext;

    private List<Class> mTableList;
    public List<Class> getTableList() {
        if(mTableList==null){
            mTableList = new ArrayList<>();
            mTableList.add(SystemLog.class);
        }
        return mTableList;
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static DatabaseHelper getDBHelper(){
        return OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
    }

    RuntimeExceptionDao<SystemLog, Integer> systemLogDao = null;
    public RuntimeExceptionDao<SystemLog, Integer> getSystemLogDao(){
        if(systemLogDao==null){
            systemLogDao = getRuntimeExceptionDao(SystemLog.class);
        }
        return systemLogDao;
    }



    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class clz : getTableList()) {
                TableUtils.createTable(connectionSource, clz);
            }
        } catch (SQLException e) {
            FakeLogger.e(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for (Class clz : getTableList()) {
                TableUtils.dropTable(connectionSource, clz, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            FakeLogger.e(e.getMessage());
        }
    }
}
