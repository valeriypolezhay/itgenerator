package com.example.itgenerator.screens.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.itgenerator.databinding.AddItemFragmentBinding
import com.example.itgenerator.R
import com.example.itgenerator.database.Position
import com.example.itgenerator.database.PositionDatabase
import com.google.android.material.snackbar.Snackbar

class AddItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding: AddItemFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.add_item_fragment, container, false
        )

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = PositionDatabase.getInstance(application).positionDatabaseDao

        val model = AddItemViewModel(dataSource, application)

        binding.button3.setOnClickListener {

            val newItem = Position()
            newItem.positionName = binding.editText2.text.toString()
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "Item added",
                Snackbar.LENGTH_SHORT // How long to display the message.
            ).show()

            model.onAddItem(newItem)
            binding.editText2.text.clear()
//            this.findNavController().navigate(ItemsListFragmentDirections.actionItemsFragmentToAddNewItem2())
        }

        return binding.root

    }
}