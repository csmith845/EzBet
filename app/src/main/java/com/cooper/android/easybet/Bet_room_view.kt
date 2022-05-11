package com.cooper.android.easybet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Bet_room_view: AppCompatActivity() {
    val TAG = "betroom"
    private lateinit var id: TextView
    private lateinit var potdisplay: TextView
    private lateinit var closeButton:Button
    private lateinit var friend1: TextView
    private lateinit var friend2: TextView
    private var uid = Firebase.auth.currentUser!!.uid

    private fun findFriend(email: String):String{
        var friendId = ""
        val size = FriendsList.LocalFriendList.friendsList.size
        for(i in 0 until size){
            if(FriendsList.LocalFriendList.friendsList[i].id == email){
                friendId = FriendsList.LocalFriendList.friendsList[i].email
                Log.d(TAG, "friend key $friendId")
            }
        }
        return friendId
    }
    private fun winning(ID: String, amount: Int){
        var bal = 0
        val wallet = Firebase.database.reference.child("users/$ID/wallet")
        wallet.get().addOnSuccessListener {
            val temp = it.value.toString()
            bal = temp.toInt()

            bal += amount

            wallet.setValue(bal)
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bet_room)
        id = findViewById(R.id.bet_name)

        potdisplay = findViewById(R.id.pot_display)
        val room_id = intent.getStringExtra("room_id")
        val key = intent.getStringExtra("key")
        val title = intent.getStringExtra("title")
        val pot = intent.getIntExtra("pot", 0)
        val uid1 = intent.getStringExtra("UserID1")
        val uid2 = intent.getStringExtra("UserID2")
        var winner = ""
        id.setText(title)
        potdisplay.setText("$"+pot.toString())

        closeButton = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            val db = Firebase.database.reference
            Log.d(Tag,"roomid is $room_id")




            val popup: PopupMenu = PopupMenu(this, closeButton)
            popup.setOnMenuItemClickListener { item ->
                Log.d(Tag, "item id${item.title}")
                if (item.title == findFriend(uid1.toString())) {
                    winner = uid1.toString()
                    Log.d(Tag, "winner fr1$winner")
                }
                else{

                    winner = uid2.toString()
                    Log.d(Tag, "winner fr2 $winner")
                }
                winning(winner, pot)

                db.child("Room").child(room_id.toString()).removeValue()
                db.database.reference.child("users").child(uid1.toString()).child("rooms").child(room_id.toString()).removeValue()
                db.database.reference.child("users").child(uid2.toString()).child("rooms").child(room_id.toString()).removeValue()
                return@setOnMenuItemClickListener true
            }

            popup.menuInflater.inflate(R.menu.pop_menu, popup.menu)
            val p = popup.menu
            p.clear()
            p.add(findFriend(uid1.toString()))
            p.add(findFriend(uid2.toString()))
            popup.show()


        }

    }
}
