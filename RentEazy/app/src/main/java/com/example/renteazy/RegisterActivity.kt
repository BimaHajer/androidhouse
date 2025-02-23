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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.renteazy.ui.theme.Purple40

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen()
        }
    }
}

@Composable
fun RegisterScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

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
                .padding(vertical = 34.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.f8),
                contentDescription = "Image d'accueil",
                modifier = Modifier.size(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Inscription",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomOutlinedTextField(name, { name = it }, "Nom complet", Icons.Filled.Person)
        CustomOutlinedTextField(email, { email = it }, "Email", Icons.Filled.Email, KeyboardType.Email)
        CustomOutlinedTextField(password, { password = it }, "Mot de passe", Icons.Filled.Lock, visualTransformation = PasswordVisualTransformation())
        CustomOutlinedTextField(phone, { phone = it }, "Téléphone", Icons.Filled.Phone, KeyboardType.Phone)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!validateInputs(context, name, email, password, phone)) return@Button

                val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().apply {
                    putString("email", email)
                    putString("password", password)
                    apply()
                }

                showSuccessDialog = true
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text("S'inscrire", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Déjà un compte ? Se connecter",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { context.startActivity(Intent(context, Login::class.java)) }
                .padding(bottom = 32.dp)
        )
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Succès") },
            text = { Text("Inscription réussie !") },
            confirmButton = {
                Button(onClick = {
                    showSuccessDialog = false
                    context.startActivity(Intent(context, Login::class.java))
                }) {
                    Text("OK")
                }
            }
        )
    }
}

fun validateInputs(context: Context, name: String, email: String, password: String, phone: String): Boolean {
    if (name.isBlank() || !name.matches("^[a-zA-Z\\s]+$".toRegex())) {
        Toast.makeText(context, "Nom invalide", Toast.LENGTH_SHORT).show()
        return false
    }
    if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Toast.makeText(context, "Email invalide", Toast.LENGTH_SHORT).show()
        return false
    }
    if (password.length < 8 || !password.matches(".*[A-Z].*".toRegex()) ||
        !password.matches(".*[0-9].*".toRegex()) || !password.matches(".*[!@#\$%^&*].*".toRegex())) {
        Toast.makeText(context, "Mot de passe non conforme", Toast.LENGTH_SHORT).show()
        return false
    }
    if (!phone.matches("^(?:\\+216|216)?[0-9]{8}$".toRegex())) {
        Toast.makeText(context, "Numéro de téléphone invalide", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        singleLine = true,
    )
}
