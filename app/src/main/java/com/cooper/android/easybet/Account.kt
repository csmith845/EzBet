package com.cooper.android.easybet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class Account : Fragment() {

    private lateinit var signOut: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var emailText: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.account_layout, container, false)

        auth = FirebaseAuth.getInstance()

        emailText = view.findViewById(R.id.email_text)
        signOut = view.findViewById(R.id.sign_out)

        checkUser()
        signOut.setOnClickListener{
            auth.signOut()
            checkUser()
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(activity, SignIn::class.java))
        }
        else{
            val email = firebaseUser.email
            emailText.setText(email)
        }
    }
}