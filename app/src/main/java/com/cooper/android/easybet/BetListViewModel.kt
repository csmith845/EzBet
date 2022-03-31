package com.cooper.android.easybet

import androidx.lifecycle.ViewModel

class BetListViewModel:ViewModel() {
    val bets = mutableListOf<Bets>()
    init {
        for (i in 0 until 10){
            val bet = Bets()
            bet.title = "Bet #$i"
            bet.pot = 0
            bets += bet
        }
    }
}