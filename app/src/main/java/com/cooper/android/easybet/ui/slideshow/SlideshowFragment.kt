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

        newRoom.setOnClickListener(){
            val conditionString = condition.text.toString()
            val email = addFriend.text.toString()
            val size = FriendsList.LocalFriendList.friendsList.size
            Log.d(tag, "friend size $size")



            val potString = pot.text.toString()
            val id: UUID = UUID.randomUUID()
            if(conditionString != "" && potString != "" && email !=""){

                database.child("Room").child(id.toString()).child("title").setValue(conditionString)
                database.child("Room").child(id.toString()).child("pot").setValue(potString)
                database.child("Room").child(id.toString()).child("UserID1").setValue(userID)

                database.child("users").child(userID).child("rooms").child(id.toString()).setValue(id.toString())
                for(i in 0 until size){
                    if(FriendsList.LocalFriendList.friendsList[i].email == email){
                        val friendKey = FriendsList.LocalFriendList.friendsList[i].id
                        Log.d(tag, "friend key $friendKey")
                        database.child("users").child(friendKey).child("rooms").child(id.toString()).setValue(id.toString())
                        database.child("Room").child(id.toString()).child("userID2").setValue(friendKey)
                    }
                }


        }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}