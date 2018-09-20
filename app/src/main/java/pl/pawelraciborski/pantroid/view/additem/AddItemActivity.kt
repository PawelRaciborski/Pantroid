package pl.pawelraciborski.pantroid.view.additem

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_item.*
import pl.pawelraciborski.pantroid.R
import pl.pawelraciborski.pantroid.view.util.addFragment
import pl.pawelraciborski.pantroid.view.util.getIntExtraOrNull

class AddItemActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        setSupportActionBar(toolbar)

        addFragment(AddItemActivityFragment.newInstance(
                intent.getIntExtraOrNull(SELECTED_ITEM_ID)),
                R.id.flContent
        )
    }

    companion object {
        const val SELECTED_ITEM_ID = "SELECTED_ITEM_ID"
    }
}
