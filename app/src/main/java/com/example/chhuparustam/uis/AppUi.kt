package com.example.chhuparustam.uis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUI() {
    var userInput by remember { mutableStateOf("") }
    var normalMeaning by remember { mutableStateOf("") }
    var doubleMeaning by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "🤫 ChhupaRustam",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,
                        color = Color.White,
                        modifier = Modifier.graphicsLayer {
                            shadowElevation = 16f
                            renderEffect = null
                        }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
                    )
                )
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = {
                        Text("💬 Enter your text...", color = Color(0xFFBBDEFB))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Cyan,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Cyan,
                        cursorColor = Color.Cyan
                    )
                )

                Spacer(modifier = Modifier.height(25.dp))

                ExpensiveButton(
                    onClick = {
                        normalMeaning = "👉 Normal: ${userInput.ifEmpty { "Nothing entered" }}"
                        doubleMeaning = "😏 Double Meaning: Hidden spicy context detected!"
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                if (normalMeaning.isNotEmpty()) {
                    MeaningCard("✨ Clean Sense", normalMeaning, Color(0xFF00E676))
                }

                if (doubleMeaning.isNotEmpty()) {
                    MeaningCard("🔥 Hidden Sense", doubleMeaning, Color(0xFFFFC107))
                }
            }
        }
    }
}