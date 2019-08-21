package com.example.itgenerator.screens.positionlist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.itgenerator.R
import com.example.itgenerator.database.PositionDatabase
import com.example.itgenerator.databinding.ItemsListFragmentBinding

class ItemsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        val binding: ItemsListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.items_list_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PositionDatabase.getInstance(application).positionDatabaseDao
        val viewModelFactory = ItemsListViewModelFactory(dataSource, application)

        val itemsViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(ItemsListViewModel::class.java)

        binding.itemsViewModel = itemsViewModel

        val adapter = ItemsListAdapter()
        binding.recyclerItemsList.adapter = adapter

        itemsViewModel.database.getAllPositions().observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.items_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)


    }

}