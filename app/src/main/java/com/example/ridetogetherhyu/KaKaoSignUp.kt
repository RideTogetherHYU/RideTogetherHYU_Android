package com.example.ridetogetherhyu

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun KakaoSignUpScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            // horizontalAlignment = Alignment.CenterHorizontally, // 중앙 정렬
            //verticalArrangement = Arrangement.Center // 세로 중앙 정렬

        ) {
            // "로그인" 텍스트
            Text(
                text = "회원가입",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 38.sp,
                letterSpacing = (-0.32).sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .width(88.dp)  // 너비
                    .height(38.dp) // 높이
                    .padding(bottom = 0.dp) // 아래 여백
                    .offset(x = 29.dp, y = 196.dp) // Y축 오프셋
            )
        }
        // 카카오톡 로그인 버튼 이미지
        Image(
            painter = painterResource(id = R.drawable.kakao_login_large_wide),
            contentDescription = "카카오로 시작하기 버튼 이미지",
            modifier = Modifier
                .width(380.dp)
                .height(75.dp)
                .offset(y = 700.dp)
                .clickable { startSignUp(context) }
                .padding(start = 29.dp)
        )
    }
}

/* private fun startSignUp(context: android.content.Context) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            // 카카오톡이 설치되어 있지 않은 경우
            if (error is com.kakao.sdk.common.model.ClientError && error.reason == com.kakao.sdk.common.model.ClientErrorCause.Cancelled) {
                // 웹 브라우저로 로그인
                loginWithKakaoAccount(context)
            } else {
                Log.e("KakaoLogin", "Failed: ${error.message}")
                Toast.makeText(context, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        } else if (token != null) {
            // 액세스 토큰을 받아옴
            val accessToken = token.accessToken
            Log.i("KakaoLogin", "Success. AccessToken: $accessToken")
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
} */


private fun startSignUp(context: android.content.Context) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            Log.e("KakaoLogin", "Failed: ${error.message}")
            Toast.makeText(context, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            // 액세스 토큰을 받아옴
            val accessToken = token.accessToken
            Log.i("KakaoLogin", "Success. AccessToken: $accessToken")
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
}

