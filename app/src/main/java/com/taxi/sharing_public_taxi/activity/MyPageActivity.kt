package com.taxi.sharing_public_taxi.activity

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.compose.EditProfileScreen
import com.taxi.sharing_public_taxi.fragment.AddFragment
import com.taxi.sharing_public_taxi.fragment.HomeFragment
import com.taxi.sharing_public_taxi.ui.theme.MyPageTheme

class MyPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_mypage.xml 레이아웃을 연결
        setContentView(R.layout.activity_mypage)

        // BottomNavigationView 설정
        setBottomNavigationView()

        enableEdgeToEdge()

        // Compose Content 설정
        setContent {
            MyPageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditProfileScreen()
                }
            }
        }
    }

    private fun setBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    // HomeFragment로 이동
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.fragment_add -> {
                    // AddFragment로 이동
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, AddFragment())
                        .commit()
                    true
                }
                R.id.menu_mypage -> {
                    // 현재 페이지 유지
                    true
                }
                else -> false
            }
        }
    }
}
