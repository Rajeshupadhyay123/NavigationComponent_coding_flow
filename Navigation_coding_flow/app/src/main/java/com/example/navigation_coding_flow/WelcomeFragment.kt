package com.example.navigation_coding_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigation_coding_flow.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args=WelcomeFragmentArgs.fromBundle(requireArguments())
        /**
         * we can also create an binding object by:-
         * val binding=DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater,R.layout.fragment_welcome,container,false)
         */

        val binding=DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater,R.layout.fragment_welcome,container,false)

        binding.textViewUsername.text=args.username
        binding.textViewPassword.text=args.password

        binding.buttonOk.setOnClickListener {
            val action=WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}