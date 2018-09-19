package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecase
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */

class ItemsListActivityFragmentViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        getAllPantryItemsUsecase: GetAllPantryItemsUsecase
) : ViewModel() {

    val items: MutableLiveData<List<PantryListItem>> = MutableLiveData()

    init {
        compositeDisposable +=
                getAllPantryItemsUsecase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { result ->
                            items.postValue(
                                    result.map {
                                        PantryListItem(it.id!!, it.name, it.quantity)
                                    })
                        }
    }


    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}