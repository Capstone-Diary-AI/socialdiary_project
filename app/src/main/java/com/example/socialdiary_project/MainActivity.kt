package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.databinding.ActivityMainBinding
import com.example.socialdiary_project.ui.theme.DiaryEntryActivity
import com.example.socialdiary_project.ui.theme.SettingsActivity
import com.example.socialdiary_project.ui.theme.ViewPagerFragmentAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentAdapter = ViewPagerFragmentAdapter(this)
        binding.viewPager.adapter = fragmentAdapter

        // 상단 버튼 이벤트 설정
        binding.addButton.setOnClickListener {
            when (binding.viewPager.currentItem) {
                0 -> showFriendManagementDialog() // 친구 관리 팝업
                1 -> startActivity(Intent(this, DiaryEntryActivity::class.java)) // 일기 작성 화면 이동
                2 -> showAddScheduleDialog() // 일정 추가 팝업
            }
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // ViewPager와 하단 네비게이션 바 연결
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.pageTitle.text = "친구 목록"
                        binding.bottomNavigation.menu.findItem(R.id.nav_friends).isChecked = true
                    }
                    1 -> {
                        binding.pageTitle.text = "일기 목록"
                        binding.bottomNavigation.menu.findItem(R.id.nav_diary).isChecked = true
                    }
                    2 -> {
                        binding.pageTitle.text = "캘린더"
                        binding.bottomNavigation.menu.findItem(R.id.nav_calendar).isChecked = true
                    }
                }
            }
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> binding.viewPager.currentItem = 0
                R.id.nav_diary -> binding.viewPager.currentItem = 1
                R.id.nav_calendar -> binding.viewPager.currentItem = 2
            }
            true
        }

        // 초기 페이지를 일기 목록으로 설정
        binding.viewPager.currentItem = 1
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
