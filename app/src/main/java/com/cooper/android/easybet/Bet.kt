package com.cooper.android.easybet

import java.util.*

data class Bets(private var id:UUID = UUID.randomUUID(), private var title:String ="", private var pot:Int = 0, private var friend1:String="", private var friend2 :String = "") {
    fun getTitle(): String {
        return title
    }

    fun getPot(): Int {
        return pot
    }
    fun getFriend1():String{
        return friend1
    }
    fun getFriend2():String{
        return friend2
    }
}




