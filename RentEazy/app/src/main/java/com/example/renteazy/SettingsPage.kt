package com.example.renteazy

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.renteazy.ui.theme.RentEazyTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentEazyTheme {
                SettingsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    var darkMode by remember { mutableStateOf(sharedPreferences.getBoolean("darkMode", false)) }
    var notifications by remember { mutableStateOf(true) }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val userEmail = sharedPreferences.getString("email", "email@example.com") ?: "email@example.com"
    val userRole = sharedPreferences.getString("role", "client") ?: "client" // Récupérer le rôle de l'utilisateur

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri.toString() }
    )

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.apply()
        context.startActivity(Intent(context, Login::class.java))
        (context as? ComponentActivity)?.finish()
    }

    // Fonction pour gérer le clic sur la flèche de retour
    fun navigateBack() {
        // Récupérer le rôle de l'utilisateur depuis les SharedPreferences
        val userRole = sharedPreferences.getString("role", "client") ?: "client"

        // Créer l'intent approprié en fonction du rôle
        val intent = when (userRole) {
            "client" -> Intent(context, HomeActivity::class.java)
            "bailleur" -> Intent(context, HomeBailleurActivity::class.java)
            else -> Intent(context, HomeActivity::class.java) // Par défaut, rediriger vers HomeActivity
        }

        // Démarrer l'activité appropriée
        context.startActivity(intent)

        // Terminer l'activité actuelle (SettingsActivity)
        (context as? ComponentActivity)?.finish()
    }

    val colors = if (darkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colors) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Paramètres", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = colors.onPrimary) },
                    navigationIcon = {
                        IconButton(onClick = { navigateBack() }) { // Appeler navigateBack() au lieu de rediriger directement
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = colors.onPrimary)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colors.primary)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp)
                    .background(colors.background)
                    .verticalScroll(rememberScrollState()) // Activation du scroll
            ) {
                // Section Profil
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .border(3.dp, Color.White, CircleShape)
                            .clickable { launcher.launch("image/*") }
                    ) {
                        imageUri?.let { uri ->
                            val painter: Painter = rememberAsyncImagePainter(uri)
                            Image(painter = painter, contentDescription = "Profile Picture", modifier = Modifier.fillMaxSize())
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(userEmail, fontSize = 14.sp, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                // Mode sombre
                SettingsItem(
                    icon = Icons.Default.Brightness4,
                    title = "Mode Sombre",
                    switchState = darkMode,
                    onSwitchChange = {
                        darkMode = it
                        sharedPreferences.edit().putBoolean("darkMode", it).apply()
                    },
                    iconColor = colors.onBackground,
                    textColor = colors.onBackground
                )

                // Profil
                Spacer(modifier = Modifier.height(24.dp))
                Text("Profil", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colors.onBackground)
                SettingsItem(icon = Icons.Default.AccountCircle, title = "Modifier le Profil", iconColor = colors.onBackground, textColor = colors.onBackground)
                SettingsItem(icon = Icons.Default.Lock, title = "Changer le mot de passe", iconColor = colors.onBackground, textColor = colors.onBackground)

                // Notifications
                Spacer(modifier = Modifier.height(24.dp))
                Text("Notifications", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colors.onBackground)
                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    switchState = notifications,
                    onSwitchChange = { notifications = it },
                    iconColor = colors.onBackground,
                    textColor = colors.onBackground
                )

                // Déconnexion
                Spacer(modifier = Modifier.height(24.dp))
                SettingsItem(
                    icon = Icons.Default.ExitToApp,
                    title = "Se déconnecter",
                    onClick = { logout() },
                    iconColor = colors.onBackground,
                    textColor = colors.onBackground
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text("Version de l'App 2.0.1", fontSize = 14.sp, color = colors.onBackground, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    switchState: Boolean = false,
    onSwitchChange: ((Boolean) -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    iconColor: Color = Color.Unspecified,
    textColor: Color = Color.Unspecified
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(if (switchState) Color(0xFFE0E0E0) else Color(0xFFF1F1F1), shape = RoundedCornerShape(12.dp))
            .padding(18.dp)
            .clickable { onClick?.invoke() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(28.dp),
            tint = iconColor
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f), color = textColor)

        if (onSwitchChange != null) {
            Switch(checked = switchState, onCheckedChange = { onSwitchChange(it) })
        }
    }
}

@Composable
fun darkColorScheme() = MaterialTheme.colorScheme.copy(
    primary = Color(0xFF1A1A1A),
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSurface = Color.White,
    onBackground = Color.Black,
    secondary = Color(0xFF6200EE),
    secondaryContainer = Color(0xFF3700B3)
)

@Composable
fun lightColorScheme() = MaterialTheme.colorScheme.copy(
    primary = Color(0xFF7857A9),
    background = Color(0xFFF9F9F9),
    surface = Color.White,
    onPrimary = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black,
    secondary = Color(0xFF03DAC6),
    secondaryContainer = Color(0xFF018786)
)

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    RentEazyTheme {
        SettingsScreen()
    }
}