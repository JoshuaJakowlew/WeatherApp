package com.example.weatherapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentNotificationsBinding
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.squareup.moshi.Json

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tempGraph: GraphView = binding.tempGraph
        notificationsViewModel.temp.observe(viewLifecycleOwner) {
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(it)

            tempGraph.viewport.isScrollable = true
            tempGraph.viewport.isScalable = true
            tempGraph.viewport.setScalableY(true)
            tempGraph.viewport.setScrollableY(true)
            tempGraph.viewport.setMaxX(5.0)

            series.setAnimated(true)
            series.dataPointsRadius = 25.0f
            series.thickness = 15
            series.backgroundColor = R.color.white
            series.color = R.color.white
            series.isDrawAsPath = true
            series.isDrawDataPoints = true
            series.isDrawBackground = true

            tempGraph.removeAllSeries()
            tempGraph.addSeries(series)
            tempGraph.animate()
        }

        notificationsViewModel.updateForecast(activity as MainActivity)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}