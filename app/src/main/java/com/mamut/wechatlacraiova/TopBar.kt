package com.mamut.wechatlacraiova

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(controller: NavController){
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { controller.navigate("settings") },
                content = {Icon(Icons.Filled.Menu,contentDescription = null)}
            )
        }

}