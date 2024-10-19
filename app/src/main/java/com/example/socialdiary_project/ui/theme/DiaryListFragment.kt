// DiaryListFragment.kt
package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class DiaryListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트 레이아웃 로드
        val view = inflater.inflate(R.layout.fragment_diary_list, container, false)

        return view
    }
}