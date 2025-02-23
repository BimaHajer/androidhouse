package com.example.renteazy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DetailHouseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailHouseScreen()
        }
    }
}

@Composable
fun DetailHouseScreen() {
    val context = LocalContext.current
    val houseImages = remember { listOf(R.drawable.house_1, R.drawable.house_4, R.drawable.house_2) }
    val description = "🏡 Cette magnifique maison moderne offre un espace lumineux, une grande terrasse et une vue imprenable sur la nature."
    val features = listOf("🏠 3 Chambres", "🛁 2 Salles de bain", "🌿 Jardin", "🚗 Garage", "🔥 Cheminée", "📶 WiFi inclus")
    val reviews = listOf("⭐ Superbe maison!", "⭐ Très confortable", "⭐ Emplacement parfait", "⭐ Propriétaire très accueillant")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Galerie d'images
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(houseImages) { imageRes ->
                HouseImage(painterResource(id = imageRes))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Détails Maison
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 6.dp,
            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Détails de la Maison", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, fontSize = 16.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Caractéristiques
        SectionTitle("Caractéristiques")
        FeaturesGrid(features)

        Spacer(modifier = Modifier.height(16.dp))

        // Avis clients
        SectionTitle("Avis des Clients")
        ReviewsList(reviews)

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton de discussion
        Button(

            onClick = {

                val intent = Intent(context, ChatActivity::class.java)
                context.startActivity(intent) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.85f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "💬 Discuter avec le propriétaire", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        }

    }
}





@Composable
fun SectionTitle(title: String) {
    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun HouseImage(image: Painter) {
    Image(
        painter = image,
        contentDescription = "Image de la maison",
        modifier = Modifier
            .size(250.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
    )
}

@Composable
fun FeaturesGrid(features: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            features.chunked(2).forEach { rowItems ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    rowItems.forEach { feature ->
                        Text(
                            text = feature,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewsList(reviews: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            reviews.forEach { review ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = review,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
