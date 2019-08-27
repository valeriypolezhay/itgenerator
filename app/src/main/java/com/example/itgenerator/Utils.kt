package com.example.itgenerator

fun getGreatPositionName(input: List<String>): String {

    val set = mutableSetOf("")
    if (input.isNotEmpty()) {

        val listSize = input.size - 1


        for (i in 0..10) {
            set.add(input[getRandomNumber(listSize)])
        }
    }

    return set.joinToString(separator = " ")
}

fun getRandomNumber(to: Int): Int {
    return (0..to).shuffled().first()
}