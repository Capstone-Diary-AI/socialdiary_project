// SocialDiaryApp.kt
// 앱의 Application 클래스. 앱 전역 설정 및 테마 모드 설정 초기화를 담당

package com.example.socialdiary_project

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class SocialDiaryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // SharedPreferences에서 저장된 테마 모드 설정을 불러옴
        val sharedPref = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val themeMode = sharedPref.getString("theme_mode", "default")

        // 저장된 테마 모드 값에 따라 다크 모드, 라이트 모드 또는 시스템 모드 설정
        when (themeMode) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "default" -> {
                // 안드로이드 버전에 따라 시스템 기본 모드 또는 배터리 절약 모드 적용
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}
