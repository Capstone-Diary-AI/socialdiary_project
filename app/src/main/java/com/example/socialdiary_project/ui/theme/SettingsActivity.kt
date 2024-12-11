// SettingsActivity.kt
package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.example.socialdiary_project.R
import com.example.socialdiary_project.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var currentMode: Int = AppCompatDelegate.MODE_NIGHT_NO // 초기값: 라이트 모드
    private var currentFontResId: Int = R.font.gmarket_sans_ttf_light // 초기 폰트 설정
    private var currentFontSize: Float = 16f // 초기 글자 크기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 저장된 설정 불러오기
        val preferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        currentMode = preferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_NO)
        currentFontResId = preferences.getInt("font_preference", R.font.gmarket_sans_ttf_light)
        currentFontSize = preferences.getFloat("font_size_preference", 16f)

        // 초기 설정 적용
        AppCompatDelegate.setDefaultNightMode(currentMode)
        applySelectedFont(currentFontResId)
        applyFontSizeRecursively(binding.root, currentFontSize)

        // Toolbar 설정
        setSupportActionBar(binding.settingsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 뒤로가기 버튼 클릭 리스너
        binding.settingsToolbar.setNavigationOnClickListener {
            finish()
        }

        // 라디오 버튼 선택 이벤트 (테마 변경 즉시 적용)
        binding.radioMode.setOnCheckedChangeListener { _, checkedId ->
            val editor = preferences.edit()
            val selectedMode = when (checkedId) {
                R.id.rbLight -> AppCompatDelegate.MODE_NIGHT_NO
                R.id.rbDark -> AppCompatDelegate.MODE_NIGHT_YES
                R.id.rbDefault -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                } else {
                    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                }
                else -> currentMode
            }

            if (currentMode != selectedMode) {
                currentMode = selectedMode
                AppCompatDelegate.setDefaultNightMode(selectedMode)
                editor.putInt("theme_mode", selectedMode)
                editor.apply()
            }
        }

        // 글꼴 선택 스피너 설정 (선택 시 즉시 적용)
        ArrayAdapter.createFromResource(
            this,
            R.array.font_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFont.adapter = adapter
        }

        binding.spinnerFont.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val editor = preferences.edit()
                val selectedFontResId = when (position) {
                    0 -> R.font.gmarket_sans_ttf_light
                    1 -> R.font.gmarket_sans_ttf_medium
                    2 -> R.font.gmarket_sans_ttf_bold
                    else -> R.font.gmarket_sans_ttf_light
                }

                if (currentFontResId != selectedFontResId) {
                    currentFontResId = selectedFontResId
                    editor.putInt("font_preference", selectedFontResId)
                    editor.apply()
                    applySelectedFont(selectedFontResId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // 글자 크기 선택 스피너 설정 (선택 시 즉시 적용)
        ArrayAdapter.createFromResource(
            this,
            R.array.font_size_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFontSize.adapter = adapter
        }

        binding.spinnerFontSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val editor = preferences.edit()
                val selectedFontSize = when (position) {
                    0 -> 14f
                    1 -> 16f
                    2 -> 18f
                    3 -> 20f
                    else -> 16f
                }

                if (currentFontSize != selectedFontSize) {
                    currentFontSize = selectedFontSize
                    editor.putFloat("font_size_preference", selectedFontSize)
                    editor.apply()
                    applyFontSizeRecursively(binding.root, selectedFontSize)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun applySelectedFont(fontResId: Int) {
        try {
            val typeface = ResourcesCompat.getFont(this, fontResId)
            typeface?.let { applyFontRecursively(binding.root, it) }
        } catch (e: Exception) {
            e.printStackTrace()
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
