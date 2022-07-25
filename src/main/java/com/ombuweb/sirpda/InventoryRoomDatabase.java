package com.ombuweb.sirpda;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Database(entities = {Inventory.class, Product.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class InventoryRoomDatabase  extends RoomDatabase {

    private static InventoryRoomDatabase INSTANCE;
    public abstract InventoryDao inventoryDao();
    public abstract ProductDao productDao();
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    //new PopulateDbAsync(INSTANCE).execute();
                    InventoryDao inventoryDao = INSTANCE.inventoryDao();
                    Date fecha = new Date();
                    Long hora = fecha.getTime();
                    Inventory inventory = new Inventory(
                            fecha, hora,"miAlmancen","DEscription",
                            "Nandee"
                    );
                    //inventoryDao.insert(inventory);
                    //inventoryDao.deleteAll();
                }
            };

    public static InventoryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryRoomDatabase.class,"inventory:database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                    .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Populate the database in the background.
     */
    /*private static class PopulateDbAsync extends AsyncTask<Void, Void,Long> {

        private final InventoryDao mDao;


        PopulateDbAsync(InventoryRoomDatabase db) {
            mDao = db.inventoryDao();
        }

        @Override
        protected Long doInBackground(final Void... params) {

            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            Date fecha = new Date();
            Long hora = fecha.getTime();
Inventory inventory = new Inventory(
        fecha, hora,"miAlmancen","DEscription",
        "Nandee"
        );
            Log.i("ROOMD","HELLO");
            Long i = mDao.insert(inventory);
            return  i;
        }

        protected void onPostExecute(Integer integer) {
            Log.i("ROOM", (integer).toString());
        }
    }*/
}
