// CalendarActivity.kt
// 캘린더를 보여주고 날짜 선택 및 팝업 대화창 기능 제공
package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // CalendarFragment를 호출
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, CalendarFragment()) // 프래그먼트로 교체
                .commit()
        }
    }
}
