package pl.pawelraciborski.pantroid.view

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import pl.pawelraciborski.pantroid.view.util.autoCleared
import javax.inject.Inject

/**
 * Created by Pawel Raciborski on 18.09.2018.
 */

abstract class BaseFragment<T : android.databinding.ViewDataBinding, VM : ViewModel>
    : DaggerFragment() {

    @Inject
    lateinit var viewModel: VM

    protected var binding by autoCleared<T>()

    protected abstract val layoutId: Int

    protected abstract fun setupViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                layoutId,
                container,
                false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.setLifecycleOwner(this)
    }
}