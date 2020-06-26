package com.kasianov.sergei.user.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kasianov.sergei.user.databinding.FragmentUsersListBinding

class UsersListFragment : Fragment() {

    // TODO: NOT implemented yet.

    // @Inject
    // lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentUsersListBinding
    // private val viewModel: UsersListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // (activity?.application as OmaLomaApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddUser.setOnClickListener {
            // this.findNavController().navigate(R.id.action_usersListFragment_to_userFragment)
            // this.findNavController().navigate(R.id.action_usersListFragment_to_publicHolidaysListFragment)
        }
    }
}
