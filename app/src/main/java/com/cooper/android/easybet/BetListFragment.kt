package com.cooper.android.easybet

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BetListFragment:Fragment() {

    private lateinit var betRecyclerView: RecyclerView
    private lateinit var refresh: Button
    private var adapter: BetAdapter? = BetAdapter(mutableListOf<Bets>())

    private val betListViewModel:BetListViewModel by lazy {
        ViewModelProvider(this).get(BetListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        betRecyclerView.adapter = adapter
        refresh.setOnClickListener{
            updateUi(betListViewModel.bets)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi(betListViewModel.bets)
    }
    private fun onRoomSelected(title: String, pot: Int,UserID1:String,room_id:String){

        val intent = Intent(activity, Bet_room_view::class.java)
        intent.putExtra("room_id",room_id)
        intent.putExtra("title", title)
        intent.putExtra("pot", pot)
        intent.putExtra("UserID1", UserID1)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bet_room, menu)
    }

    private fun updateUi(bets: MutableList<Bets>) {

        val adapter = BetAdapter(bets)
        betRecyclerView.adapter = adapter
    }

    private inner class BetHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var bet: Bets

        val titletextview: TextView =itemView.findViewById(R.id.bet_title)
        val pottextview:TextView = itemView.findViewById(R.id.pot_total)

        init {itemView.setOnClickListener(this)}

        fun bind(bet:Bets){
            this.bet = bet
            titletextview.text = this.bet.title
            pottextview.text = this.bet.pot.toString()
        }

        override fun onClick(v: View?) {
            onRoomSelected(this.bet.title, this.bet.pot,this.bet.friend1,this.bet.id.toString())
        }
    }

    private inner class BetAdapter(val bets: MutableList<Bets>):RecyclerView.Adapter<BetHolder>(){
        override fun getItemCount()=bets.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetHolder {
            val view=layoutInflater.inflate(R.layout.bet_list_layout, parent, false)
            return BetHolder(view)
        }

        override fun onBindViewHolder(holder: BetHolder, position: Int) {
            val bet = bets[position]
            holder.bind(bet)

        }
    }

    companion object {
        fun newInstance(): BetListFragment {
            return BetListFragment()
        }
    }
}

