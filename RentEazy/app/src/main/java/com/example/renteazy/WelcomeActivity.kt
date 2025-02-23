package com.example.renteazy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.renteazy.ui.theme.Purple40
import com.example.renteazy.ui.theme.Purple80

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranAccueil()
        }
    }
}

@Composable
fun EcranAccueil() {
    val contexte = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Purple40, Purple80)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.f1),
                contentDescription = "Image d'accueil",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Titre
            Text(
                text = "Bienvenue sur RentEazy",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Connectez-vous en tant que Locateur ou Bailleur",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Bouton pour les clients
            Button(
                onClick = {
                    val intent = Intent(contexte, Login::class.java)
                    intent.putExtra("USER_ROLE", "Locateur")
                    contexte.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(" Locateur", fontSize = 16.sp, color = Purple40)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton pour les bailleurs
            Button(
                onClick = {
                    val intent = Intent(contexte, Login::class.java)
                    intent.putExtra("USER_ROLE", "Bailleur")
                    contexte.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Bailleur", fontSize = 16.sp, color = Purple40)
            }
        }
    }
}