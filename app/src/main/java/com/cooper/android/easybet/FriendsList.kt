package com.cooper.android.easybet

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cooper.android.easybet.BetList.database
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import kotlin.Exception
import kotlin.math.log

private const val Tag = "friends"
class FriendsList : Fragment(){
    private lateinit var friendButton: Button
    private lateinit var emailSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.friends_list, container, false)
        friendButton = view.findViewById(R.id.friend_button)
        emailSearch = view.findViewById(R.id.email_search)
        val data = Firebase.database.reference.child("users")
        friendButton.setOnClickListener{

            val emailString: String = emailSearch.text.toString()
            data.get().addOnSuccessListener {
                Log.d(Tag,"got that shit $it")
                val childs = it.children
                childs.forEach { i->
                    if(i.hasChild("email")){
                        val email = i.child("email").value.toString()
                        if(emailString == email){
                            Log.d(Tag, "found email")
                            Toast.makeText(activity, "user found", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity, "user '$emailString' not found ", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }



            //get email address from user
            //search database for email address
            //add friend ID to user friends
        }

        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}