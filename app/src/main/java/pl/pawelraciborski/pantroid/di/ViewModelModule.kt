package pl.pawelraciborski.pantroid.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import pl.pawelraciborski.pantroid.vm.AddItemActivityFragmentViewModel
import pl.pawelraciborski.pantroid.vm.ItemsListActivityFragmentViewModel
import kotlin.reflect.KClass

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */

@MustBeDocumented
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemsListActivityFragmentViewModel::class)
    abstract fun bindItemsListActivityFragmentViewModel(
            itemsListActivityFragmentViewModel: ItemsListActivityFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddItemActivityFragmentViewModel::class)
    abstract fun bindAddItemActivityFragmentViewModel(
            addItemActivityFragmentViewModel: AddItemActivityFragmentViewModel
    ): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}