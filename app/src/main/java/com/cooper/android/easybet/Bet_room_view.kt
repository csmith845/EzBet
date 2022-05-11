package com.cooper.android.easybet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Bet_room_view: AppCompatActivity() {
    val TAG = "betroom"
    private lateinit var id: TextView
    private lateinit var potdisplay: TextView
    private lateinit var closeButton:Button
    private var uid = Firebase.auth.currentUser!!.uid
    private var payoutFlag = 0

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
        id.setText(title)
        potdisplay.setText("$"+pot.toString())

        closeButton = findViewById(R.id.closeButton)
        closeButton.setOnClickListener{
            if (payoutFlag == 0){

            }
        }




    }


}
