package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기화
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        toolbar = findViewById(R.id.top_navigation)

        // ViewPager 어댑터 설정
        val fragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = fragmentAdapter

        // 하단 네비게이션바 클릭 이벤트
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> viewPager.currentItem = 0
                R.id.nav_diary -> viewPager.currentItem = 1
                R.id.nav_calendar -> viewPager.currentItem = 2
            }
            true
        }

        // ViewPager 페이지 변경 리스너
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        bottomNavigationView.menu.findItem(R.id.nav_friends).isChecked = true
                        toolbar.title = "친구 목록"
                    }
                    1 -> {
                        bottomNavigationView.menu.findItem(R.id.nav_diary).isChecked = true
                        toolbar.title = "일기 목록"
                    }
                    2 -> {
                        bottomNavigationView.menu.findItem(R.id.nav_calendar).isChecked = true
                        toolbar.title = "캘린더"
                    }
                }
            }
        })

        // 상단의 + 버튼과 설정 버튼 클릭 이벤트
        val addButton = toolbar.findViewById<ImageButton>(R.id.add_button)
        val settingsButton = toolbar.findViewById<ImageButton>(R.id.settings_button)

        addButton.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> {
                    // 친구 관리 팝업 띄우기
                    // 예시로 다이얼로그를 띄운다고 가정하겠습니다.
                    // showFriendManagementDialog()
                }
                1 -> {
                    // 일기 작성 페이지로 이동
                    val intent = Intent(this, DiaryEntryActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    // 일정 추가 팝업 띄우기
                    // 예시로 다이얼로그를 띄운다고 가정하겠습니다.
                    // showCalendarEventDialog()
                }
            }
        }

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // 시작 페이지를 일기 목록으로 설정
        viewPager.currentItem = 1
    }
}
