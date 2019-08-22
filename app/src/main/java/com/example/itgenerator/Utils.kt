package com.example.itgenerator

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 */
class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)


fun getGreatPositionName(input: List<String>): String {

    val listSize = input.size - 1
    val set = mutableSetOf("")

    for (i in 1..10) {
        set.add(input[getRandomNumber(listSize)])
    }

    return set.joinToString(separator = " ")
}

fun getRandomNumber(to: Int): Int {
    return (1..to).shuffled().first()
}