package pl.pawelraciborski.pantroid.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.pawelraciborski.pantroid.view.list.ItemsListActivity

/**
 * Created by Pawel Raciborski on 30.08.2018.
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ItemsListActivityFragmentProvider::class])
    abstract fun bindItemsListActivity(): ItemsListActivity
}
