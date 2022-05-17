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



    private lateinit var newRoom: Button
    private lateinit var condition: EditText
    private lateinit var pot: EditText
    private lateinit var friendSelector: Spinner
    private lateinit var dateTime: EditText
    private lateinit var addFriend: EditText
    private lateinit var friend: String


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
            if(conditionString != "" && potString != "" && email !="" ) {





                for (i in 0 until size) {
                    if (FriendsList.LocalFriendList.friendsList[i].email == email) {
                        val friendKey = FriendsList.LocalFriendList.friendsList[i].id
                        Log.d(tag, "friend key $friendKey")
                        val totalpot = potString.toInt() * 2
                        withdrawFriend(friendKey, potString.toInt())
                        withdrawMe(potString.toInt())
                        database.child("users").child(friendKey).child("rooms").child(id.toString())
                            .setValue(id.toString())
                        database.child("Room").child(id.toString()).child("userID2")
                            .setValue(friendKey)
                        database.child("Room").child(id.toString()).child("title").setValue(conditionString)
                        database.child("Room").child(id.toString()).child("pot").setValue(totalpot)
                        database.child("Room").child(id.toString()).child("UserID1").setValue(userID)

                        database.child("users").child(userID).child("rooms").child(id.toString())
                            .setValue(id.toString())
                    }
                }
            }else{
            Toast.makeText(activity, "incorrect email", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    private fun withdrawMe(amount: Int){
        var bal = 0
        val user = Firebase.auth.currentUser!!.uid
        val myWallet = Firebase.database.reference.child("users/$user/wallet")
        myWallet.get().addOnSuccessListener {
            val temp = it.value.toString()
            bal = temp.toInt()
            bal -= amount
            myWallet.setValue(bal)
        }
    }
    private fun withdrawFriend(ID: String, amount: Int){
        var bal = 0
        val wallet = Firebase.database.reference.child("users/$ID/wallet")
            wallet.get().addOnSuccessListener {
            val temp = it.value.toString()
            bal = temp.toInt()
                Log.d(Tag, "withdraw bal = $bal")
                Log.d(Tag, "withdraw bal = $bal")
                bal -= amount
                Log.d(Tag, "new bal = $bal")
                wallet.setValue(bal)
        }


    }
    private fun findFriend(email: String):String{
        var friendId = ""
        val size = FriendsList.LocalFriendList.friendsList.size
        for(i in 0 until size){
            if(FriendsList.LocalFriendList.friendsList[i].email == email){
                friendId = FriendsList.LocalFriendList.friendsList[i].id
                Log.d(tag, "friend key $friendId")
            }
        }
        return friendId
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}