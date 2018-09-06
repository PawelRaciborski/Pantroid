package pl.pawelraciborski.pantroid.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.pawelraciborski.pantroid.PantroidApplication
import javax.inject.Singleton

/**
 * Created by Pawel Raciborski on 30.08.2018.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class
])
interface AppComponent : AndroidInjector<PantroidApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PantroidApplication>()
}