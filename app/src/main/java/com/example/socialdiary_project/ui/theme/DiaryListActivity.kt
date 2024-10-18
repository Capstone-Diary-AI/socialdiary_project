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

        pageTitle = findViewById(R.id.page_title)
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // ViewPager 어댑터 설정
        val fragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = fragmentAdapter

        // 하단 네비게이션 클릭 이벤트 처리
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> {
                    viewPager.currentItem = 0  // 친구 목록 페이지
                    true
                }
                R.id.nav_diary -> {
                    viewPager.currentItem = 1  // 일기 목록 페이지
                    true
                }
                R.id.nav_calendar -> {
                    viewPager.currentItem = 2  // 캘린더 페이지
                    true
                }
                else -> false
            }
        }

        // ViewPager2 페이지 전환 리스너 설정
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
    }
}
