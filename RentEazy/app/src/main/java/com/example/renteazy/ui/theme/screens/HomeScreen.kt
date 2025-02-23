package com.example.renteazy.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.renteazy.data.listOfConversations
import com.example.renteazy.ui.components.ConversationItem
import com.example.renteazy.ui.components.HomeAppBar

@Composable
fun HomeScree(navController: NavController) {
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        containerColor = Color.Transparent,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(listOfConversations) { conversation ->
                ConversationItem(
                    conversation = conversation,
                    onClick = {
                        navController.navigate("chat")
                    }
                )
            }
        }
    }
}