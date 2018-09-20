package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */
interface Usecase<T> {
    fun execute(): Single<T>
}

interface FlowableUsecase<T> {
    fun execute(): Flowable<T>
}

inline fun <reified T> Single<T>.defaultSubscribe(noinline callback: (T, Throwable?) -> Unit): Disposable =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)

inline fun <reified T> Flowable<T>.defaultSubscribe(noinline callback: (T) -> Unit): Disposable =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
