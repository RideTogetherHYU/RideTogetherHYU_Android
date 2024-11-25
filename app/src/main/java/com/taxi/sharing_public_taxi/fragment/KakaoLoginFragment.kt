package com.taxi.sharing_public_taxi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.taxi.sharing_public_taxi.databinding.FragmentKakaoLoginBinding

class KakaoLoginFragment : Fragment() {

    private var _binding: FragmentKakaoLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKakaoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 카카오 로그인 이미지 버튼 클릭 이벤트
        binding.kakaoLoginButton.setOnClickListener {
            // 로그인 로직 추가
            loginWithKakao()
        }
    }
    private fun loginWithKakao() {
        // 카카오톡으로 로그인 시도
        UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
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

    private fun fetchUserData(token: OAuthToken) {
        // 로그인 성공 후, 사용자 정보 가져오기
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                // 사용자 정보 요청 실패 처리
                showError(error)
            } else if (user != null) {
                // 사용자 정보 처리
                handleUserInfo(user)
            }
        }
    }

    private fun showError(error: Throwable) {
        // 에러 처리 (로그 또는 Toast 등)
        error.printStackTrace()
        Toast.makeText(requireContext(), "로그인 실패: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun handleUserInfo(user: com.kakao.sdk.user.model.User) {
        // 사용자 정보 처리 로직
        // 예: user.id, user.kakaoAccount?.profile?.nickname 등
        Toast.makeText(requireContext(), "로그인 성공: ${user.kakaoAccount?.profile?.nickname}", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}