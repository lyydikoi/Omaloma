package com.kasianov.sergei.omaloma.presentation.maincontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.omaloma.databinding.FragmentContentMainBinding

/**
 * Main content: general information and statistics.
 */
class MainContentFragment : Fragment() {

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

        binding.fabEditHolidays.setOnClickListener {
            //this.findNavController().navigate(R.id.action_contentMain_to_holidaysList)
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }

        binding.fabOpenProfile.setOnClickListener {
            //this.findNavController().navigate(R.id.action_contentMain_to_profile)
        }
    }
}
