package com.cooper.android.easybet.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cooper.android.easybet.Bet
import com.cooper.android.easybet.R

class pubBet3 : AppCompatActivity() {
    private lateinit var newView: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pub_bet3)

        newView = findViewById(R.id.Button_pub3)
        newView.setOnClickListener{
            val intent = Intent(this, Bet::class.java)
            startActivity(intent)
        }
    }
}