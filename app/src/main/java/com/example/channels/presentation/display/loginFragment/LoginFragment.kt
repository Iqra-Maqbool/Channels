package com.example.channels.presentation.display.loginFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.channels.BaseFragment
import com.example.channels.R
import com.example.channels.databinding.FragmentLoginBinding
import com.example.channels.ext.showToast
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        observeLoginEvents()
    }

    private fun setupListener() {
        binding.loginBtn.setOnClickListener {
            handleLoginButtonClick()
        }
    }

    private fun handleLoginButtonClick() {
        val username = binding.loginUsername.text.toString()
        val password = binding.loginPassword.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(username, password)
        } else {
            requireContext().showToast(getString(R.string.Please_Fill_All_Field))
        }
    }

    private fun observeLoginEvents() {
        lifecycleScope.launch {
            viewModel.loginEvents.collect { event ->
                handleLoginEvent(event)
            }
        }
    }

    private fun handleLoginEvent(event: LoginViewModel.LoginEvent) {
        when (event) {
            is LoginViewModel.LoginEvent.NavigateToHome -> navigateToHome()
            is LoginViewModel.LoginEvent.ShowErrorMessage -> showErrorMessage(event.message)
        }
    }

    private fun navigateToHome() {
        requireContext().showToast(getString(R.string.Login_successful))
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(
            username = binding.loginUsername.text.toString(),
            password = binding.loginPassword.text.toString()
        )
        if (findNavController().currentDestination?.id == R.id.loginFragment) {
            findNavController().navigate(action)
        }
    }

    private fun showErrorMessage(message: String) {
        requireContext().showToast(message)
    }
}
