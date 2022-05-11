package com.cooper.android.easybet.localdatabase

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*
import com.cooper.android.easybet.FriendsLocal

@Dao
interface FriendLocalDAO{
    @Query("SELECT * FROM FriendsLocal")
    fun get():LiveData<List<FriendsLocal>>
    @Query("SELECT * FROM FriendsLocal WHERE email = (:email)")
    fun getFriend(email: String):LiveData<String?>

    @Insert
    fun addFriendLocal(friend:FriendsLocal)
}