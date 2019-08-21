package com.example.itgenerator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.example.itgenerator.generator.PositionGenerator
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import android.speech.tts.TextToSpeech
import com.example.itgenerator.R
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mTTS: TextToSpeech
    lateinit var mTTS2: TextToSpeech


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS.language = Locale.US
                mTTS.setPitch(1.3f);
                mTTS.setSpeechRate(1f);
            }


        })

        mTTS2 = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS2.language = Locale("ru")
                mTTS2.setPitch(0.7f)
            }

        })

        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textView: TextView = view.findViewById(R.id.mytext)
        val generateButton: Button = view.findViewById(R.id.button)
        val iHoldButton: Button = view.findViewById(R.id.iHoldButton)
        val youHoldButton: Button = view.findViewById(R.id.youHoldButton)


        generateButton.setOnClickListener {
            val finalText = PositionGenerator().generate()
            textView.text = finalText
            mTTS.speak(finalText, TextToSpeech.QUEUE_FLUSH, null, "1")
        }



        iHoldButton.setOnClickListener {
            mTTS2.speak("держу жаву", TextToSpeech.QUEUE_FLUSH, null, "1")
        }

        youHoldButton.setOnClickListener {
            mTTS2.speak("держи жаву", TextToSpeech.QUEUE_FLUSH, null, "1")
        }

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

//    fun sendArguments(view: View) {
//        var action: HomeFragmentDirections.ActionGoto1 =
//            HomeFragmentDirections.actionGoto1()
//        action.setTestNumber(1234)
//        Navigation.findNavController(view).navigate(action)
//    }

}


