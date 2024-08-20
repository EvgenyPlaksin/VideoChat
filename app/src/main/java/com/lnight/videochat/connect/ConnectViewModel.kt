package com.lnight.videochat.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.lnight.videochat.VideoChatApp

class ConnectViewModel(
    private val app: Application
): AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction) {
        when(action) {
            ConnectAction.OnConnectClick -> {
                connectToRoom()
            }
            is ConnectAction.OnNameChange -> {
                state = state.copy(name = action.name)
            }
        }
    }

    private fun connectToRoom() {
        state = state.copy(errorMessage = null)
        if(state.name.isBlank()) {
            state = state.copy(
                errorMessage = "The username can't be blank."
            )
            return
        }

        // this method is simple, but will lead to problems with testing.
        // Do not use AndroidViewModel in serious projects.
        (app as VideoChatApp).initVideoClient(state.name)

        state = state.copy(isConnected = true)
    }
}