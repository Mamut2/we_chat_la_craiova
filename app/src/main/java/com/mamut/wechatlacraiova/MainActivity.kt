package com.mamut.wechatlacraiova

import android.R
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.mamut.wechatlacraiova.ui.theme.WeChatLaCraiovaTheme

/*
class OtherClass {
    @Composable
    fun accessListState() {
        // Call the composable function to initialize listState
        InitializeListState()

        // Access the listState variable
        MyViewModel.listState
    }
}
 */



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)             // Firebase
        dbr = FirebaseDatabase.getInstance("https://wechatlacraiova-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
        readMessages()

        enableEdgeToEdge()
        hideSystemUI(window)  // Hide navigation bar

        setContent {
            WeChatLaCraiovaTheme {
                MainUI()

            }
        }
    }
}

@Preview
@Composable
fun MainUI(modifier: Modifier = Modifier) {
    //InitializeListState()
    val listState = rememberLazyListState()
    Scaffold (
        bottomBar = { ChatBar()},
        containerColor = Color(186, 212, 170, 255),
        content = { innerPadding -> ChatLog(innerPadding) },
        /*floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          //absolut nmc, inca n-am reusit sa-l fac sa dea scroll in jos de aici
                    //coroutineScope().launch { listState?.animateScrollToItem(index = listState!!.layoutInfo.totalItemsCount - 1) }
                },
                content = {Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Bottom of messaages")},
                containerColor = Color(255, 233, 125, 255),
                elevation = FloatingActionButtonDefaults.elevation(2.5.dp),
                shape = CircleShape,
                modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 10.dp).size(30.dp)

            )
        }*/
    )
}

@Preview(showBackground = true)
@Composable
fun UIPreview() {
    WeChatLaCraiovaTheme {
        MainUI()
    }
}

private fun hideSystemUI(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window,
        window.decorView.findViewById(R.id.content)).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())

        // When the screen is swiped up at the bottom
        // of the application, the navigationBar shall
        // appear for some time
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

/*
fun Scrolltolast(){
    coroutineScope.launch{listState.animateScrollToItem(index = listState.layoutInfo.totalItemsCount - 1)}
}
*/
