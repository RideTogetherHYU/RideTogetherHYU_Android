package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ReportUserScreen(navController: NavHostController) {
    var selectedUser by remember { mutableStateOf<String?>(null) }
    val users = listOf("강하나", "최지영", "이예린")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar with title and close button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "유저 신고하기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.weight(2f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* 닫기 기능 */ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Prompt text
        Text(
            text = "신고할 유저를 선택해주세요",
            fontSize = 14.sp,
            color = Color(0xFF969696),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User selection buttons
        users.forEach { user ->
            UserButton(
                userName = user,
                isSelected = user == selectedUser,
                onClick = { selectedUser = user }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        // Next button
        Button(
            onClick = { /* 다음으로 이동 기능 */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (selectedUser != null) Color(0xFF627EF5) else Color(0xFFD3D3D3)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(25.dp),
            enabled = selectedUser != null
        ) {
            Text(
                text = "다음으로",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (selectedUser != null) Color.White else Color.White
            )
        }
    }
}

@Composable
fun UserButton(userName: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) Color(0xFF627EF5) else Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = userName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 12.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportUserScreen() {
    val navController = rememberNavController()
    ReportUserScreen(navController = navController)
}
