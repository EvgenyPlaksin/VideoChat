package com.lnight.videochat.video

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.getstream.video.android.compose.permission.rememberCallPermissionsState
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.controls.actions.DefaultOnCallActionHandler
import io.getstream.video.android.core.call.state.LeaveCall

@Composable
fun VideoCallScreen(
    modifier: Modifier = Modifier,
    state: VideoCallState,
    onAction: (VideoCallAction) -> Unit
) {
    val context = LocalContext.current

    when {
        state.error != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        state.callState == CallState.JOINING -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Joining...")
            }
        }
        else -> {
            val basePermissionsList = listOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
            val bluetoothConnectPermission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                listOf(Manifest.permission.BLUETOOTH_CONNECT)
            } else {
                emptyList()
            }
            val notificationPermission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                listOf(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                emptyList()
            }
            CallContent(
                call = state.call,
                modifier = modifier
                    .fillMaxSize(),
                permissions = rememberCallPermissionsState(
                    call = state.call,
                    permissions = basePermissionsList + bluetoothConnectPermission + notificationPermission,
                    onPermissionsResult = { permissions ->
                        if(permissions.values.contains(false)) {
                            Toast.makeText(
                                context,
                                "Please grant all permissions to use this app.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            onAction(VideoCallAction.JoinCall)
                        }
                    }
                ),
                onCallAction = { action ->
                    if(action == LeaveCall) {
                        onAction(VideoCallAction.OnDisconnectClick)
                    }
                    DefaultOnCallActionHandler.onCallAction(state.call, action)
                },
                onBackPressed = {
                    onAction(VideoCallAction.OnDisconnectClick)
                }
            )
        }
    }
}