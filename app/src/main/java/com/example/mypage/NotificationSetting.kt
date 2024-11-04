package com.example.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun NotificationSettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("알림", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
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

        // Notification Settings Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "알림",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            NotificationToggleItem("매칭 요청 알림")
            NotificationToggleItem("매칭 수락 알림")
            NotificationToggleItem("출발까지 남은 시간 알림")
            NotificationToggleItem("도착 / 송금 알림")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
        //BottomNavigationBar()
    }
}

@Composable
fun NotificationToggleItem(text: String) {
    var isChecked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF3E64FF),
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color.Gray
            )
        )
    }
}

//@Composable
//fun BottomNavigationBar() {
    //BottomNavigation(
        //backgroundColor = Color.White,
        //elevation = 8.dp
    //) {
        //BottomNavigationItem(
            //icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            //selected = false,
            //onClick = { /* 홈으로 이동 */ }
        //)
        //BottomNavigationItem(
            //icon = { Icon(painterResource(id = R.drawable.ic_account), contentDescription = "Account", tint = Color(0xFF3E64FF)) },
            //selected = true,
            //onClick = { /* 계좌 등록으로 이동 */ }
        //)
    //}
//}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationSettingsScreen() {
    NotificationSettingsScreen()
}
