package com.kasianov.sergei.main.maincontent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kasianov.sergei.main.R
import com.kasianov.sergei.main.databinding.FragmentContentMainBinding

/**
 * Main content: general information and statistics.
 */
class MainContentFragment : Fragment() {

    // TODO: NOT implemented yet.

    private lateinit var binding: FragmentContentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: this is for testing only
        binding.chartView.setValues(100f, 30f, 10f)
        binding.chartView.chartSelectedInteraction = { name: String ->
            binding.customViewLabel.text = name
            Log.v("ChartViewTag", "chart: $name")
        }

        binding.fabNavigateToAbsences.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.mainContentFragment) {
                findNavController().navigate(R.id.absencesListFragment)
                // findNavController().navigate(R.id.pubHolListFragment)
            }
        }
    }
}
