package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.Provides
import pl.pawelraciborski.pantroid.PantroidApplication
import pl.pawelraciborski.pantroid.model.db.PantroidDatabase
import pl.pawelraciborski.pantroid.model.db.PantryDao
import pl.pawelraciborski.pantroid.model.db.Repository
import pl.pawelraciborski.pantroid.model.db.RepositoryImpl
import javax.inject.Singleton

/**
 * Created by Pawel Raciborski on 30.08.2018.
 */
@Module(includes = [
    ViewModelModule::class,
    UsecasesModule::class
])
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(dao: PantryDao): Repository =
            RepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideDao(database: PantroidDatabase) = database.pandryDao()

    @Provides
    @Singleton
    fun provideDatabase(application: PantroidApplication) =
            PantroidDatabase.getInstance(application)!!
}