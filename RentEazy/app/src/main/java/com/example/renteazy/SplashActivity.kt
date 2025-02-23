package com.example.renteazy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.renteazy.ui.theme.Purple40
import com.example.renteazy.ui.theme.Purple80
import com.example.renteazy.ui.theme.RentEazyTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, 5000)

        setContent {
            RentEazyTheme {
                SplashScreen()
            }
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current

    // Animation de l'effet d'apparition
    var scale by remember { mutableStateOf(0.8f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(Unit) {
        scale = 1f
    }

    // ImageLoader pour charger les GIFs
    val imageLoader = ImageLoader.Builder(context)
        .components { add(GifDecoder.Factory()) }
        .build()

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(R.drawable.load1)
            .build(),
        imageLoader = imageLoader
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Purple40, Purple80)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "Logo animé",
                modifier = Modifier
                    .size(120.dp)
                    .scale(animatedScale)
                    .background(Color.White, shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bienvenue sur RentEazy",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
