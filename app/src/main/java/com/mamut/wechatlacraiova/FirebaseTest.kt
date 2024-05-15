package com.mamut.wechatlacraiova

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

lateinit var dbr: DatabaseReference
fun main(mesaj: String ) {
    dbr= FirebaseDatabase.getInstance("https://wechatlacraiova-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
    dbr.push().setValue(mesaj)
    //dbreadlistener()
}
/*
fun dbreadlistener(){
    val msgListen = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for(msgSnapshot in snapshot.children){
                val chatMessage = msgSnapshot.getValue()
                chatMessage?.let {
                    Log.d("mesaj",chatMessage.toString())
                }
            }
        }
        override fun onCancelled(error: DatabaseError) {
            Log.d("eroare","eroare la listener")
        }

    }

}
*/


