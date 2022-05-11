package com.cooper.android.easybet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Bet_room_view: AppCompatActivity() {
    val TAG = "betroom"
    private lateinit var id: TextView
    private lateinit var potdisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bet_room)
        id = findViewById(R.id.bet_name)
        potdisplay = findViewById(R.id.pot_display)

        val key = intent.getStringExtra("key")
        val title = intent.getStringExtra("title")
        val pot = intent.getIntExtra("pot", 0)
        id.setText(title)
        potdisplay.setText(pot.toString())

    }

}