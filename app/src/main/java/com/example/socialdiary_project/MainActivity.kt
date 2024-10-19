package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialdiary_project.ui.theme.DiaryEntryActivity
import com.example.socialdiary_project.ui.theme.SettingsActivity
import com.example.socialdiary_project.ui.theme.ViewPagerFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var pageTitle: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var addButton: ImageButton
    private lateinit var settingsButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상단 네비게이션 바 버튼 초기화
        pageTitle = findViewById(R.id.page_title)
        addButton = findViewById(R.id.add_button)
        settingsButton = findViewById(R.id.settings_button)

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        val fragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = fragmentAdapter

        // 상단 버튼 이벤트 설정
        addButton.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> showFriendManagementDialog() // 친구 관리 팝업
                1 -> startActivity(Intent(this, DiaryEntryActivity::class.java)) // 일기 작성 화면 이동
                2 -> showAddScheduleDialog() // 일정 추가 팝업
            }
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // ViewPager와 하단 네비게이션 바 연결
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

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> viewPager.currentItem = 0
                R.id.nav_diary -> viewPager.currentItem = 1
                R.id.nav_calendar -> viewPager.currentItem = 2
            }
            true
        }

        // 초기 페이지를 일기 목록으로 설정
        viewPager.currentItem = 1
    }

    // 친구 관리 팝업 다이얼로그
    private fun showFriendManagementDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_friend_management, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("친구 관리")
            .setNegativeButton("취소", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        // 팝업 내부 버튼 클릭 리스너 설정
        dialogView.findViewById<View>(R.id.btn_add_friend).setOnClickListener {
            // 친구 추가 로직
            alertDialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.btn_remove_friend).setOnClickListener {
            // 친구 삭제 로직
            alertDialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.btn_manage_groups).setOnClickListener {
            // 그룹 관리 로직
            alertDialog.dismiss()
        }
    }

    // 일정 추가 팝업 다이얼로그
    private fun showAddScheduleDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_schedule, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("일정 추가")
            .setNegativeButton("취소", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        // 일정 저장 버튼 클릭 리스너 설정
        dialogView.findViewById<View>(R.id.btn_save_event).setOnClickListener {
            // 일정 추가 로직
            alertDialog.dismiss()
        }
    }
}
