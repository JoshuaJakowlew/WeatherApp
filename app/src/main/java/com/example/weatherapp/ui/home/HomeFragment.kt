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
import org.w3c.dom.Text

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

        val location: TextView = binding.location
        homeViewModel.location.observe(viewLifecycleOwner) { location.text = it }

        val temp: TextView = binding.realtemp
        homeViewModel.temp.observe(viewLifecycleOwner) { temp.text = it }

        val date: TextView = binding.lastUpdate
        homeViewModel.date.observe(viewLifecycleOwner) { date.text = it }

        homeViewModel.updateForecast(activity as MainActivity)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}