package com.cooper.android.easybet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Account : AppCompatActivity() {

    private lateinit var signOut: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var emailText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_layout)

        auth = FirebaseAuth.getInstance()
        emailText = findViewById(R.id.email_text)
        signOut = findViewById(R.id.sign_out)
        checkUser()



        signOut.setOnClickListener{
            auth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }
        else{
            val email = firebaseUser.email
            emailText.setText(email)
        }
    }
}