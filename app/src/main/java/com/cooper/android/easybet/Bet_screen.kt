package com.cooper.android.easybet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cooper.android.easybet.ui.WalletRepository

class Bet : AppCompatActivity() {
    private lateinit var total: TextView
    private lateinit var entry: EditText
    private lateinit var bet_button: Button
    private lateinit var string: String




    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bet_screen_layout)
        bet_button = findViewById(R.id.betButton)
        total = findViewById(R.id.total)
        entry = findViewById(R.id.bet_amount)

        bet_button.setOnClickListener{
            string = entry.text.toString()
            val bet = string.toInt()


        }
    }

}