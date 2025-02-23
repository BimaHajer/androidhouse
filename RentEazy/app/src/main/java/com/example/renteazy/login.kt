package com.example.renteazy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.renteazy.ui.theme.Purple40

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val role = intent.getStringExtra("USER_ROLE") ?: "Client" // Récupère le rôle passé dans l'Intent
        setContent {
            LoginScreen(role = role) // Passer le rôle à l'écran de connexion
        }
    }
}

@Composable
fun LoginScreen(role: String) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) } // État pour gérer la visibilité du mot de passe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF654FA3)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ajouter une flèche de retour en haut à gauche
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(
                onClick = {
                    // Rediriger vers WelcomeActivity
                    context.startActivity(Intent(context, WelcomeActivity::class.java))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = Color.Black
                )
            }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(

                modifier = Modifier
                .fillMaxWidth()
                .background(Purple40, shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.f7),
                contentDescription = "Image d'accueil",
                modifier = Modifier.size(400.dp)
            )
        }

        Text(
            text = "Connexion en tant que $role", // Affiche le rôle de l'utilisateur
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ email avec icône
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Icône email"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Champ mot de passe avec icône et bouton pour voir/cacher le mot de passe
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Icône mot de passe"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible } // Basculer la visibilité du mot de passe
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Cacher le mot de passe" else "Voir le mot de passe"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mot de passe oublié ?",
            fontSize = 14.sp,
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
                }
        )
        Spacer(modifier = Modifier.height(16.dp))


        // Bouton de connexion
        Button(
            onClick = {
                val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val storedEmail = sharedPreferences.getString("email", null)
                val storedPassword = sharedPreferences.getString("password", null)

                if (email == storedEmail && password == storedPassword) {
                    val nextActivity = if (role == "Locateur") {
                        HomeActivity::class.java // Rediriger vers la page d'accueil pour le client
                    } else {
                        HomeBailleurActivity::class.java // Rediriger vers une autre activité pour le bailleur
                    }

                    context.startActivity(Intent(context, nextActivity))
                } else {
                    Toast.makeText(context, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .background(Purple40, shape = RoundedCornerShape(8.dp)) // Utiliser la couleur personnalisée ici aussi
        ) {
            Text("Se connecter", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pas encore de compte ? S'inscrire",
            fontSize = 14.sp,
            modifier = Modifier.clickable {
                context.startActivity(Intent(context, RegisterActivity::class.java)) // Aller à la page d'inscription
            }
        )
    }
}}