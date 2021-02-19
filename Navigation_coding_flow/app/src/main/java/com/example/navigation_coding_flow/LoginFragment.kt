package com.example.navigation_coding_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigation_coding_flow.databinding.FragmentLoginBinding
import com.example.navigation_coding_flow.databinding.FragmentLoginBindingImpl

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentLoginBindingImpl.inflate(layoutInflater)

        val args=LoginFragmentArgs.fromBundle(requireArguments())
        val usernameDeepLink=args.username
        val userpassDeepLink=args.userpass
        binding.editTextUsername.setText(usernameDeepLink)
        binding.editTextPassword.setText(userpassDeepLink)

        binding.buttonConfirm.setOnClickListener {
            val username=binding.editTextUsername.text.toString()
            val password=binding.editTextPassword.text.toString()

            val action=LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username,password)
            findNavController().navigate(action)
        }

        return binding.root
    }
}