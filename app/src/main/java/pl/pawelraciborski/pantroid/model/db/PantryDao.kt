package pl.pawelraciborski.pantroid.model.db

import android.arch.persistence.room.*
import io.reactivex.Flowable
import pl.pawelraciborski.pantroid.model.PantryItem

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

@Dao
interface PantryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPantryItem(pantryItem: PantryItem): Long

    @Update
    fun updatePantryItem(pantryItem: PantryItem): Int

    @Query("SELECT * from pantryitem")
    fun getAllPantryItems(): Flowable<List<PantryItem>>

    @Query("SELECT * FROM PantryItem WHERE id =:id")
    fun getPantryItemById(id: Long): Flowable<PantryItem>
}