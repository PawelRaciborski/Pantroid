package pl.pawelraciborski.pantroid.model.db

import io.reactivex.Flowable
import io.reactivex.Single
import pl.pawelraciborski.pantroid.model.PantryItem

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface Repository {
    fun insertPantryItem(pantryItem: PantryItem): Single<Long>
    fun getItemById(id: Long): Flowable<PantryItem>
    fun getAllItems(): Flowable<List<PantryItem>>
}

class RepositoryImpl(
        private val dao: PantryDao
) : Repository {
    override fun getAllItems(): Flowable<List<PantryItem>> {
        return dao.getAllPantryItems()
    }

    override fun getItemById(id: Long): Flowable<PantryItem> =
            dao.getPantryItemById(id)

    override fun insertPantryItem(pantryItem: PantryItem): Single<Long> =
            Single.fromCallable {
                dao.insertPantryItem(pantryItem)
            }
}