package com.cooper.android.easybet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooper.android.easybet.BetList.database
import com.cooper.android.easybet.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception

private const val RC_SIGN_IN = 100

class SignIn : AppCompatActivity() {
    private lateinit var signIn: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var sign_in_button: com.google.android.gms.common.SignInButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)


        auth = Firebase.auth
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signIn = GoogleSignIn.getClient(this, googleSignInOptions)

        auth = FirebaseAuth.getInstance()
        checkUser()
        sign_in_button = findViewById(R.id.sign_in_button)
        sign_in_button.setOnClickListener{
            val intent = signIn.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }
    private fun checkUser(){
        val firebaseUser = auth.currentUser
        if(firebaseUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch(e: Exception){

            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                val firebaseUser = auth.currentUser
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email

                if(authResult.additionalUserInfo!!.isNewUser){
                    Toast.makeText(this, "Account Created \n$email", Toast.LENGTH_SHORT).show()
                    writeNewUser(uid, email.toString())
                }
                else{
                    Toast.makeText(this, "Logged in  \n$email", Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{e ->
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun writeNewUser(uid: String, email: String) {
        database.child("users").child(uid).child("email").setValue(email)
        database.child("users").child(uid).child("wallet").setValue(100)
    }
}