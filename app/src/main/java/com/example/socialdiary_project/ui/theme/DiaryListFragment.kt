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

        // 상단 네비게이션바에서 플러스 버튼과 설정 버튼 찾기
        val toolbar = view.findViewById<Toolbar>(R.id.top_navigation)
        val addButton = toolbar.findViewById<ImageButton>(R.id.add_button)
        val settingsButton = toolbar.findViewById<ImageButton>(R.id.settings_button)

        // 플러스 버튼 클릭 이벤트 설정
        addButton.setOnClickListener {
            val intent = Intent(activity, DiaryEntryActivity::class.java)
            startActivity(intent)
        }

        // 설정 버튼 클릭 이벤트 설정
        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
