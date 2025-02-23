package com.example.renteazy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForgotPasswordScreen()
        }
    }
}

@Composable
fun ForgotPasswordScreen() {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var generatedOtp by remember { mutableStateOf("") }
    var step by remember { mutableStateOf(1) } // Étape actuelle du processus

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (step == 1) "Mot de passe oublié" else "Vérification OTP",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A1B9A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (step) {
                1 -> { // Étape 1 : Demande d'email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Entrez votre email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (email.isNotEmpty()) {
                                generatedOtp = (1000..9999).random().toString() // Génère un OTP aléatoire
                                Toast.makeText(context, "Code OTP envoyé : $generatedOtp", Toast.LENGTH_LONG).show()
                                step = 2 // Passer à l'étape 2
                            } else {
                                Toast.makeText(context, "Veuillez entrer un email valide", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("Envoyer le code", color = Color.White, fontSize = 16.sp)
                    }
                }

                2 -> { // Étape 2 : Vérification du code OTP et réinitialisation du mot de passe
                    OutlinedTextField(
                        value = otp,
                        onValueChange = { otp = it },
                        label = { Text("Entrez le code OTP reçu") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nouveau mot de passe") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (otp == generatedOtp) {
                                Toast.makeText(context, "Mot de passe réinitialisé avec succès", Toast.LENGTH_LONG).show()
                                context.startActivity(Intent(context, Login::class.java)) // Redirection vers la connexion
                            } else {
                                Toast.makeText(context, "Code OTP invalide", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("Confirmer", color = Color.White, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Retour à la connexion",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, Login::class.java))
                }
            )
        }
    }
}
