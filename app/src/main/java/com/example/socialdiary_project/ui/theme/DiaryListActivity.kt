package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListActivity : AppCompatActivity() {

    private lateinit var pageTitle: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_list_navigation)

        // 상단 네비게이션 바 - 페이지 제목 설정
        pageTitle = findViewById(R.id.page_title)

        // ViewPager2 설정
        viewPager = findViewById(R.id.viewPager)
        val fragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = fragmentAdapter

        // ViewPager2 페이지 변경 리스너 - 슬라이드 시 상단 타이틀과 하단 네비게이션 상태 변경
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

        // 하단 네비게이션 바와 ViewPager2 연동
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> viewPager.currentItem = 0
                R.id.nav_diary -> viewPager.currentItem = 1
                R.id.nav_calendar -> viewPager.currentItem = 2
            }
            true
        }

        // 초기 페이지를 일기 목록으로 설정
        viewPager.setCurrentItem(1, false)

        // 설정 버튼 클릭 시 설정 페이지로 이동
        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
