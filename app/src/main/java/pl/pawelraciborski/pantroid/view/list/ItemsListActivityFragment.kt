package pl.pawelraciborski.pantroid.view.list

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import pl.pawelraciborski.pantroid.R
import pl.pawelraciborski.pantroid.databinding.FragmentItemsListBinding
import pl.pawelraciborski.pantroid.databinding.ListItemBinding
import pl.pawelraciborski.pantroid.view.util.autoCleared
import pl.pawelraciborski.pantroid.vm.ItemsListActivityFragmentViewModel
import pl.pawelraciborski.pantroid.vm.PantryListItem
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class ItemsListActivityFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ItemsListActivityFragmentViewModel

    private var binding by autoCleared<FragmentItemsListBinding>()
    private var rvAdapter by autoCleared<ItemsAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_items_list,
                container,
                false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            rvAdapter = ItemsAdapter()
            adapter = rvAdapter
        }

        viewModel.items.observe(this, Observer { result ->
            result?.let {
                rvAdapter.updateValues(it)
            }
        })
    }

    private class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.PantryItemViewHolder>() {

        private val values = mutableListOf<PantryListItem>()

        fun updateValues(items: List<PantryListItem>) {
            with(values) {
                clear()
                addAll(items)
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): PantryItemViewHolder {
            return ItemsAdapter.PantryItemViewHolder(createBinding(viewGroup))
        }

        private fun createBinding(parent: ViewGroup) = DataBindingUtil.inflate<ListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
        )

        override fun getItemCount() = values.size

        override fun onBindViewHolder(holder: PantryItemViewHolder, position: Int) {
            holder.bind(values[position], position)
        }

        class PantryItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: PantryListItem, position: Int) {
                binding.text = item.let { "Position($position) ${item.name} with ID: ${item.id} (${item.quantity})" }
            }
        }
    }
}