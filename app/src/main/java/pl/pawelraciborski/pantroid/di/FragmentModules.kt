package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import pl.pawelraciborski.pantroid.model.db.Repository
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecase
import pl.pawelraciborski.pantroid.model.usecase.GetAllPantryItemsUsecaseImpl
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecase
import pl.pawelraciborski.pantroid.model.usecase.InsertPantryItemUsecaseImpl
import pl.pawelraciborski.pantroid.view.list.ItemsListActivityFragment
import java.util.*

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */
@Module
abstract class ItemsListActivityFragmentProvider {
    @ContributesAndroidInjector(modules = [ItemsListActivityFragmentModule::class])
    abstract fun bindItemsListActivityFragment(): ItemsListActivityFragment
}

@Module
class ItemsListActivityFragmentModule {
    @Provides
    fun provideRandom() = Random(10)

    @Provides
    fun provideInsertPantryItemUsecase(repository: Repository): InsertPantryItemUsecase =
            InsertPantryItemUsecaseImpl(repository)

    @Provides
    fun provideGetAllPantryItemsUsecase(repository: Repository): GetAllPantryItemsUsecase =
            GetAllPantryItemsUsecaseImpl(repository)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}