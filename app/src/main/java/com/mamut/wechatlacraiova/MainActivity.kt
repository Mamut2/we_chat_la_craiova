package com.mamut.wechatlacraiova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamut.wechatlacraiova.ui.theme.WeChatLaCraiovaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeChatLaCraiovaTheme {
                MainUI()
            }
        }
    }
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    Scaffold (bottomBar = { ChatBar()}, containerColor = Color(94, 94, 94, 256)){ innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UIPreview() {
    WeChatLaCraiovaTheme {
        MainUI()
    }
}