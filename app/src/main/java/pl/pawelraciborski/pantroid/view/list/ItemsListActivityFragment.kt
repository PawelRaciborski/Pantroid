package pl.pawelraciborski.pantroid.view.list

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.pawelraciborski.pantroid.R

/**
 * A placeholder fragment containing a simple view.
 */
class ItemsListActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_items_list, container, false)
    }
}
