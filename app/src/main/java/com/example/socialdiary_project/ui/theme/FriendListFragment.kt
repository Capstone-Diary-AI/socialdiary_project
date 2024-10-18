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

class FriendListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)

        // 상단 네비게이션바 설정
        val toolbar = view.findViewById<Toolbar>(R.id.top_navigation)
        toolbar.title = "친구 목록"

        // 플러스 버튼 클릭 이벤트: 친구 관리 팝업
        val addButton = view.findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            showFriendManagementPopup()
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

    // 친구 관리 팝업 로직
    private fun showFriendManagementPopup() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("친구 관리")

        // 팝업에 표시할 옵션
        val options = arrayOf("친구 추가", "친구 삭제", "친구 목록 관리")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> addNewFriend() // 친구 추가
                1 -> deleteFriend()  // 친구 삭제
                2 -> manageFriendList()  // 친구 목록 관리
            }
        }

        // 취소 버튼
        builder.setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    // 각 기능에 맞는 메서드 구현
    private fun addNewFriend() {
        // 친구 추가 로직
        Toast.makeText(context, "친구를 추가했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun deleteFriend() {
        // 친구 삭제 로직
        Toast.makeText(context, "친구를 삭제했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun manageFriendList() {
        // 친구 목록 관리 로직
        Toast.makeText(context, "친구 목록을 관리합니다.", Toast.LENGTH_SHORT).show()
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
