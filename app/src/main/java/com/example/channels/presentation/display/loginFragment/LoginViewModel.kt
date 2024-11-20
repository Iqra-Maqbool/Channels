package com.example.channels.presentation.display.loginFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.channels.domain.Constants
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginEventsChannel = Channel<LoginEvent>()
    val loginEvents = loginEventsChannel.receiveAsFlow()

    fun login(username: String, password: String) = viewModelScope.launch {
        if (username == Constants.KEY_USER_NAME && password == Constants.KEY_PASSWORD) {
            loginEventsChannel.send(LoginEvent.NavigateToHome)
        } else {
            loginEventsChannel.send(LoginEvent.ShowErrorMessage(Constants.KEY_INVALID))
        }
    }

    sealed class LoginEvent {
        data object NavigateToHome : LoginEvent()
        data class ShowErrorMessage(val message: String) : LoginEvent()
    }
}