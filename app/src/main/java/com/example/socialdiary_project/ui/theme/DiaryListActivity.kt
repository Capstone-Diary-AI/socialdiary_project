package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pageTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // activity_main에 ViewPager와 네비게이션바 설정

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        pageTitle = findViewById(R.id.page_title) // 상단 네비게이션 바의 제목 텍스트

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
                        pageTitle.text = "친구 목록" // 페이지에 맞게 제목 설정
                    }
                    1 -> {
                        bottomNavigationView.menu.findItem(R.id.nav_diary).isChecked = true
                        pageTitle.text = "일기 목록" // 페이지에 맞게 제목 설정
                    }
                    2 -> {
                        bottomNavigationView.menu.findItem(R.id.nav_calendar).isChecked = true
                        pageTitle.text = "캘린더" // 페이지에 맞게 제목 설정
                    }
                }
            }
        })

        // 시작 페이지를 일기 목록으로 설정
        viewPager.currentItem = 1
        pageTitle.text = "일기 목록"
    }
}