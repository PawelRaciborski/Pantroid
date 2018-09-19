package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { result, exception ->
                        // TODO: handle this!!
                    }
        }
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}