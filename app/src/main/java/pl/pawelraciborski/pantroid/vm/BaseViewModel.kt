package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pawel Raciborski on 20.09.2018.
 */
abstract class BaseViewModel<T, U>(protected val compositeDisposable: CompositeDisposable) : ViewModel() {
    val navigationEventLiveData = SingleLiveData<UiEvent<T, U>>()

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

    protected fun postUiEvent(event: UiEvent<T, U>) {
        navigationEventLiveData.value = event
    }
}