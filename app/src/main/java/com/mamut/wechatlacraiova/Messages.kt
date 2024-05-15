package com.mamut.wechatlacraiova

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Messages(innerPadding:PaddingValues=PaddingValues(500.dp)){
    var texts by remember {
        mutableStateOf("")
    }
    LazyColumn(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ) {

    }
}