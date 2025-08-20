package com.example.chhuparustam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chhuparustam.ui.theme.ChhupaRustamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChhupaRustamTheme {
                AppUI()
            }
        }
    }
}

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

@Composable
fun ExpensiveButton(onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f)

    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                onClick = { pressed = !pressed }
            ),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Brush.horizontalGradient(
                listOf(Color(0xFFFF512F), Color(0xFFDD2476))
            ).toColor()
        ),
        elevation = ButtonDefaults.buttonElevation(12.dp)
    ) {
        Text(
            "🔍 Decode",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

// Convert Brush to Solid Color (hack for Button background)
fun Brush.toColor(): Color = Color.White.copy(alpha = 0f)

@Composable
fun MeaningCard(title: String, text: String, accent: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = accent
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = text,
                fontSize = 17.sp,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
    }
}
