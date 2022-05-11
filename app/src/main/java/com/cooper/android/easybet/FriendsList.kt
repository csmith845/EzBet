package com.cooper.android.easybet

import android.os.AsyncTask
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.currentCoroutineContext

const val Tag = "friends"
class FriendsList : Fragment(){

    private lateinit var friendRecycler: RecyclerView
    private lateinit var refresh: Button
    object LocalFriendList{
        var friendsList :MutableList<Friends> = arrayListOf()
    }

    private val friendListViewModel:FriendListViewModel by lazy {
        ViewModelProvider(this).get(FriendListViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.friends_list, container, false)
        friendRecycler = view.findViewById(R.id.friend_recycler) as RecyclerView
        friendRecycler.layoutManager = LinearLayoutManager(context)
        refresh = view.findViewById(R.id.refresh)
        refresh.setOnClickListener{
            updateUi()
        }
        updateUi()
        return view
    }

    private var curuser = Firebase.auth.currentUser!!.uid
    fun updateUi() {
        val friendRef = Firebase.database.reference.child("users").child(curuser).child("friends")
        friendRef.get().addOnSuccessListener {
            val friend = it.children
            friend.forEach { i ->
                if(!(friendListViewModel.friends.contains(Friends(i.key.toString(), i.value.toString())))) {
                        friendListViewModel.friends += Friends(i.key.toString(), i.value.toString())
                        LocalFriendList.friendsList.add(Friends(i.key.toString(),i.value.toString()))
                }
            }
            for (i in 0 until LocalFriendList.friendsList.size){
                println(LocalFriendList.friendsList[i].email)
            }

        }
        val friends = friendListViewModel.friends
        val adapter = FriendAdapter(friends)
        friendRecycler.adapter = adapter
    }

    private inner class FriendHolder(view:View): RecyclerView.ViewHolder(view){
        val titletextview: TextView =itemView.findViewById(R.id.email_view)
    }

    private inner class FriendAdapter(val friends: MutableList<Friends>):RecyclerView.Adapter<FriendHolder>(){
        override fun getItemCount()=friends.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
            val view=layoutInflater.inflate(R.layout.friend_list_layout, parent, false)
            return FriendHolder(view)
        }

        override fun onBindViewHolder(holder: FriendHolder, position: Int) {
            val friend = friends[position]
            holder.apply {
                titletextview.text = friend.email
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}