package com.example.ridetogetherhyu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4E6BF5)),
        contentAlignment = Alignment.Center
    ) {
        // 로고 텍스트 또는 이미지
        Text(
            text = "같이타HYU",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }


    // 5초 후 로그인 화면으로 전환
    LaunchedEffect(Unit) {
        delay(3000) // 5초 지연
        onTimeout()
    }
}
