package com.kasianov.sergei.omaloma

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_content_main.*

/**
 * Main content: general information and statistics.
 */
class MainContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_edit_holidays.setOnClickListener { view ->
            this.findNavController().navigate(R.id.action_contentMain_to_holidaysList)
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }

        fab_open_profile.setOnClickListener { view ->
            this.findNavController().navigate(R.id.action_contentMain_to_profile)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = MainContentFragment()
    }
}
