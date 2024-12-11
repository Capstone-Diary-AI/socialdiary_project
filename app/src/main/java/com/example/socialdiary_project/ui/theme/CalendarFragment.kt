// CalendarFragment.kt
// 캘린더를 표시하는 프래그먼트로, 사용자가 날짜를 확인하고 선택할 수 있는 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_calendar.xml 파일을 inflate하여 화면에 캘린더 레이아웃을 표시
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        // 일정 예시 데이터 생성
        val eventList = listOf(
            mapOf("date" to "2024-11-01", "event" to "친구와 저녁 식사"),
            mapOf("date" to "2024-11-05", "event" to "프로젝트 미팅"),
            mapOf("date" to "2024-11-10", "event" to "가족 모임"),
            mapOf("date" to "2024-11-14", "event" to "헬스장 등록"),
            mapOf("date" to "2024-11-18", "event" to "스터디 모임"),
            mapOf("date" to "2024-11-20", "event" to "패스티벌 참가하기"),
            mapOf("date" to "2024-11-23", "event" to "친구 생일 파티"),
            mapOf("date" to "2024-11-27", "event" to "최종 보고서 작성"),
            mapOf("date" to "2024-11-30", "event" to "영화 관람")
        )

        // ListView에 일정 데이터를 표시하기 위한 SimpleAdapter 설정
        val listView: ListView = view.findViewById(R.id.listview_schedule)
        val adapter = SimpleAdapter(
            context,
            eventList,
            android.R.layout.simple_list_item_2,
            arrayOf("date", "event"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        listView.adapter = adapter

        return view
    }
}
