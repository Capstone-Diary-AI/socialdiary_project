package com.example.socialdiary_project.ui.theme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.socialdiary_project.R

abstract class BaseActivity : AppCompatActivity() {

    // BroadcastReceiver 정의
    private val settingsChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "com.example.socialdiary_project.SETTINGS_CHANGED") {
                // 설정이 변경되었을 때 UI에 반영
                applySettings()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySettings() // 설정 적용

        // BroadcastReceiver 등록
        val filter = IntentFilter("com.example.socialdiary_project.SETTINGS_CHANGED")

        // Android 12(API 31) 이상일 경우 export 여부를 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(settingsChangedReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(settingsChangedReceiver, filter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(settingsChangedReceiver) // BroadcastReceiver 해제
    }

    // 설정 적용 메서드
    private fun applySettings() {
        // SharedPreferences에서 설정 값 가져오기
        val preferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val selectedFontResId = preferences.getInt("font_preference", R.font.gmarket_sans_ttf_light)
        val selectedFontSize = preferences.getFloat("font_size_preference", 16f)

        // 폰트와 폰트 크기 적용
        applySelectedFont(selectedFontResId)
        applyFontSizeRecursively(window.decorView, selectedFontSize)
    }

    private fun applySelectedFont(fontResId: Int) {
        val typeface: Typeface? = ResourcesCompat.getFont(this, fontResId)
        typeface?.let {
            applyFontRecursively(window.decorView, it)
        }
    }

    private fun applyFontRecursively(view: View, typeface: Typeface) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                applyFontRecursively(child, typeface)
            }
        } else if (view is TextView) {
            view.typeface = typeface
        }
    }

    private fun applyFontSizeRecursively(view: View, fontSize: Float) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                applyFontSizeRecursively(child, fontSize)
            }
        } else if (view is TextView) {
            view.textSize = fontSize
        }
    }
}
