package pl.pawelraciborski.pantroid

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.pawelraciborski.pantroid.di.DaggerAppComponent
/**
 * Created by Pawel Raciborski on 30.08.2018.
 */
class PantroidApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)
}