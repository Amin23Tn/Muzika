package tn.example.muzika.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {user.class,Post.class,friendships.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract UserDao userDao();
    public abstract PostDao postDao();
    public abstract FriendsDao friendsDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "muzika")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
