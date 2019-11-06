package com.kasianov.sergei.omaloma

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.omaloma.databinding.FragmentProfileBinding


/**
 * Snows users profile
 */
class ProfileFragment : Fragment() {
    private lateinit var viewBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProfileBinding.inflate(inflater, false)
        return viewBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
