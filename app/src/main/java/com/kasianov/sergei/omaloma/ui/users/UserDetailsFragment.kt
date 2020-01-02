package com.kasianov.sergei.omaloma.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.omaloma.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

}
