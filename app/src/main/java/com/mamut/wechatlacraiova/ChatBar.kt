package com.mamut.wechatlacraiova

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ChatBar(modifier: Modifier = Modifier){
    BottomAppBar(
        contentPadding = PaddingValues(0.dp),
        containerColor = Color.Transparent,
        modifier = Modifier
            .padding(10.dp, 0.dp, 10.dp, 20.dp)
            .height(70.dp)
    ){
        Box(
            modifier = Modifier
                .background(
                    color = Color(237, 180, 88, 255),
                    shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                )
                .border(
                    width = 2.dp,
                    Color.Black,
                    shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                )
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ){
                var fieldText by remember { mutableStateOf("") }
                TextField(
                    value = fieldText, onValueChange = { fieldText = it},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .weight(85f)
                        .clip(RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)),
                    placeholder = { Text(text = "Enter text...", color = Color(92, 88, 88, 255))}
                )

                IconButton(
                    onClick = {main(fieldText.toString())
                        fieldText=""},
                    content = {
                        Icon(Icons.Filled.ArrowForward, contentDescription = null)
                    },
                    modifier = Modifier
                        .weight(15f)
                        .fillMaxSize()
                )
            }
        }
    }
}