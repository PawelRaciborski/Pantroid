package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pawel Raciborski on 20.09.2018.
 */
abstract class BaseViewModel(protected val compositeDisposable: CompositeDisposable) : ViewModel() {
    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}