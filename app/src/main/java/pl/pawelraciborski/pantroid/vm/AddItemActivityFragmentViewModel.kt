package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.defaultSubscribe
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 14.09.2018.
 */
class AddItemActivityFragmentViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        private val insertPantryItemUsecase: InsertPantryItemUsecase
) : ViewModel() {
    val name = MutableLiveData<String>()
    val quantity = MutableLiveData<String>()

    fun onSave() {
        name.value?.let {
            compositeDisposable += insertPantryItemUsecase
                    .init(it, quantity.value?.toIntOrNull() ?: 0)
                    .execute()
                    .defaultSubscribe { t1, _ ->
                        // TODO: handle this!!
                        print(t1)
                    }
        }
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

    fun init(itemId: Int) {
        //TODO: handle initialization
    }
}