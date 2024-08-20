package com.lnight.videochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lnight.videochat.connect.ConnectScreen
import com.lnight.videochat.connect.ConnectViewModel
import com.lnight.videochat.ui.theme.VideoChatTheme
import com.lnight.videochat.video.CallState
import com.lnight.videochat.video.VideoCallScreen
import com.lnight.videochat.video.VideoCallViewModel
import io.getstream.video.android.compose.theme.VideoTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = ConnectRoute
                    ) {
                        composable<ConnectRoute> {
                            val viewModel = koinViewModel<ConnectViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state.isConnected) {
                                if (state.isConnected) {
                                    navController.navigate(VideoCallRoute) {
                                        popUpTo(ConnectRoute) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                            ConnectScreen(
                                state = state,
                                onAction = viewModel::onAction
                            )
                        }
                        composable<VideoCallRoute> {
                            val viewModel = koinViewModel<VideoCallViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state.callState) {
                                if (state.callState == CallState.ENDED) {
                                    navController.navigate(ConnectRoute) {
                                        popUpTo(VideoCallRoute) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                            VideoTheme {
                                VideoCallScreen(
                                    state = state,
                                    onAction = viewModel::onAction
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data object ConnectRoute

@Serializable
data object VideoCallRoute