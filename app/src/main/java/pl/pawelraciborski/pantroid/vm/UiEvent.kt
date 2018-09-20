package pl.pawelraciborski.pantroid.vm

/**
 * Created by Pawel Raciborski on 20.09.2018.
 */
class UiEvent<T, U>(
        val type: T,
        val value: U? = null
)

infix fun <A, B> A.toEventWith(that: B) = UiEvent(this, that)