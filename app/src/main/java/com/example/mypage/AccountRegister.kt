package com.example.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Brush

@Composable
fun AccountRegistrationScreen() {
    var selectedAccount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("계좌등록 / 변경", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            backgroundColor = Color.White,
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = { /* 뒤로가기 처리 */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Selected Account
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF3E64FF), shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "⭐ 카카오뱅크 398490238492849",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "내 계좌",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Account List
        AccountItem("카카오뱅크 398490238492849", isSelected = selectedAccount == 0) { selectedAccount = 0 }
        AccountItem("신한은행 11048829992", isSelected = selectedAccount == 1) { selectedAccount = 1 }
        AccountItem("국민은행 34992030403", isSelected = selectedAccount == 2) { selectedAccount = 2 }

        Spacer(modifier = Modifier.height(20.dp))

        // Add New Account Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
                .clickable { /* 새 계좌 추가 처리 */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                color = Color.Gray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
        //BottomNavigationBar()
    }
}

@Composable
fun AccountItem(accountName: String, isSelected: Boolean, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(50.dp)
            .background(
                color = if (isSelected) Color.White else Color(0xFFF5F5F5),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onSelect() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = if (isSelected) "⭐ $accountName" else "☆ $accountName",
                fontSize = 14.sp,
                color = if (isSelected) Color(0xFF3E64FF) else Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Edit",
                tint = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAccountRegistrationScreen() {
    AccountRegistrationScreen()
}
