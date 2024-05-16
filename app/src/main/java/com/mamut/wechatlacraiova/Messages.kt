package com.mamut.wechatlacraiova

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
        elevation = CardDefaults.cardElevation(4.dp)
    //.width(10.dp) - nu merge wtff
    ){
        Text(text = text)
    }
}