package com.example.channels.presentation.homeFragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.channels.BaseFragment
import com.example.channels.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val args: HomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayUserCredentials()
    }

    private fun displayUserCredentials() {
        binding.showUsername.text = args.username
        binding.showPassword.text = args.password
    }
}



