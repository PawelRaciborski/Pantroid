package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import pl.pawelraciborski.pantroid.model.usecase.GetItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.defaultSubscribe
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 14.09.2018.
 */
class AddItemActivityFragmentViewModel @Inject constructor(
        compositeDisposable: CompositeDisposable,
        private val insertPantryItemUsecase: InsertPantryItemUsecase,
        private val getItemUsecase: GetItemUsecase
) : BaseViewModel<AddItemActivityFragmentViewModel.NavigationEvent, Any>(compositeDisposable) {

    enum class NavigationEvent {
        BACK,
        DISPLAY_SAVE_ERROR
    }

    val name = MutableLiveData<String>()
    val quantity = MutableLiveData<String>()

    fun onSave() {
        name.value?.let { itemName ->
            compositeDisposable += insertPantryItemUsecase
                    .init(itemName, quantity.value?.toIntOrNull() ?: 0)
                    .execute()
                    .defaultSubscribe { retult, throwable ->
                        throwable?.let {
                            postUiEvent(UiEvent(NavigationEvent.DISPLAY_SAVE_ERROR))
                        }
                        postUiEvent(UiEvent(NavigationEvent.BACK))
                    }
        }
    }


    fun init(itemId: Long) {
        compositeDisposable += getItemUsecase
                .init(itemId)
                .execute()
                .defaultSubscribe {
                    name.value = it.name
                    quantity.value = it.quantity.toString()
                }
    }
}