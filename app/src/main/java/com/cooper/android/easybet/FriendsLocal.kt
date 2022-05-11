package com.cooper.android.easybet

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FriendsLocal(@PrimaryKey(autoGenerate = true) val id:Int = 0, @ColumnInfo val uid:String="", @ColumnInfo val email: String){
}