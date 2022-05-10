package com.cooper.android.easybet

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val TAG = "WALLET CHECK"

object Wallet{
    private var bal : Long = 0
    private var dbBal : Long = 0
    private val uid = Firebase.auth.currentUser!!.uid
    private val db = Firebase.database.reference.child("users/$uid")

    fun getBal(): Long{
        db.get().addOnSuccessListener {
            if (it.exists()){
                dbBal = it.child("wallet").value as Long
                Log.i(TAG,"WALLET BAL IS $dbBal")
            }
        }
        if (dbBal != bal) {
            bal = dbBal
        }
        return bal

    }
    fun withdrawal(amount:Int){
        if(amount>bal){
            return
        }else{
            bal -= amount
            db.child("wallet").setValue(bal)
            dbBal = getBal()
            bal = dbBal
        }
    }
    fun credit(amount:Int){
        bal += amount
        db.child("wallet").setValue(bal)
        dbBal = getBal()
        bal = dbBal
    }
}