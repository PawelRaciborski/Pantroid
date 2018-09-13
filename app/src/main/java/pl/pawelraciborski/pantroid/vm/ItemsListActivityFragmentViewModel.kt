package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import pl.pawelraciborski.pantroid.model.PantryItem
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecase
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import java.util.*
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */

class ItemsListActivityFragmentViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        private val random: Random,
        insertPantryItemUsecase: InsertPantryItemUsecase,
        getAllPantryItemsUsecase: GetAllPantryItemsUsecase
) : ViewModel() {

    val items: MutableLiveData<List<PantryListItem>> = MutableLiveData()

    init {
        compositeDisposable +=
                getAllPantryItemsUsecase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            items.postValue(it.map {
                                PantryListItem(it.id!!, it.name, it.quantity)
                            })
                        }

        compositeDisposable +=
                insertPantryItemUsecase
                        .init(PantryItem("Item ${random.nextInt()}", random.nextInt()))
                        .execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { result, exception ->
                            //TODO: add logs here
                        }
    }


    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}