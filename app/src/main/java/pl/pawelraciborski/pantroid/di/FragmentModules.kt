package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import pl.pawelraciborski.pantroid.view.additem.AddItemActivityFragment
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
}

@Module
abstract class AddItemActivityFragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindAddItemActivityFragment(): AddItemActivityFragment
}