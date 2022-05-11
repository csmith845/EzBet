package com.cooper.android.easybet.localdatabase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.cooper.android.easybet.FriendsLocal
import com.cooper.android.easybet.localdatabase.FriendLocalDAO

private const val DATABASE_NAME = "friends-local-database"

class FriendLocalRepository private constructor(context: Context) {
    private val database : FriendLocalDatabase = Room.databaseBuilder(
        context.applicationContext,
        FriendLocalDatabase ::class.java,
        DATABASE_NAME
    ).build()

    private val FriendLocalDAO = database.FriendLocalDAO()

    fun getFriends():LiveData<List<FriendsLocal>> = FriendLocalDAO.get()

    fun getFriend(email:String):LiveData<String?> = FriendLocalDAO.getFriend(email)

    companion object{
        private var INSTANCE:FriendLocalRepository?=null
        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE= FriendLocalRepository(context)
            }
        }
        fun get():FriendLocalRepository{
            return INSTANCE?:throw IllegalStateException("Friends Local must be initialized")
        }
    }
}