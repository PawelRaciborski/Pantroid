package pl.pawelraciborski.pantroid.view.additem

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import pl.pawelraciborski.pantroid.R
import pl.pawelraciborski.pantroid.databinding.FragmentAddItemBinding
import pl.pawelraciborski.pantroid.view.BaseFragment
import pl.pawelraciborski.pantroid.vm.AddItemActivityFragmentViewModel
import pl.pawelraciborski.pantroid.vm.AddItemActivityFragmentViewModel.NavigationEvent.BACK
import pl.pawelraciborski.pantroid.vm.AddItemActivityFragmentViewModel.NavigationEvent.DISPLAY_SAVE_ERROR

/**
 * A placeholder fragment containing a simple view.
 */
class AddItemActivityFragment
    : BaseFragment<FragmentAddItemBinding, AddItemActivityFragmentViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_add_item

    override fun setupViewModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigationEventLiveData.observe(this, Observer {
            it?.let { event ->
                when (event.type) {
                    BACK -> activity?.onBackPressed()
                    DISPLAY_SAVE_ERROR -> view?.let { view ->
                        Snackbar.make(view, "Could not save item!", Snackbar.LENGTH_LONG)
                    }
                }
            }
        })

        arguments?.let { viewModel.init(it.getLong(ITEM_ID)) }


    }

    companion object {
        private const val ITEM_ID = "ITEM_ID"
        fun newInstance(itemId: Long? = null) = AddItemActivityFragment().apply {
            itemId?.let {
                arguments = Bundle().apply {
                    putLong(ITEM_ID, itemId)
                }
            }
        }
    }
}
