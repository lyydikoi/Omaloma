package com.kasianov.sergei.user.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kasianov.sergei.user.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    // TODO: NOT implemented yet.

    // Inject
    // lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentUserDetailsBinding
    // private val viewModel: UserDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var viewModel: UserDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // (activity?.application as OmaLomaApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.tbCollapsing.title = getString(R.string.user_details)
        /*binding.ivStartDate.setOnClickListener {
            this.findNavController().navigate(R.id.action_userFragment_to_datePickerDialogFragment)
        }*/
    }
}
