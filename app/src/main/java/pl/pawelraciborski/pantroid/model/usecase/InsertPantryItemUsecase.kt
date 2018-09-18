package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Single
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.db.Repository

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface InsertPantryItemUsecase : Usecase<PantryItem> {
    fun init(name: String, quantity: Int): InsertPantryItemUsecase
}

class InsertPantryItemUsecaseImpl(
        private val repository: Repository
) : InsertPantryItemUsecase {
    private lateinit var pantryItem: PantryItem

    override fun init(name: String, quantity: Int): InsertPantryItemUsecase {
        this.pantryItem = PantryItem(name, quantity)
        return this
    }

    override fun execute(): Single<PantryItem> =
            repository
                    .insertPantryItem(pantryItem)
                    .flatMap {
                        repository.getItemById(it)
                                .firstOrError()
                    }
}