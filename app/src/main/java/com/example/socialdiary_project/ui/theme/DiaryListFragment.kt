package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diary_list, container, false)

        // 상단 네비게이션바 설정
        val toolbar = view.findViewById<Toolbar>(R.id.top_navigation)
        toolbar.title = "일기 목록"

        // 플러스 버튼: 일기 작성 화면으로 이동
        val addButton = view.findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(activity, DiaryEntryActivity::class.java)
            startActivity(intent)
        }

        // 설정 버튼: 설정 페이지로 이동
        val settingsButton = view.findViewById<ImageButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        // 하단 네비게이션바 유지
        setupBottomNavigation(view)

        return view
    }

    private fun setupBottomNavigation(view: View) {
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> {
                    // 친구 페이지로 이동
                    true
                }
                R.id.nav_diary -> {
                    // 일기 페이지로 이동
                    true
                }
                R.id.nav_calendar -> {
                    // 캘린더 페이지로 이동
                    true
                }
                else -> false
            }
        }
    }
}
