package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MatchingDetailScreen(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntry
    val index = navBackStackEntry?.arguments?.getString("index")?.toIntOrNull()

    if (index != null) {
        // 해당 index에 맞는 매칭 상세 정보를 표시
        Text("매칭 상세 정보: $index")
    } else {
        // index 값이 없거나 잘못된 경우 처리
        Text("잘못된 인덱스입니다.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9))
            .padding(16.dp),
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

        Spacer(modifier = Modifier.height(8.dp))

        // Matching detail card
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "제가 절반 부담할게요 ... - 도현킹",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(10.dp))

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
            onClick = { navController.navigate("reportUser") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF627EF5)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(16.dp),
            //.offset(y=197.dp),
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
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF969696),
            modifier = Modifier.width(80.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
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
    val navController = rememberNavController()
    MatchingDetailScreen(navController = navController)
}
