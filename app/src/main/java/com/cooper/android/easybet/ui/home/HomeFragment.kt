package com.cooper.android.easybet.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooper.android.easybet.*
import com.cooper.android.easybet.databinding.FragmentHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
private const val Tag = "home"

class HomeFragment : Fragment() {
    private lateinit var friendButton: Button
    private lateinit var emailSearch: EditText
    private lateinit var friendRecycler: RecyclerView
    private lateinit var curuser: String
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






        curuser = Firebase.auth.currentUser!!.uid
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        friendButton = view.findViewById(R.id.friend_button)
        emailSearch = view.findViewById(R.id.email_search)
        val data = Firebase.database.reference.child("users")
        var friendEmail: String
        var friendID: String
        friendButton.setOnClickListener{
            var email : String = ""
            var user: Boolean = false
            var friends: Boolean = false
            var newFriend: Friends
            val emailString: String = emailSearch.text.toString()
            data.get().addOnSuccessListener {
                val childs = it.children
                childs.forEach { i->
                    if(i.hasChild("email")){
                        email = i.child("email").value.toString()
                        if(emailString == email){
                            friendID = i.key.toString()
                            if(!(it.child(curuser).child("friends").hasChild(friendID))) {
                                Log.d(Tag, "found $email")
                                Toast.makeText(activity, "user found $email", Toast.LENGTH_SHORT)
                                    .show()
                                friendEmail = email
                                user = true
                                data.child(curuser).child("friends").child(friendID)
                                    .setValue(friendEmail)
                            }
                            else{
                                friends = true
                                Toast.makeText(activity, "Already friends", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
                if (!user and !friends){
                    Log.d(Tag, "didnt find $email")
                    Toast.makeText(activity, "user '$emailString' not found ", Toast.LENGTH_SHORT).show()
                }
            }
            //get email address from user
            //search database for email address
            //add friend ID to user friends
        }


        return view


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}