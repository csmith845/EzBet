package com.cooper.android.easybet.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cooper.android.easybet.Bet
import com.cooper.android.easybet.BetList
import com.cooper.android.easybet.R
import com.cooper.android.easybet.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var newRoom: Button
    private lateinit var condition: EditText
    private lateinit var pot: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_slideshow ,container, false)

        newRoom = view.findViewById(R.id.new_room)
        condition = view.findViewById(R.id.condition)
        pot = view.findViewById(R.id.pot_init)

        newRoom.setOnClickListener(){
            val conditionString = condition.text.toString()
            val potString = pot.text.toString()
            if(conditionString != "" && potString != ""){
                val potInt = potString.toInt()
                BetList.newBet(conditionString, potInt)
        }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}