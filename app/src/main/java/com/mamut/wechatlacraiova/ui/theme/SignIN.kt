package com.mamut.wechatlacraiova.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@Composable
public fun LoginScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var logInState by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val user = User(context)
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Login", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = pass,
            onValueChange = {pass = it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text(text = "Remember me")
        }

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            logInState = "Login successful"
                            scope.launch{
                                user.saveUser(rememberMe)
                            }
                            navController.navigate("chat")
                        } else {
                            logInState = "Login failed: ${task.exception?.message}"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = logInState)

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {navController.navigate("signup")}){
            Text("Sign up here")
        }
    }
}
@Composable
public fun SignUpScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passConfirm by remember { mutableStateOf("") }
    var signUpState by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Signup", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = pass,
            onValueChange = {pass = it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = passConfirm,
            onValueChange = {passConfirm = it},
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if(pass == passConfirm) {
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                signUpState = "Sign up successful"
                                navController.navigate("chat")
                            } else {
                                signUpState = "Sign up failed: ${task.exception?.message}"
                            }
                        }
                }
                else{
                    signUpState = "Passwords do not match"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = signUpState)
    }
}

