package pl.pawelraciborski.pantroid.model.db

import io.reactivex.Single
import pl.pawelraciborski.pantroid.model.PantryItem

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface Repository {
    fun insertPantryItem(pantryItem: PantryItem): Single<Long>
    fun getItemById(id: Long): Single<PantryItem>
}

class RepositoryImpl(
        private val dao: PantryDao
) : Repository {
    override fun getItemById(id: Long): Single<PantryItem> =
            dao
                    .getPantryItemById(id)
                    .firstOrError()

    override fun insertPantryItem(pantryItem: PantryItem): Single<Long> =
            Single
                    .fromCallable {
                        dao.insertPantryItem(pantryItem)
                    }
}