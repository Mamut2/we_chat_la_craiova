package com.mamut.wechatlacraiova

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


var texts = mutableStateListOf<String>()

/*
object MyViewModel {
    //companion object {
        val listState: LazyListState? = null
    //}
}

 */


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
            val coroutineScope = rememberCoroutineScope()
            LazyColumn(
                state=listState,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(0.dp, 5.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
                    .weight(90f)
            ) {
                items(texts){text->
                    Message(text = text)
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(0.dp, 0.dp, 40.dp, 20.dp),
                shape = RoundedCornerShape(25.dp)
            )
            {

                /*
                IconButton(content = {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                },
                    onClick = { coroutineScope.launch { listState.animateScrollToItem(index = listState.layoutInfo.totalItemsCount - 1) } }
                )
                */
                var opening by remember { mutableStateOf(true) }
                LaunchedEffect(key1 = opening) {
                    if(opening){
                        delay(500)
                        coroutineScope.launch{listState.animateScrollToItem(index = listState.layoutInfo.totalItemsCount - 1)}
                        opening=false
                    }
                }

            }
        }
    }
    FloatingActionButton(

        content = {Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Bottom of messaages")},
        containerColor = Color(255, 233, 125, 255),
        elevation = FloatingActionButtonDefaults.elevation(2.5.dp),
        shape = CircleShape,

        modifier = Modifier
            .size(100.dp),
        onClick = {
            //coroutineScope.launch { listState.animateScrollToItem(index = listState.layoutInfo.totalItemsCount - 1)}
            //absolut nmc, inca n-am reusit sa-l fac sa dea scroll in jos de aici
            //coroutineScope().launch { listState?.animateScrollToItem(index = listState!!.layoutInfo.totalItemsCount - 1) }
        }
    )
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

