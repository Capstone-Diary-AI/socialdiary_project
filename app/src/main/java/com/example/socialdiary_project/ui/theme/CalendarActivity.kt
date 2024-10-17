// CalendarActivity.kt
// 캘린더를 보여주고 날짜 선택 및 팝업 대화창 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.CalendarView
import android.widget.ImageButton
import com.example.socialdiary_project.R

class CalendarActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        // 캘린더 뷰 설정
        calendarView = findViewById(R.id.calendar_view)
        calendarView.setOnDateChangeListener { _, _, _, _ ->
            // 날짜 선택 로직
        }

        // 플러스 버튼 클릭 이벤트
        val addButton = findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            // 일정 추가 기능 실행
            // 일정 추가 로직 추가
        }
    }
}
