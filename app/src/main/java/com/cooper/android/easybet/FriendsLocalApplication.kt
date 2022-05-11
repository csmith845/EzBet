package com.cooper.android.easybet

import android.app.Application
import com.cooper.android.easybet.localdatabase.FriendLocalRepository

class FriendsLocalApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        FriendLocalRepository.initialize(this)
    }
}