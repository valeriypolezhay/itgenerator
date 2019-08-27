package com.example.itgenerator.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import android.speech.tts.TextToSpeech
import androidx.databinding.DataBindingUtil
import com.example.itgenerator.R
import com.example.itgenerator.database.PositionDatabase
import com.example.itgenerator.databinding.HomeFragmentBinding
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mTTS: TextToSpeech
    lateinit var mTTS2: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS.language = Locale.US
                mTTS.setPitch(1.3f)
                mTTS.setSpeechRate(1f)
            }
        })

        mTTS2 = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS2.language = Locale("ru")
                mTTS2.setPitch(0.7f)
            }
        })

        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )

        binding.lifecycleOwner = this

        binding.iHoldButton.setOnClickListener {
            mTTS2.speak("держу жаву", TextToSpeech.QUEUE_FLUSH, null, "1")
        }

        binding.youHoldButton.setOnClickListener {
            mTTS2.speak("держи жаву", TextToSpeech.QUEUE_FLUSH, null, "1")
        }

        val textView: TextView = binding.mytext

        val application = requireNotNull(this.activity).application
        val dataSource = PositionDatabase.getInstance(application).positionDatabaseDao

        val homeViewModel = HomeViewModel(dataSource, application)

        binding.button.setOnClickListener {
            val newPositionName = homeViewModel.generate()

            textView.text = newPositionName
            mTTS.speak(newPositionName, TextToSpeech.QUEUE_FLUSH, null, "1")
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    //    fun sendArguments(view: View) {
//        var action: HomeFragmentDirections.ActionGoto1 =
//            HomeFragmentDirections.actionGoto1()
//        action.setTestNumber(1234)
//        Navigation.findNavController(view).navigate(action)
//    }

}


