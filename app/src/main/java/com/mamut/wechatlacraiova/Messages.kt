package com.mamut.wechatlacraiova

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Updater
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


var msgList = mutableStateListOf<TimestmpMsgData>()
const val buffer = 1

@Composable
fun ChatLog(innerPadding:PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ){
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Box (
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)
                    )
                    .weight(5f)
                    .fillMaxSize()
            )

            val listState = rememberLazyListState()
            val reachedTop: Boolean by remember {
                derivedStateOf {
                    val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                    lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
                }
            }

            LazyColumn(
                state=listState,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                reverseLayout = true,
                modifier = Modifier
                    .padding(0.dp, 5.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
                    .weight(90f)
            ) {
                items(msgList){msg->
                    Message(text = msg.text)
                }
            }

            LaunchedEffect(key1 = reachedTop, key2 = scrollToBottom) {
                if(reachedTop) loadOldMessages()
                if(scrollToBottom) {
                    listState.animateScrollToItem(0)
                    scrollToBottom = false
                }
            }
        }
    }
}

@Composable
fun Message(text: String){
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 40.dp, 0.dp)
            .wrapContentSize(
                align = Alignment.CenterStart
            ),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp)
        )
    }
}

data class TimestmpMsgData(
    val text:String,
    val time:String
)