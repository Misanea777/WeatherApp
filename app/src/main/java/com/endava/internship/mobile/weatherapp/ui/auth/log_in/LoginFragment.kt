package com.endava.internship.mobile.weatherapp.ui.auth.log_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginButton.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_splash_fragment)
        }
        binding.toSingUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_sign_up_fragment)
        }
    }
}