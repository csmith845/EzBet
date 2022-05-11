package com.cooper.android.easybet.ui.slideshow

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cooper.android.easybet.*
import com.cooper.android.easybet.BetList.database

import com.cooper.android.easybet.databinding.FragmentSlideshowBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class SlideshowFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var newRoom: Button
    private lateinit var condition: EditText
    private lateinit var pot: EditText
    private lateinit var friendSelector: Spinner
    private lateinit var dateTime: EditText
    private lateinit var addFriend: EditText

    private val friendListViewModel:FriendListViewModel by lazy {
        ViewModelProvider(this).get(FriendListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_slideshow ,container, false)
        val emails = friendListViewModel.friends
        newRoom = view.findViewById(R.id.new_room)
        condition = view.findViewById(R.id.condition)
        pot = view.findViewById(R.id.pot_init)
        addFriend = view.findViewById(R.id.add_friend)




        val user = Firebase.auth.currentUser
        val userID = user!!.uid
        val Id: UUID = UUID.randomUUID()
        newRoom.setOnClickListener(){
            val conditionString = condition.text.toString()
            val email = addFriend.text.toString()
            val size = friendListViewModel.friends.size
            Log.d(tag, "friend size $size")

            for(i in 0 until size){
                if(friendListViewModel.friends[i].email == email){
                    val friendKey = friendListViewModel.friends[i].id
                    Log.d(tag, "friend key $friendKey")
                    database.child("users").child(friendKey).child("rooms").child(Id.toString()).setValue(Id.toString())
                }
            }

            val potString = pot.text.toString()

            if(conditionString != "" && potString != ""){

                database.child("Room").child(Id.toString()).child("title").setValue(conditionString)
                database.child("Room").child(Id.toString()).child("pot").setValue(potString)
                database.child("Room").child(Id.toString()).child("UserID").setValue(userID)
                database.child("users").child(userID).child("rooms").child(Id.toString()).setValue(Id.toString())



        }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}