package com.example.itgenerator

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

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

fun firebase(){
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
//                    Log.w(TAG, "getInstanceId failed", task.exception)
                return@OnCompleteListener
            }

            // Get new Instance ID token
            val token = task.result?.token
        })

}