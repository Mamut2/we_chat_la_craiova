package com.mamut.wechatlacraiova

import android.R
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.mamut.wechatlacraiova.ui.theme.LoginScreen
import com.mamut.wechatlacraiova.ui.theme.SignUpScreen
import com.mamut.wechatlacraiova.ui.theme.User
import com.mamut.wechatlacraiova.ui.theme.WeChatLaCraiovaTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

var scrollToBottom by mutableStateOf(false)

class MainActivity : ComponentActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = User(this)

        FirebaseApp.initializeApp(this)             // Firebase
        dbr = FirebaseDatabase.getInstance("https://wechatlacraiova-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
        readMessages()

        enableEdgeToEdge()
        hideSystemUI(window)  // Hide navigation bar

        setContent {
            val controller = rememberNavController()
            var startDestination by remember { mutableStateOf("login") }
            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    user.isUserLoggedIn.collectLatest {
                        isLoggedIn -> startDestination = if (isLoggedIn) "chat" else "login"
                    }
                }
            }
            WeChatLaCraiovaTheme {
                App(controller = controller, startDestination = startDestination)
            }
        }
    }
}

@Composable
fun App(controller: NavHostController, startDestination: String){
    NavHost(navController=controller, startDestination = startDestination){
        composable("login"){LoginScreen(controller)}
        composable("signup"){SignUpScreen(controller)}
        composable("chat"){MainUI()}
    }
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = { ChatBar()},
        containerColor = Color(186, 212, 170, 255),
        content = { innerPadding -> ChatLog(innerPadding) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scrollToBottom = true
                },
                content = {Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Bottom of messaages")},
                containerColor = Color(255, 233, 125, 255),
                elevation = FloatingActionButtonDefaults.elevation(2.5.dp),
                shape = CircleShape,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .size(30.dp)
            )
        }
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
