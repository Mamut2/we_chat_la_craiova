package com.mamut.wechatlacraiova

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.unit.dp


var texts = mutableStateListOf<String>()

@Composable
fun ChatLog(innerPadding:PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(texts){text->
                Message(text = text)
            }
        }
    }
}

@Composable
fun Message(text: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 110.dp, 0.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(2.dp, Color(red = 255f, green = 255f, blue = 255f, alpha = 255f)) ///AJUTOR DC NU SE SCHIMBA CULOAREA
        //Color(186, 212, 170, 256))
        //.width(10.dp) - nu merge wtff
    ){
        Text(text = text)
    }
}