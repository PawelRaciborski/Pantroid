package pl.pawelraciborski.pantroid.model.usecase

import io.reactivex.Single

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */
interface Usecase<T> {
    fun execute(): Single<T>
}