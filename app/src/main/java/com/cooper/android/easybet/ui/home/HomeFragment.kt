package com.cooper.android.easybet.ui.home

import android.app.Activity
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
import com.cooper.android.easybet.Bet
import com.cooper.android.easybet.MainActivity
import com.cooper.android.easybet.Wallet
import com.cooper.android.easybet.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var pub1: Button
    private lateinit var pub2: Button
    private lateinit var pub3: Button
    private lateinit var pub4: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}