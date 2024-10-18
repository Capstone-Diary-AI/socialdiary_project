package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.example.socialdiary_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        // 상단 네비게이션바 설정
        val toolbar = view.findViewById<Toolbar>(R.id.top_navigation)
        toolbar.title = "캘린더"

        // 플러스 버튼 클릭 이벤트: 일정 추가 팝업
        val addButton = view.findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            showEventAddPopup()
        }

        // 설정 버튼: 설정 페이지로 이동
        val settingsButton = view.findViewById<ImageButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        // 하단 네비게이션바 설정
        setupBottomNavigation(view)

        return view
    }

    // 일정 추가 팝업 로직
    private fun showEventAddPopup() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("일정 추가")

        // 팝업에 표시할 옵션
        val options = arrayOf("일정 추가", "기존 일정 수정", "일정 삭제")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> addNewEvent()  // 일정 추가
                1 -> editEvent()    // 일정 수정
                2 -> deleteEvent()  // 일정 삭제
            }
        }

        // 취소 버튼
        builder.setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    // 각 기능에 맞는 메서드 구현
    private fun addNewEvent() {
        // 일정 추가 로직
        Toast.makeText(context, "새로운 일정을 추가했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun editEvent() {
        // 기존 일정 수정 로직
        Toast.makeText(context, "일정을 수정했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun deleteEvent() {
        // 일정 삭제 로직
        Toast.makeText(context, "일정을 삭제했습니다.", Toast.LENGTH_SHORT).show()
    }

    // 하단 네비게이션바 설정
    private fun setupBottomNavigation(view: View) {
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> true
                R.id.nav_diary -> true
                R.id.nav_calendar -> true
                else -> false
            }
        }
    }
}
