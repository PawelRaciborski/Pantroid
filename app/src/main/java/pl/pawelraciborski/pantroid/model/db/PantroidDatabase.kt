package pl.pawelraciborski.pantroid.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.pawelraciborski.pantroid.model.PantryItem

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

@Database(
        entities = [PantryItem::class],
        version = 1
)
abstract class PantroidDatabase : RoomDatabase() {
    companion object {
        private var instacne: PantroidDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PantroidDatabase? {
            if (instacne == null) {
                instacne = Room.databaseBuilder(
                        context.applicationContext,
                        PantroidDatabase::class.java,
                        "pantroid.db"
                ).build()
            }
            return instacne
        }
    }

    abstract fun pandryDao(): PantryDao
}