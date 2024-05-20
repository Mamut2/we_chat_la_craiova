package com.mamut.wechatlacraiova

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun settings(controller: NavController){
    val scope =rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = {
            val user = User(context)
            scope.launch{
                user.saveUser(false)
            }
            controller.navigate("login")
        }
        )
        {
            Text(text = "log out")
        }
    }
}