package com.cooper.android.easybet

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FriendListViewModel : ViewModel() {
    var friends = mutableListOf<Friends>()

}