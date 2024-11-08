// DiaryEntryActivity.kt
// 사용자가 일기를 작성하는 화면을 위한 액티비티로,
// 제목, 날짜 선택, 위치, 일기 내용 작성 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import com.example.socialdiary_project.databinding.ActivityDiaryEntryBinding

class DiaryEntryActivity : AppCompatActivity() {

    // View Binding을 사용하여 XML 레이아웃과 연결
    private lateinit var binding: ActivityDiaryEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActivityDiaryEntryBinding을 통해 레이아웃 설정
        binding = ActivityDiaryEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상단 네비게이션 바 설정
        setSupportActionBar(binding.diaryToolbar)

        // 뒤로가기 버튼 클릭 리스너
        binding.diaryToolbar.setNavigationOnClickListener {
            onBackPressed() // 뒤로가기 기능 수행
        }

        // 날짜 선택 버튼 클릭 리스너 설정
        binding.buttonSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // 위치 갱신 버튼 클릭 리스너 설정
        binding.buttonRefreshLocation.setOnClickListener {
            // 위치 갱신 로직
        }

        // 일기 저장 버튼 클릭 리스너 설정
        binding.buttonSaveDiary.setOnClickListener {
            // 일기 저장 로직
        }
    }

    // 날짜 선택 다이얼로그를 표시하는 함수
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // 선택된 날짜 처리 로직
        }, 2024, 6, 2) // 기본 날짜 설정
        datePickerDialog.show()
    }
}
