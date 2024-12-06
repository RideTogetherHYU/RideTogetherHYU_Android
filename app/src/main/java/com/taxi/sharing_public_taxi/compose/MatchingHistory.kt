package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MatchingHistoryScreen(navController: NavHostController) {
    // LocalConfiguration을 사용하여 화면 크기 가져오기
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // Top Bar
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color(0xFFF5F6F9),
            modifier = Modifier.height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                //.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "뒤로가기"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "완료한 매칭 이력",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        val historyList = listOf(
            "2025년 1월 15일\n제가 절반 부담할게요 ... - 도현킹",
            "2025년 1월 15일\n빠르게 ㅃㄹ - 한국인815",
            "2025년 1월 10일\n같이가자 어서어서 - 숭",
            "2024년 12월 25일\n빨리갈 사람 - 초이밍키2",
            "2024년 12월 13일\n...사람구해요... - 페페",
            "2024년 12월 12일\n말 안하고 갈사람 - 서멍"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
            //.offset(x = 20.dp, y = 80.dp)
        ) {
            historyList.forEachIndexed { index, historyItem ->
                HistoryCard(
                    text = historyItem,
                    screenHeight = screenHeight,
                    onClick = {
                        navController.navigate("matchingdetails/$index")
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun HistoryCard(text: String, screenHeight: Dp, onClick: () -> Unit) { // screenHeight를 매개변수로 받음
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * (80f / 800f)) // 화면 비율에 따라 높이 설정
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
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
    val navController = rememberNavController()
    MatchingHistoryScreen(navController = navController)
}
