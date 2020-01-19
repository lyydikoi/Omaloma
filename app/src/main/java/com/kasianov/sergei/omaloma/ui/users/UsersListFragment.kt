package com.kasianov.sergei.omaloma.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.databinding.FragmentUsersListBinding

class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddUser.setOnClickListener {
            //this.findNavController().navigate(R.id.action_usersListFragment_to_userFragment)
            this.findNavController().navigate(R.id.action_usersListFragment_to_publicHolidaysListFragment)
        }
    }
}
