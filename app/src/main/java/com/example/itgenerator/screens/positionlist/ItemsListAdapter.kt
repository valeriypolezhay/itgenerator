package com.example.itgenerator.screens.positionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itgenerator.R
import com.example.itgenerator.TextItemViewHolder
import com.example.itgenerator.database.Position

class ItemsListAdapter : ListAdapter<Position, ItemsListAdapter.ViewHolder>(PositionDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemText.text = item.positionName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.view_holder_text)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_holder, parent, false)

                return ViewHolder(view)
            }
        }
    }
}

class PositionDiffCallback : DiffUtil.ItemCallback<Position>() {
    override fun areItemsTheSame(oldItem: Position, newItem: Position): Boolean {
        return oldItem.positionID == newItem.positionID
    }

    override fun areContentsTheSame(oldItem: Position, newItem: Position): Boolean {
        return oldItem == newItem
    }

}
