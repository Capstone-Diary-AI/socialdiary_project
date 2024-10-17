package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListActivity : AppCompatActivity() {

    private lateinit var pageTitle: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_list)

        // ViewPager 중복 설정 방지
        viewPager = findViewById(R.id.viewPager)
        if (viewPager.adapter == null) {
            val fragmentAdapter = ViewPagerFragmentAdapter(this)
            viewPager.adapter = fragmentAdapter

            // 초기 페이지를 일기 목록으로 설정
            viewPager.setCurrentItem(1, false)
        }

        // 상단 네비게이션 바 - 페이지 제목 설정
        pageTitle = findViewById(R.id.page_title)

        // ViewPager 페이지 변경 리스너 설정
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        pageTitle.text = "친구 목록"
                        bottomNavigationView.menu.findItem(R.id.nav_friends).isChecked = true
                    }
                    1 -> {
                        pageTitle.text = "일기 목록"
                        bottomNavigationView.menu.findItem(R.id.nav_diary).isChecked = true
                    }
                    2 -> {
                        pageTitle.text = "캘린더"
                        bottomNavigationView.menu.findItem(R.id.nav_calendar).isChecked = true
                    }
                }
            }
        })

        // 하단 네비게이션 바와 ViewPager 연동
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> viewPager.currentItem = 0
                R.id.nav_diary -> viewPager.currentItem = 1
                R.id.nav_calendar -> viewPager.currentItem = 2
            }
            true
        }

        // 플러스 버튼 클릭 이벤트 설정
        val addButton = findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(this, DiaryEntryActivity::class.java)
            startActivity(intent)
        }

        // 설정 버튼 클릭 이벤트 설정
        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
