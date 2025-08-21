package com.example.chhuparustam.uis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUI() {
    var userInput by remember { mutableStateOf("") }
    var normalMeaning by remember { mutableStateOf("") }
    var doubleMeaning by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("🍆 Boys") }
    var expanded by remember { mutableStateOf(false) }

    // Categories with dirty lines
    val responses = mapOf(
        "🍆 Boys" to listOf(
            "तेरा लौड़ा आग लगा देगा अंदर जाते ही 🔥",
            "तेरा लंड इतना मोटा है कि चूत फट जाएगी 😈",
            "तेरा खड़ा हुआ लौड़ा देखकर मुँह में पानी आ रहा है 👅",
            "इतना सख्त लौड़ा चाहिए कि पूरी रात चीख निकल जाए 🍆",
            "तेरी जांघों से टकराता लंड पागल कर देगा 😏"
        ),
        "🍑 Girls" to listOf(
            "तेरी चूत गीली करके चाटना चाहता हूँ 💦",
            "तेरे दूध दबाकर निप्पल चूसूँगा 🍒",
            "तेरी गांड में थपकी मारकर धीरे-धीरे भरना है 🔥",
            "तेरी रसदार चूत से मुँह नहीं हटेगा 👅",
            "तेरी बोतल जैसी गांड देखके लंड और खड़ा हो गया 🍑"
        ),
        "💦 Acts" to listOf(
            "तेरे होंठ चूसते-चूसते लंड गरम कर दूँगा 🫦",
            "तेरी चूत में जीभ डालकर पानी निकालूँगा 👅💦",
            "तेरी गांड पर थप्पड़ मारते-मारते और सख्त हो जाऊँगा 🍑",
            "तेरे निप्पल चूसते-चूसते चूत भी गीली कर दूँगा 🍒",
            "तेरे बदन पर लंड रगड़कर पागल कर दूँगा 🔥"
        )
    )

    // Emoji → category map
    val emojiMap = mapOf(
        "🍆" to "🍆 Boys",
        "🥒" to "🍆 Boys",
        "🌽" to "🍆 Boys",

        "🍑" to "🍑 Girls",
        "🍒" to "🍑 Girls",
        "🥭" to "🍑 Girls",
        "🌸" to "🍑 Girls",

        "💦" to "💦 Acts",
        "👅" to "💦 Acts",
        "🫦" to "💦 Acts",
        "🔥" to "💦 Acts"
    )

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
                // Input field
                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = {
                        Text("💬 Enter emoji/text...", color = Color(0xFFBBDEFB))
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

                Spacer(modifier = Modifier.height(20.dp))

                // Category dropdown
                Box {
                    OutlinedButton(onClick = { expanded = true }) {
                        Text(selectedCategory, color = Color.White)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        responses.keys.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                // Button
                ExpensiveButton(
                    onClick = {
                        normalMeaning =
                            "👉 Input: ${userInput.ifEmpty { "कुछ नहीं लिखा 😅" }}"

                        // Detect emoji
                        var categoryFromEmoji: String? = null
                        for ((emoji, cat) in emojiMap) {
                            if (userInput.contains(emoji)) {
                                categoryFromEmoji = cat
                                break
                            }
                        }

                        val finalCategory =
                            categoryFromEmoji ?: selectedCategory

                        doubleMeaning = "🔞 Hidden Dirty: " +
                                responses[finalCategory]!![Random.nextInt(responses[finalCategory]!!.size)]
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                if (normalMeaning.isNotEmpty()) {
                    MeaningCard("📝 Input", normalMeaning, Color(0xFF00E676))
                }

                if (doubleMeaning.isNotEmpty()) {
                    MeaningCard("🔥 Dirty Output", doubleMeaning, Color(0xFFFF3D00))
                }
            }
        }
    }
}
