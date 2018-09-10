package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Single
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.db.Repository

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface InsertPantryItemUsecase : Usecase<PantryItem>

class InsertPantryItemUsecaseImpl(
        private val repository: Repository
) : InsertPantryItemUsecase {
    override fun execute(): Single<PantryItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}