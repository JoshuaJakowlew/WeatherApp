package com.example.weatherapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MainActivity
import com.example.weatherapp.databinding.FragmentDashboardBinding
import com.example.weatherapp.owm.dataclasses.Forecast
import java.sql.Date

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button1: Button = binding.button1
        button1.setOnClickListener { selectForecast(0) }

        val button2: Button = binding.button2
        button2.setOnClickListener { selectForecast(1) }

        val button3: Button = binding.button3
        button3.setOnClickListener { selectForecast(2) }

        val button4: Button = binding.button4
        button4.setOnClickListener { selectForecast(3) }

        val button5: Button = binding.button5
        button5.setOnClickListener { selectForecast(4) }

        dashboardViewModel.forecasts.observe(viewLifecycleOwner) {
            button1.text = showForecast(0, it)
            button2.text = showForecast(1, it)
            button3.text = showForecast(2, it)
            button4.text = showForecast(3, it)
            button5.text = showForecast(4, it)
        }

        dashboardViewModel.updateForecast((activity as MainActivity))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showForecast(day: Int, forecast: List<Forecast>): String {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        return if (dashboardViewModel.forecastReady.value == true) {
            val f = forecast[day]
            Date(f.dateUnix.toLong() * 1000).toString()
        } else {
            "Day ${day + 1}"
        }
    }

    private fun selectForecast(day: Int)
    {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        val forecastReady = dashboardViewModel.forecastReady
        val forecasts = dashboardViewModel.forecasts

        val a = (activity as MainActivity)

        if (forecastReady.value == true) {
            val forecast = forecasts.value!![day]
            a.setCurrentForecast(forecast)
        }

        a.showMainCard()
    }
}