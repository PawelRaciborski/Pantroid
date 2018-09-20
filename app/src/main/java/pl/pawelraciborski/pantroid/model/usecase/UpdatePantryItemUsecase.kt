package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Single
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.db.Repository

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface UpdatePantryItemUsecase : Usecase<Int> {
    fun init(item: PantryItem): UpdatePantryItemUsecase
}

class UpdatePantryItemUsecaseImpl(
        private val repository: Repository
) : UpdatePantryItemUsecase {
    override fun init(item: PantryItem): UpdatePantryItemUsecase {
        this.pantryItem = item
        return this
    }

    private lateinit var pantryItem: PantryItem

    override fun execute(): Single<Int> =
            repository.updatePantryItem(pantryItem)

}