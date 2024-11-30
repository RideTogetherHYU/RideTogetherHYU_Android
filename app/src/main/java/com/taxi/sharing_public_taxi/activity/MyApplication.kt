package com.taxi.sharing_public_taxi.activity

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.taxi.sharing_public_taxi.R
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        var keyHash = Utility.getKeyHash(this)
        Log.d("TAG", "keyhash : ${Utility.getKeyHash(this)}")
        // 카카오 SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}
