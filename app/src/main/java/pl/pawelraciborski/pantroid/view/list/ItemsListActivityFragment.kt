package pl.pawelraciborski.pantroid.view.list

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import pl.pawelraciborski.pantroid.R
import pl.pawelraciborski.pantroid.databinding.ListItemBinding
import pl.pawelraciborski.pantroid.vm.ItemsListActivityFragmentViewModel
import java.util.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class ItemsListActivityFragment : DaggerFragment() {

    @Inject
    lateinit var random: Random

    @Inject
    lateinit var viewModel: ItemsListActivityFragmentViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_items_list, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.rvItems).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ItemsAdapter()
        }
        return view
    }
}

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder<ListItemBinding>>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder<ListItemBinding> {
        createBinding(viewGroup)
        return ItemsAdapter.ViewHolder(binding)
    }

    private lateinit var binding: ListItemBinding

    private fun createBinding(parent: ViewGroup) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
        )
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder<ListItemBinding>, position: Int) {
        binding.text = position.toString()
    }

    class ViewHolder<out T : ViewDataBinding>(binding: T) : RecyclerView.ViewHolder(binding.root)

}