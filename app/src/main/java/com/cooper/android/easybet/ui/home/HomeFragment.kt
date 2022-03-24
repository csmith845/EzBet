package com.cooper.android.easybet.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cooper.android.easybet.MainActivity
import com.cooper.android.easybet.Wallet
import com.cooper.android.easybet.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var total: TextView
    private lateinit var string: String
    private lateinit var entry: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.betButton
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        total = binding.total
        entry = binding.betAmount


        textView.setOnClickListener{
            string = entry.text.toString()
            val bet = string.toInt()
            Wallet.withdraw(bet)

            println(Wallet.balance())
            total.text = string
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}