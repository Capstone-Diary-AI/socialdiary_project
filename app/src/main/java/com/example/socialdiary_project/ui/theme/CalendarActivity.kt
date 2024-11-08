// CalendarActivity.kt
// 캘린더 화면을 위한 액티비티로, CalendarFragment를 불러와 화면에 표시
// 사용자가 날짜를 선택하고 일정을 확인하거나 추가할 수 있는 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // CalendarFragment를 호출하여 액티비티의 전체 화면을 채움
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, CalendarFragment())
                .commit()
        }
    }
}
