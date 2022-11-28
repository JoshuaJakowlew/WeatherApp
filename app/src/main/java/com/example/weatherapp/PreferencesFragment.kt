package com.example.weatherapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import com.example.weatherapp.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment() {

    private var _binding: FragmentPreferencesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this)[PreferencesViewModel::class.java]

        _binding = FragmentPreferencesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val temp = binding.switchTemp
        temp.setOnCheckedChangeListener { _, inKelvin -> settingsViewModel.updateTemp(inKelvin) }

        val speed = binding.switchSpeed
        speed.setOnCheckedChangeListener { _, inMs -> settingsViewModel.updateSpeed(inMs) }

        val pressure = binding.switchPressure
        pressure.setOnCheckedChangeListener { _, inPascal -> settingsViewModel.updatePressure(inPascal) }

        updatePreferences()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updatePreferences() {
        val preferencesViewModel =
            ViewModelProvider(this)[PreferencesViewModel::class.java]

        val a = activity as MainActivity
        a.preferences = preferencesViewModel.preferences.value!!
    }
}