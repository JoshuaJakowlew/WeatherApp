package com.example.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MainActivity
import com.example.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textDateView: TextView = binding.textDate
        homeViewModel.textDate.observe(viewLifecycleOwner) {
            textDateView.text = it
        }

        val textCityView: TextView = binding.textCity
        homeViewModel.textCity.observe(viewLifecycleOwner) {
            textCityView.text = it
        }

        val textTempView: TextView = binding.textTemp
        homeViewModel.textTemp.observe(viewLifecycleOwner) {
            textTempView.text = "${it} C"
        }

//        val locationClient = (activity as MainActivity).fusedLocationClient
        homeViewModel.updateForecast(activity as MainActivity)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}