package com.kasianov.sergei.main.maincontent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.main.databinding.FragmentContentMainBinding

/**
 * Main content: general information and statistics.
 */
class MainContentFragment : Fragment() {

    // TODO: NOT implemented yet.

    private lateinit var binding: FragmentContentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: this is for testing only
        binding.chartView.setValues(100f, 30f, 10f)
<<<<<<< HEAD
        binding.chartView.setChartSelectedInteraction { name: String ->
=======
        binding.chartView.chartSelectedInteraction = { name: String ->
>>>>>>> otus-hw-custom-view
            binding.customViewLabel.text = name
            Log.v("ChartViewTag", "chart: $name")
        }
    }
}
