package com.taxi.sharing_public_taxi.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.activity.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스플래시 화면을 위한 레이아웃 설정
        setContentView(R.layout.activity_splash)

        // 3초 후에 로그인 화면으로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // 스플래시 화면 종료
        }, 3000) // 3초 대기 후 로그인 화면으로 전환
    }
}
