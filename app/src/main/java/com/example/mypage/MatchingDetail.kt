package com.example.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MatchingDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Top bar with back button and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* 뒤로가기 기능 */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "완료한 매칭이력",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Matching detail card
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFF0F0F0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "제가 절반 부담할게요 ... - 도현킹",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(8.dp))

                DetailItem(label = "탑승 시간", value = "2025년 1월 15일 15시 30분")
                DetailItem(label = "도착 시간", value = "2025년 1월 15일 15시 38분")
                DetailItem(label = "이동 장소", value = "한양대 정문 → 한대앞 역")
                DetailItem(label = "대표 동승자", value = "도현킹")
                DetailItem(label = "동승자", value = "이 밴쌔 / 김 숭 / 최 페페")
                DetailItem(label = "최종 금액", value = "6800원")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Report button
        Button(
            onClick = { /* 유저 신고 기능 */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF627EF5)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "유저 신고하기",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF969696),
            modifier = Modifier.width(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchingDetailScreen() {
    MatchingDetailScreen()
}
