package com.example.itgenerator.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(com.example.itgenerator.R.xml.settings, rootKey)


    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        val telegramLinkPreference = findPreference("telegram") as Preference?
        telegramLinkPreference!!.setOnPreferenceClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Nativus")))
            true
        }

        val versionPreference = findPreference("version") as Preference?

        var a = 0
        versionPreference!!.setOnPreferenceClickListener {

            when(a){
                5->Toast.makeText(context, "What are you doing?", Toast.LENGTH_SHORT).show()
                10->Toast.makeText(context, "There is not a single toad", Toast.LENGTH_SHORT).show()
                15->Toast.makeText(context, "NOT", Toast.LENGTH_SHORT).show()
                16->Toast.makeText(context, "A SINGLE", Toast.LENGTH_SHORT).show()
                17->Toast.makeText(context, "TOAD", Toast.LENGTH_SHORT).show()
                20->Toast.makeText(context, "You thought u can become a developer?", Toast.LENGTH_SHORT).show()
                25->Toast.makeText(context, "Okey...", Toast.LENGTH_SHORT).show()
                30->Toast.makeText(context, "Держи уже свою жабу", Toast.LENGTH_SHORT).show()

            }
            a++
            true
        }

        return true
    }


}
