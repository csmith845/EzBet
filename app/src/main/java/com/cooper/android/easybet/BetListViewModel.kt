package com.cooper.android.easybet

import androidx.lifecycle.ViewModel

class BetListViewModel:ViewModel() {
    var bets = mutableListOf<Bets>()


    init {

        for(i in 0 until BetList.idList.size){
            val bet = Bets()
            bet.id = BetList.idList[i]
            bet.pot = BetList.getPot(BetList.idList[i])
            bet.title = BetList.getTitle(BetList.idList[i])
            bets += bet
        }
    }
}