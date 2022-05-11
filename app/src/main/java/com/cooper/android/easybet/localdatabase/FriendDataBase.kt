package com.cooper.android.easybet.localdatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import com.cooper.android.easybet.FriendsLocal


@Database
    (entities = [FriendsLocal::class], version = 1)
abstract class FriendLocalDatabase:RoomDatabase(){
    abstract fun FriendLocalDAO():FriendLocalDAO
}