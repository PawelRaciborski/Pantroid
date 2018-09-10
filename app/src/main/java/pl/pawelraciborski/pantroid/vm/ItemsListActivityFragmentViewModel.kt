package pl.pawelraciborski.pantroid.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 04.09.2018.
 */

class ItemsListActivityFragmentViewModel @Inject constructor(
) : ViewModel() {

    val items: MutableLiveData<List<PantryListItem>> = MutableLiveData()

    init {
        val tmpItems = mutableListOf<PantryListItem>()

        for (i in 1..10) {
            tmpItems.add(PantryListItem(i.toLong(), "Name $i", i * 2))
        }

        items.postValue(tmpItems)
    }
}