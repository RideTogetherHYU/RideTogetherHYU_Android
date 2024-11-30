package com.taxi.sharing_public_taxi.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.taxi.sharing_public_taxi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding 초기화
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카카오 로그인 버튼 클릭
        binding.kakaoLoginButton.setOnClickListener {
            loginWithKakao()
        }
    }

    private fun loginWithKakao() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "로그인 실패", error)
                Toast.makeText(this, "로그인 실패: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
            } //else if (token != null) {
                //Log.i("KakaoLogin", "로그인 성공: ${token.accessToken}")
                // 로그인 성공 후 다음 화면으로 이동
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                //finish() // 현재 액티비티 종료
            //}
        }
    }
}
