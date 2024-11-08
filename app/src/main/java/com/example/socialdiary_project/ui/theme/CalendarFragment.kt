// CalendarFragment.kt
// 캘린더를 표시하는 프래그먼트로, 사용자가 날짜를 확인하고 선택할 수 있는 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_calendar.xml 파일을 inflate하여 화면에 캘린더 레이아웃을 표시
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        return view
    }
}
