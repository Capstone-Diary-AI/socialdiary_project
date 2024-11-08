// DiaryListActivity.kt
// 일기 목록을 보여주는 화면을 위한 액티비티로,
// DiaryListFragment를 불러와서 사용자가 작성한 일기들을 리스트로 표시

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListActivity : AppCompatActivity() {

    // ViewPager2와 BottomNavigationView는 일기 목록 내에서의 화면 전환을 지원하는데 사용될 수 있음
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DiaryListFragment를 호출하여 액티비티의 전체 화면을 채움
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, DiaryListFragment())
                .commit()
        }
    }
}
