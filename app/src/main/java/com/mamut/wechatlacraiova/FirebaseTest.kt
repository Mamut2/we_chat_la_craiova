package com.mamut.wechatlacraiova

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.snapshots
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.last
import kotlin.time.Duration.Companion.seconds

lateinit var dbr: DatabaseReference

var lastLoadedMessageIndex = 0
const val numberOfMessagesToLoad = 21


fun pushMessage(message: TimestmpMsgData ) {
    dbr.push().setValue(message)
}

fun readMessages() {
    dbr.get().addOnSuccessListener { data ->
        for (i in data.childrenCount - 1 downTo (if (data.childrenCount - numberOfMessagesToLoad < 0) 0 else data.childrenCount - numberOfMessagesToLoad))
            data.children.elementAt(i.toInt()).getValue<TimestmpMsgData>()?.let { msgList.add(it) }
        lastLoadedMessageIndex = (if (data.childrenCount - numberOfMessagesToLoad < 0) 0 else data.childrenCount - numberOfMessagesToLoad).toInt()
    }

    dbr.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                if(!msgList.isEmpty()) dataSnapshot.children.last().getValue<TimestmpMsgData>()?.let {msgList.add(0, it) }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }
    )
}

fun loadOldMessages() {
    dbr.get().addOnSuccessListener { data ->
        for (i in lastLoadedMessageIndex - 1 downTo  (if (lastLoadedMessageIndex - numberOfMessagesToLoad < 0) 0 else lastLoadedMessageIndex - numberOfMessagesToLoad))
            data.children.elementAt(i).getValue<TimestmpMsgData>()?.let { msgList.add(it) }
        lastLoadedMessageIndex = (if (lastLoadedMessageIndex - numberOfMessagesToLoad < 0) 0 else lastLoadedMessageIndex - numberOfMessagesToLoad).toInt()
    }
}




