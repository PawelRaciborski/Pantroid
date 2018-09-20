package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.pawelraciborski.pantroid.model.db.Repository
import pl.pawelraciborski.pantroid.model.usecase.*

/**
 * Created by Pawel Raciborski on 14.09.2018.
 */

@Module
class UsecasesModule {
    @Provides
    fun provideInsertPantryItemUsecase(repository: Repository): InsertPantryItemUsecase =
            InsertPantryItemUsecaseImpl(repository)

    @Provides
    fun provideGetAllPantryItemsUsecase(repository: Repository): GetAllPantryItemsUsecase =
            GetAllPantryItemsUsecaseImpl(repository)

    @Provides
    fun provideGetItemUsecase(repository: Repository): GetItemUsecase =
            GetItemUsecaseImpl(repository)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}