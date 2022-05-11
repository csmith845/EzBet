package com.cooper.android.easybet

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class BetListViewModel:ViewModel() {
    var bets = mutableListOf<Bets>()

    private var activeRoomId = mutableListOf<String>()

    private var user = Firebase.auth.currentUser!!.uid
    private var userRoomRef = Firebase.database.reference.child("users").child(user).child("rooms")
    private var roomRef = Firebase.database.reference.child("Room")

    init {
        userRoomRef.get().addOnSuccessListener {
            val room = it.children
            room.forEach { i ->
                activeRoomId += i.key.toString()
                Log.d(com.cooper.android.easybet.ui.slideshow.Tag, "room id $activeRoomId")
            }
            roomRef.get().addOnSuccessListener {
                val publicRooms = it.children
                publicRooms.forEach { x ->

                    var pot = 0
                    var id: String
                    var title = ""
                    val roomcount = activeRoomId.size

                    for (i in 0 until roomcount) {
                        if (activeRoomId[i] == x.key) {
                            id = x.key!!
                            Log.d(Tag, "active room found ${activeRoomId[i]}")
                            roomRef.child(activeRoomId[i]).get().addOnSuccessListener {
                                it.children.forEach { i ->
                                    if (i.key == "pot") {
                                        var temp = i.value
                                        temp = temp.toString()
                                        pot = temp.toInt()
                                    } else if (i.key == "title") {
                                        title = i.value as String
                                    }
                                }
                                val bet = Bets()
                                bet.id = UUID.randomUUID()
                                bet.pot = pot
                                bet.title = title
                                bets += bet
                            }
                        }
                    }
                }
            }
        }
    }
}
