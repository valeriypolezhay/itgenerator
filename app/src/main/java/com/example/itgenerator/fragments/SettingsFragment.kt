package com.example.itgenerator.fragments

import android.os.Bundle
import com.example.itgenerator.R
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            "Can't Touch This",
            Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()

        return true
    }
}