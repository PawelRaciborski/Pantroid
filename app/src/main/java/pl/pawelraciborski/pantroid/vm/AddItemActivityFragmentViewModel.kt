package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.usecase.GetItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.UpdatePantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.defaultSubscribe
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 14.09.2018.
 */
class AddItemActivityFragmentViewModel @Inject constructor(
        compositeDisposable: CompositeDisposable,
        private val insertPantryItemUsecase: InsertPantryItemUsecase,
        private val getItemUsecase: GetItemUsecase,
        private val updatePantryItemUsecase: UpdatePantryItemUsecase
) : BaseViewModel<AddItemActivityFragmentViewModel.NavigationEvent, Any>(compositeDisposable) {

    companion object {
        private const val EMPTY_STRING = ""
    }

    private var pantryItem: PantryItem? = null

    private var editModeEnabled = false

    enum class NavigationEvent {
        BACK,
        DISPLAY_SAVE_ERROR
    }

    val name = MutableLiveData<String>()
    val quantity = MutableLiveData<String>()
    val canBeSaved = MutableLiveData<Boolean>()

    private val hasValueChanged: Boolean
        get() {
            if (!editModeEnabled) {
                return true
            }

            return pantryItem!!.name != name.value || pantryItem!!.quantity != quantityValue
        }


    private val quantityValue
        get() = quantity.value?.toIntOrNull() ?: 0

    private val nameValue
        get() = name.value ?: EMPTY_STRING

    fun onSave() {
        if (editModeEnabled) {
            pantryItem?.let {
                compositeDisposable += updatePantryItemUsecase
                        .init(it.update(nameValue, quantityValue))
                        .execute()
                        .defaultSubscribe { _, throwable ->
                            handleSaveResult(throwable)
                        }
            }
        } else {
            name.value?.let { itemName ->
                compositeDisposable += insertPantryItemUsecase
                        .init(itemName, quantityValue)
                        .execute()
                        .defaultSubscribe { _, throwable ->
                            handleSaveResult(throwable)
                        }
            }
        }
    }

    private fun handleSaveResult(throwable: Throwable?) {
        throwable?.let {
            postUiEvent(UiEvent(NavigationEvent.DISPLAY_SAVE_ERROR))
        }
        postUiEvent(UiEvent(NavigationEvent.BACK))
    }

    fun onTextChanged() {
        canBeSaved.value = hasValueChanged
    }

    fun init(itemId: Long) {
        compositeDisposable += getItemUsecase
                .init(itemId)
                .execute()
                .defaultSubscribe {
                    editModeEnabled = true
                    pantryItem = it
                    name.value = it.name
                    quantity.value = it.quantity.toString()
                    canBeSaved
                }
    }

    private fun PantryItem.update(name: String, quantity: Int): PantryItem = apply {
        this.name = name
        this.quantity = quantity
    }
}