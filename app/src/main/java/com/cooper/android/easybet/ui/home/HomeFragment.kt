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
import com.cooper.android.easybet.R
import com.cooper.android.easybet.databinding.FragmentHomeBinding
import org.w3c.dom.Text


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var username: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val nav = inflater.inflate(R.layout.nav_header_main, container, false)
        username = nav.findViewById(R.id.username_text)
        username.setText("test")
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}