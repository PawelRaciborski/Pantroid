package pl.pawelraciborski.pantroid.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pawel Raciborski on 30.08.2018.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) = application
}