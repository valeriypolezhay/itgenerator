//package com.example.itgenerator.generator
//
//import com.example.itgenerator.database.PositionDatabase
//
//class PositionGenerator {
//
//
//
//    fun generate(): String {
//
//        val dummyList = listOf(
//            "Frontend",
//            "Middle",
//            "Manual",
//            "DevOps",
//            "Security",
//            "Manager",
//            "Solution",
//            "Architect",
//            "Software",
//            "Senior",
//            "Developer",
//            "Tester",
//            "Testing",
//            "Team Leader",
//            "Chief",
//            "Java",
//            "Kotlin",
//            ".Net",
//            "Spring",
//            "BDD",
//            "Intermediate",
//            "Advanced",
//            "Performance",
//            "Analysis",
//            "JavaScript",
//            "React",
//            "Angular",
//            "Bootstrap",
//            "Full Stack"
//        )
//
//        return getGreatPositionName(dummyList)
//
//    }
//
//    private fun getGreatPositionName(input: List<String>): String {
//
//        val listSize = input.size - 1
//        val set = mutableSetOf("")
//
//        for (i in 1..10) {
//            set.add(input[getRandomNumber(listSize)])
//        }
//
//        return set.joinToString(separator = " ")
//    }
//
//    private fun getRandomNumber(to: Int): Int {
//        return (1..to).shuffled().first()
//    }
//}