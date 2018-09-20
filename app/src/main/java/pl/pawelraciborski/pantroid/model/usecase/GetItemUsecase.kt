package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Flowable
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.db.Repository

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */

interface GetItemUsecase : FlowableUsecase<PantryItem> {
    fun init(id: Long): GetItemUsecase
}

class GetItemUsecaseImpl(
        private val repository: Repository
) : GetItemUsecase {
    private var id: Long = -1L

    override fun init(id: Long): GetItemUsecase {
        this.id = id
        return this
    }

    override fun execute(): Flowable<PantryItem> =
            repository.getItemById(id)
}