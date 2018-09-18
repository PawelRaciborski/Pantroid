package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.pawelraciborski.pantroid.model.db.Repository
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecase
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecaseImpl
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecaseImpl

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
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}