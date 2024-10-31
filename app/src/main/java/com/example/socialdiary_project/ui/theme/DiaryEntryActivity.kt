// DiaryEntryActivity.kt
// 일기 작성 화면. 제목, 날짜, 위치, 일기 내용, 사진 선택 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import com.example.socialdiary_project.databinding.ActivityDiaryEntryBinding

class DiaryEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 날짜 선택 버튼 이벤트
        binding.buttonSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // 위치 갱신 버튼 이벤트
        binding.buttonRefreshLocation.setOnClickListener {
            // 위치 갱신 로직
        }

        // 저장 버튼 이벤트
        binding.buttonSaveDiary.setOnClickListener {
            // 일기 저장 로직
        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // 선택된 날짜 처리 로직
        }, 2024, 6, 2) // 기본 날짜
        datePickerDialog.show()
    }
}
