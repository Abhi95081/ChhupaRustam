package com.example.chhuparustam.uis

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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