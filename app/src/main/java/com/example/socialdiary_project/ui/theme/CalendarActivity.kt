// CalendarActivity.kt
// 캘린더를 보여주고 날짜 선택 및 팝업 대화창 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.CalendarView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.socialdiary_project.R

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        calendarView.setOnDateChangeListener { _, _, _, _ ->
            // 날짜 선택 로직 추가
        }

        // 팝업 버튼
        val popupButton = findViewById<Button>(R.id.button_popup)
        popupButton.setOnClickListener {
            showFriendActionDialog()
        }
    }

    private fun showFriendActionDialog() {
        val options = arrayOf("그룹 만들기", "친구 추가하기")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("무엇을 하시겠습니까?")
            .setItems(options) { _, _ ->
                // 선택한 작업 처리
            }
        builder.create().show()
    }
}
