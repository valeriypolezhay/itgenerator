package com.example.itgenerator.screens.home

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.itgenerator.R
import com.example.itgenerator.database.PositionDatabase
import com.example.itgenerator.databinding.HomeFragmentBinding
import com.example.itgenerator.firebase
import com.example.itgenerator.sensors.AccelerometerListener
import com.example.itgenerator.sensors.AccelerometerManager
import java.util.*

class HomeFragment : Fragment(), AccelerometerListener {

    override fun onAccelerationChanged(x: Float, y: Float, z: Float) {

    }

    override fun onShake(force: Float) {
        generateAndSpeak()

        Toast.makeText(context, "Motion detected", Toast.LENGTH_SHORT).show()
        Log.i("GYRO", "motion done")
    }


    lateinit var mTTS: TextToSpeech
    lateinit var mTTS2: TextToSpeech

    lateinit var homeViewModel: HomeViewModel
    private lateinit var textView: TextView


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

        textView = binding.mytext

        val application = requireNotNull(this.activity).application
        val dataSource = PositionDatabase.getInstance(application).positionDatabaseDao

        homeViewModel = HomeViewModel(dataSource, application)

        binding.button.setOnClickListener {

            generateAndSpeak()
        }

        firebase()

        return binding.root
    }


    private fun generateAndSpeak() {
        val newPositionName = homeViewModel.generate()

        textView.text = newPositionName
        mTTS.speak(newPositionName, TextToSpeech.QUEUE_FLUSH, null, "1")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        if (AccelerometerManager.isSupported(context)) {
            AccelerometerManager.startListening(this)
        }
    }

    override fun onStop() {
        super.onStop()

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening()

//            Toast.makeText(context, "onStop Accelerometer Stopped", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening()

//            Toast.makeText(context, "onDestroy Accelerometer Stopped", Toast.LENGTH_SHORT).show()
        }
    }
}


