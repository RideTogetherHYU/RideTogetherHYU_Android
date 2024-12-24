package com.taxi.sharing_public_taxi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import com.taxi.sharing_public_taxi.compose.EditProfileScreen
//import com.taxi.sharing_public_taxi.ui.theme.MyPageTheme

class MyPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ComposeView를 사용하여 Compose UI를 렌더링합니다.
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()

                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        EditProfileScreen(navController = navController)
                    }
                }
            }
        }
    }
}
