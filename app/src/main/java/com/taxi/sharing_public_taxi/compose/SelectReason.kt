package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectReasonScreen() {
    var selectedReason by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("유저 신고하기", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            backgroundColor = Color.White,
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = { /* 뒤로가기 처리 */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* 닫기 처리 */ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "신고사유를 선택해주세요",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Report Reasons
        val reasons = listOf("미송금", "절도", "폭행", "비매너", "기타사유")
        reasons.forEach { reason ->
            ReportReasonButton(
                reason = reason,
                isSelected = selectedReason == reason,
                onClick = { selectedReason = reason }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Report Button
        Button(
            onClick = { /* 신고하기 처리 */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            enabled = selectedReason.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (selectedReason.isNotEmpty()) Color.Gray else Color(0xFFE0E0E0),
                contentColor = Color.White
            )
        ) {
            Text(text = "신고하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ReportReasonButton(reason: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = if (isSelected) Color(0xFFE0E0E0) else Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = reason,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectReasonScreen() {
    SelectReasonScreen()
}
