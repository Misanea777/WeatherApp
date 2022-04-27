package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = signUpViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        signUpViewModel.isCreated.observe(viewLifecycleOwner) { isCreated ->
            if (isCreated) findNavController().navigate(R.id.action_sign_up_fragment_to_splash_fragment)
        }
        binding.signUpButton.setOnClickListener {
            signUpViewModel.signUpUser()
        }

        binding.backToLoginButton.setOnClickListener{
            findNavController().navigate(R.id.action_sign_up_fragment_to_login_fragment)
        }
    }
}