package com.example.ridetogetherhyu

import android.os.Bundle
import android.util.Log // Log 클래스 import
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility // Utility 클래스 import
import com.example.ridetogetherhyu.ui.theme.RideTogetherHYUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kakao SDK 초기화 (필요한 경우)
        KakaoSdk.init(this, "kakaoc086fdfcffc75554612578042121a75b") // 여기에 네이티브 앱 키를 넣으세요

        // 해시 키 출력
        Log.d("TAG", "keyhash : ${Utility.getKeyHash(this)}")

        setContent {
            var showSplash by remember {mutableStateOf(true)}
            if (showSplash) {
                SplashScreen(onTimeout = { showSplash = false })
            } else {
                KakaoSignUpScreen()
            }
        }

        // UI 구성

    }
}

