package com.example.ridetogetherhyu

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.kakao.sdk.user.UserApiClient
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun KakaoLoginScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // "로그인" 텍스트 추가
            Text(
                text = "로그인",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 38.sp,
                letterSpacing = (-0.32).sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .width(66.dp)  // 너비
                    .height(38.dp) // 높이
                    .padding(bottom = 0.dp) // 아래 여백
                    .offset(x = 29.dp, y = 196.dp) // Y축 오프셋
                //.padding(start = 10.dp) // X축 오프셋
            )

            // 이미지 표시
            Image(
                painter = painterResource(id = R.drawable.kakao_login_medium_wide), // 이미지 리소스 ID
                contentDescription = "카카오 로그인 버튼 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    //.offset(x = 29.dp, y = 671.dp)
                    .clickable {
                        loginWithKakao(context)
                    }
            )

            //Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

private fun loginWithKakao(context: android.content.Context) {
    // 카카오 로그인 요청
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e("KakaoLogin", "카카오 로그인 실패: ${error.message}")
            Toast.makeText(context, "카카오 로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            //val kakaoAccessToken = token.accessToken
            Log.i("KakaoLogin", "카카오 로그인 성공, 액세스 토큰: ${token.accessToken}")
            Toast.makeText(context, "카카오 로그인 성공", Toast.LENGTH_SHORT).show()
            requestKakaoUserInfo(context)
        }
    }
}

private fun requestKakaoUserInfo(context: android.content.Context) {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Log.e("KakaoLogin", "사용자 정보 요청 실패: $(error.message}")
            Toast.makeText(context, "사용자 정보 요청 실패: ${error.message}", Toast.LENGTH_SHORT).show()
        } else if (user != null) {
            val userId = user.id
            val nickname = user.kakaoAccount?.profile?.nickname
            val email = user.kakaoAccount?.email

            Log.i("KakaoLogin", "사용자 정보: ID=$userId, 닉네임=$nickname, 이메일=$email")
            Toast.makeText(context, "ID=$userId, 닉네임=$nickname", Toast.LENGTH_SHORT).show()

        }
    }
}