package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_list_navigation) // 새로운 레이아웃 파일 사용

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // ViewPager 어댑터 설정
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // 하단 네비게이션 바 클릭 시 페이지 이동
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_friends -> viewPager.currentItem = 0
                R.id.nav_diary -> viewPager.currentItem = 1
                R.id.nav_calendar -> viewPager.currentItem = 2
            }
            true
        }

        // 슬라이드로 페이지가 이동할 때 하단 네비게이션 바 업데이트
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.nav_friends).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.nav_diary).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.nav_calendar).isChecked = true
                }
            }
        })
    }

    private inner class ScreenSlidePagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FriendListFragment()
                1 -> DiaryListFragment()
                2 -> CalendarFragment()
                else -> DiaryListFragment()
            }
        }
    }
}
