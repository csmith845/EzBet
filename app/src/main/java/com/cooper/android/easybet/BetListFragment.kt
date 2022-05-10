package com.cooper.android.easybet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class BetListFragment:Fragment() {

    private lateinit var betRecyclerView: RecyclerView
    private lateinit var refresh: Button

    private val betListViewModel:BetListViewModel by lazy {
        ViewModelProvider(this).get(BetListViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bet_list_fragment, container, false)

        betRecyclerView = view.findViewById(R.id.bet_recycler_view) as RecyclerView
        betRecyclerView.layoutManager = LinearLayoutManager(context)
        refresh = view.findViewById(R.id.roomRefresh)
        refresh.setOnClickListener{
            updateUi()
        }
        updateUi()
        return view
    }

    private fun updateUi() {
        val bet = betListViewModel.bets
        val adapter = BetAdapter(bet)
        betRecyclerView.adapter = adapter
    }

    private inner class BetHolder(view:View): RecyclerView.ViewHolder(view){
        val titletextview: TextView =itemView.findViewById(R.id.bet_title)
        val pottextview:TextView = itemView.findViewById(R.id.pot_total)
    }

    private inner class BetAdapter(val bets:List<Bets>):RecyclerView.Adapter<BetHolder>(){
        override fun getItemCount()=bets.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetHolder {
            val view=layoutInflater.inflate(R.layout.bet_list_layout, parent, false)
            return BetHolder(view)
        }

        override fun onBindViewHolder(holder: BetHolder, position: Int) {
            val bet = bets[position]
            holder.apply {
                titletextview.text = bet.title
                pottextview.text = bet.pot.toString()
            }
        }
    }
}