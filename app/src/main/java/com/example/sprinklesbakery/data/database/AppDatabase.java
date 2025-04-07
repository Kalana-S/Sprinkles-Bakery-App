package com.example.sprinklesbakery.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.sprinklesbakery.data.dao.MemberDao;
import com.example.sprinklesbakery.data.model.Member;
import com.example.sprinklesbakery.data.dao.CategoryDao;
import com.example.sprinklesbakery.data.model.Category;
import com.example.sprinklesbakery.data.dao.CupcakeDao;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.data.dao.OrderDao;
import com.example.sprinklesbakery.data.model.Order;

@Database(entities = {Category.class, Member.class, Cupcake.class, Order.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract MemberDao memberDao();
    public abstract CategoryDao categoryDao();
    public abstract CupcakeDao cupcakeDao();
    public  abstract  OrderDao orderDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "sprinkles_bakery_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}