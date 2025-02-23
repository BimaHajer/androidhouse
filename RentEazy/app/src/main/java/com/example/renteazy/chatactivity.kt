package com.example.renteazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.renteazy.ui.screens.CallScreen
import com.example.renteazy.ui.screens.CameraScreen
import com.example.renteazy.ui.screens.ChatScreen
import com.example.renteazy.ui.screens.HomeScree
import com.example.renteazy.ui.theme.RentEazyTheme

class ChatActivity : ComponentActivity() { // Renommé en ChatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentEazyTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp)), // Coins arrondis
                    color = Color(0xFF4A4A4A), // Couleur plus claire

                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "chat") { // On démarre sur le chat
                        composable("home") {
                            HomeScree(navController = navController)
                        }
                        composable("chat") {
                            ChatScreen(navController = navController)
                        }
                        composable("camera") {
                            CameraScreen(navController = navController)
                        }
                        composable("call") {
                            CallScreen(navController = navController)
                        }

                    }
                }
            }
        }
    }
}
