package pl.pawelraciborski.pantroid.view.additem

import pl.pawelraciborski.pantroid.R
import pl.pawelraciborski.pantroid.databinding.FragmentAddItemBinding
import pl.pawelraciborski.pantroid.view.BaseFragment
import pl.pawelraciborski.pantroid.vm.AddItemActivityFragmentViewModel

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
}
