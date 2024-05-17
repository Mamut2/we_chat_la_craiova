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
import com.google.firebase.database.snapshots
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.last
import kotlin.time.Duration.Companion.seconds

lateinit var dbr: DatabaseReference

var finishedLoadingMessages by mutableStateOf(false)
var lastLoadedMessageIndex = 0
const val numberOfMessagesToLoad = 20

fun pushMessage(message: String ) {
    dbr.push().setValue(message)
}

fun readMessages() {
    dbr.get().addOnSuccessListener { data ->
        for (i in (if (data.childrenCount - numberOfMessagesToLoad < 0) 0 else data.childrenCount - numberOfMessagesToLoad)..<data.childrenCount)
            texts.add(data.children.elementAt(i.toInt()).value.toString())
        lastLoadedMessageIndex = (if (data.childrenCount - numberOfMessagesToLoad < 0) 0 else data.childrenCount - numberOfMessagesToLoad).toInt()
        finishedLoadingMessages = true
    }

    dbr.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                if(!texts.isEmpty()) texts.add(dataSnapshot.children.last().value.toString())
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
        for (i in (if (lastLoadedMessageIndex - numberOfMessagesToLoad < 0) 0 else lastLoadedMessageIndex - numberOfMessagesToLoad)..<lastLoadedMessageIndex)
            texts.add(0, data.children.elementAt(i).value.toString())
        lastLoadedMessageIndex = (if (lastLoadedMessageIndex - numberOfMessagesToLoad < 0) 0 else lastLoadedMessageIndex - numberOfMessagesToLoad).toInt()
    }
}




