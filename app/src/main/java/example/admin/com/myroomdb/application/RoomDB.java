package example.admin.com.myroomdb.application;

import android.app.Application;
import android.arch.persistence.room.Room;

import example.admin.com.myroomdb.db.AppDatabase;

public class RoomDB extends Application {

    private static RoomDB roomDB = new RoomDB();
    private static AppDatabase appDatabase;


    public static RoomDB getInstance(){
        return roomDB;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

    }
}
