// DiaryListFragment.kt
// 사용자가 작성한 일기 목록을 보여주는 프래그먼트로, 리스트 형태로 일기를 표시

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class DiaryListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var spinnerFilter: Spinner

    // 예시 일기 목록 데이터
    private var allDiaries = listOf(
        "[개인] 오늘의 일기",
        "[공유] 여행 이야기",
        "[개인] 비밀 노트",
        "[공유] 프로젝트 회의",
        "[공유] 가족 모임"
    )
    private var filteredDiaries = allDiaries // 초기에는 전체 목록 표시

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_diary_list.xml 파일을 inflate하여 일기 목록 레이아웃을 화면에 표시
        val view = inflater.inflate(R.layout.fragment_diary_list, container, false)

        // Spinner와 ListView 초기화
        spinnerFilter = view.findViewById(R.id.spinner_filter)
        listView = view.findViewById(R.id.listview_diary)

        // Spinner 옵션 설정
        val filterOptions = arrayOf("모두", "공유", "개인")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, filterOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = spinnerAdapter

        // ListView 초기화
        updateListView()

        // Spinner 선택 이벤트 리스너 설정
        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> filteredDiaries = allDiaries // 모두
                    1 -> filteredDiaries = allDiaries.filter { it.startsWith("[공유]") } // 공유
                    2 -> filteredDiaries = allDiaries.filter { it.startsWith("[개인]") } // 개인
                }
                updateListView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무것도 선택되지 않은 경우 기본 설정을 유지
            }
        }

        return view
    }

    // 필터링된 일기 목록을 ListView에 표시하는 메서드
    private fun updateListView() {
        val diaryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filteredDiaries)
        listView.adapter = diaryAdapter
    }
}
