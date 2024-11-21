package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun MatchingHistoryScreen() {
    // LocalConfiguration을 사용하여 화면 크기 가져오기
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {
        // Top bar with back button and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 22.dp, end = 114.dp, top = 13.dp, bottom = 13.dp)
                .offset(y = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Back button action */ }
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "완료한 매칭이력",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 21.sp,
                letterSpacing = (-0.32).sp,
                color = Color(0xFF333333),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .width(320.dp)
                .height(IntrinsicSize.Max)
                .offset(x = 20.dp, y = 100.dp)
        ) {
            // List of completed matching history items
            val historyList = listOf(
                "2025년 1월 15일\n제가 절반 부담할게요 ... - 도현킹",
                "2025년 1월 15일\n빠르게 ㅃㄹ - 한국인815",
                "2025년 1월 10일\n같이가자 어서어서 - 숭",
                "2024년 12월 25일\n빨리갈 사람 - 초이밍키2",
                "2024년 12월 13일\n...사람구해요... - 페페",
                "2024년 12월 12일\n말 안하고 갈사람 - 서멍"
            )

            historyList.forEach { historyItem ->
                HistoryCard(text = historyItem, screenHeight = screenHeight) // screenHeight를 매개변수로 전달
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun HistoryCard(text: String, screenHeight: Dp) { // screenHeight를 매개변수로 받음
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * (80f / 800f)) // 화면 비율에 따라 높이 설정
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val parts = text.split("\n")
            if (parts.size == 2) {
                Text(
                    text = parts[0],
                    fontSize = 12.sp,
                    color = Color(0xFF838383),
                    lineHeight = 21.sp,
                    letterSpacing = (-0.32).sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = parts[1],
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    lineHeight = 21.sp,
                    letterSpacing = (-0.32).sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(text = text, fontSize = 14.sp, color = Color(0xFF333333))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchingHistoryScreen() {
    MatchingHistoryScreen()
}