package com.example.itgenerator.screens.positionlist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.itgenerator.R
import com.example.itgenerator.database.PositionDatabase
import com.example.itgenerator.databinding.ItemsListFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_item_fragment.view.*
import kotlinx.android.synthetic.main.item_view_holder.view.*


class ItemsListFragment : Fragment() {
    lateinit var binding: ItemsListFragmentBinding

    lateinit var adapter: ItemsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.items_list_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = PositionDatabase.getInstance(application).positionDatabaseDao
        val viewModelFactory = ItemsListViewModelFactory(dataSource, application)

        val itemsViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(ItemsListViewModel::class.java)

        binding.itemsViewModel = itemsViewModel

        adapter = ItemsListAdapter()
        binding.recyclerItemsList.adapter = adapter

        itemsViewModel.database.getAllPositions().observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        enableSwipeToDeleteAndUndo()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.items_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
//        return NavigationUI.onNavDestinationSelected(
//            item!!,
//            view!!.findNavController()
//        ) || super.onOptionsItemSelected(item)


        R.id.addNewItem -> run {
            NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
        }


        R.id.deleteItems -> run {
            binding.itemsViewModel?.clearDB()
            true
        }


        else -> {
            super.onOptionsItemSelected(item)
        }


    }

    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

                val position = viewHolder.adapterPosition

                val viewItemToDelete = viewHolder.itemView.view_holder_text
                val text=viewItemToDelete.text.toString()

                Log.d("myLogs", position.toString())

                binding.itemsViewModel?.deleteItem(text)


            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(binding.recyclerItemsList)

    }


}