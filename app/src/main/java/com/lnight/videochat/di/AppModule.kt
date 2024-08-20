package com.lnight.videochat.di

import com.lnight.videochat.VideoChatApp
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.lnight.videochat.connect.ConnectViewModel
import com.lnight.videochat.video.VideoCallViewModel
import org.koin.android.ext.koin.androidContext

val appModule = module {
    factory {
        val app = androidContext().applicationContext as VideoChatApp
        app.client
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}