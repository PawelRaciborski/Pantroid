package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Flowable
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.db.Repository

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface GetAllPantryItemsUsecase : FlowableUsecase<List<PantryItem>>

class GetAllPantryItemsUsecaseImpl(
        private val repository: Repository
) : GetAllPantryItemsUsecase {
    override fun execute(): Flowable<List<PantryItem>> =
            repository.getAllItems()
}