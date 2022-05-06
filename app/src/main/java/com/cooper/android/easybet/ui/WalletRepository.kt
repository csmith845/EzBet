package com.cooper.android.easybet.ui
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.cooper.android.easybet.databinding.ActivityMainBinding
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database




import java.lang.Exception
import java.time.temporal.TemporalAmount
import kotlin.jvm.internal.Ref

private const val TAG = "wallet repo"
private var bal: Long = 0

object WalletRepository{
    private val uid = Firebase.auth.currentUser!!.uid
    private val db = Firebase.database.reference.child("users/${uid}/wallet")


    fun getBal():Long {
        db.get().addOnSuccessListener {
            if (it.exists() && it.value != null) {
                bal = it.value as Long
                Log.i(TAG,"Bal is $bal")
            }
        }
        return bal
    }
}













    






