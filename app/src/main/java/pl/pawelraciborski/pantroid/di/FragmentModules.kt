package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.pawelraciborski.pantroid.view.additem.AddItemActivityFragment
import pl.pawelraciborski.pantroid.view.list.ItemsListActivityFragment

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */
@Module
abstract class ItemsListActivityFragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindItemsListActivityFragment(): ItemsListActivityFragment
}

@Module
abstract class AddItemActivityFragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindAddItemActivityFragment(): AddItemActivityFragment
}